package com.home.amazon.provisional.lambda;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.home.amazon.provisional.core.AuroraConnectionSimpleJDBC;
import com.home.amazon.provisional.dao.TransactionDao;
import com.home.amazon.provisional.model.Transcation;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rdsdata.model.BadRequestException;
import software.amazon.awssdk.services.rdsdata.model.ForbiddenException;
import software.amazon.awssdk.services.rdsdata.model.InternalServerErrorException;
import software.amazon.awssdk.services.rdsdata.model.ServiceUnavailableErrorException;
import software.amazon.awssdk.services.rdsdata.model.StatementTimeoutException;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SqsException;

public class PutAuroraItemFunction extends AuroraConnectionSimpleJDBC
		implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	private static final int HTTP_STATUS_CODE_NO_CONTENT = 500;
	private static final int HTTP_STATUS_CODE_CREATED = 200;

	public PutAuroraItemFunction() {
		super();
	}

	@Override
	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {

		String body = input.getBody();

		context.getLogger().log("body " + body);
		Gson gson = new GsonBuilder().setDateFormat("YYYY-MM-dd hh:mm:ss").create();
		Transcation item = gson.fromJson(body, Transcation.class);

		Map<String, String> responseHeader = new HashMap<String, String>();
		responseHeader.put("Access-Control-Allow-Origin", "*");// Required for CORS support to work
		responseHeader.put("Access-Control-Allow-Credentials", "true"); // Required for cookies, authorization headers
																		// with HTTPS
		String responseBody = " ";

		int statusCode = HTTP_STATUS_CODE_NO_CONTENT;

		try {
			if (item != null) {
				TransactionDao transactionDao = new TransactionDao();
				int responseCode = transactionDao.saveTransaction(item, context);
				if (1 == responseCode) {

					String queue = "https://sqs.us-east-2.amazonaws.com/302794958025/queue-to-invoke-lambda.fifo";
					SqsAsyncClient asyncClient = SqsAsyncClient.builder().region(Region.US_EAST_2).build();
					sendSingleMessage(asyncClient, queue, context, item.toString());
					asyncClient.close();

					statusCode = HTTP_STATUS_CODE_CREATED;
					responseBody = new Gson().toJson("Data saved succesfully");
				}
			}
			return new APIGatewayProxyResponseEvent().withStatusCode(statusCode).withIsBase64Encoded(Boolean.FALSE)
					.withHeaders(responseHeader).withBody(responseBody);
		} catch (BadRequestException | StatementTimeoutException | InternalServerErrorException | ForbiddenException
				| ServiceUnavailableErrorException dataException) {
			responseBody = new Gson().toJson("Error while persisting the data, please try later");
			context.getLogger().log("Execute statement failed: Error Message: {} " + dataException.getMessage()
					+ " Cause: {} " + dataException.getCause());
			return new APIGatewayProxyResponseEvent().withStatusCode(statusCode).withIsBase64Encoded(Boolean.FALSE)
					.withHeaders(responseHeader).withBody(responseBody);
		}

	}

	private void sendSingleMessage(SqsAsyncClient sqsClient, String queueUrl, Context context, String msgBody) {
		try {
			sqsClient.sendMessage(SendMessageRequest.builder().queueUrl(queueUrl).messageBody(msgBody)
					.messageGroupId("IncrementalProcLambdaGroup").build());
			context.getLogger().log("Message has been sent successfully");
		} catch (SqsException e) {
			context.getLogger().log("error msg : " + e.awsErrorDetails().errorMessage());
		}
	}
}

package com.home.amazon.provisional.lambda;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.home.amazon.provisional.model.Transcation;

@ExtendWith(MockitoExtension.class)
class PutAuroraItemFunctionTest {

	// MOCK DATA
	private static final int TEST_ID = 1;
	private static final long GARAGE_ID = 2;
	private static final String GARAGE_NAME = "Schenectady Garage Location";
	private static final long BUS_NUMBER = 203;
	private static final long DRIVER_NUMBER = 4323;
	private static final long ROUTE_NUMBER = 601;
	private static final long RUN_NUMBER = 19;
	private static final String TRANSACTION_TYPE_DESC = "test stop name";
	private static final String TRANSACTION_TIME_STAMP = "2022-10-20 05:30:55";
	private static final long TRANSACTION_TYPE = 123;
	private static final long TRIP_NUMBER = 98;
	private static final String TRANSIT_DAY = "2022-10-20";
	private static final Long TRANSCATION_ID = 12L;
	private static final String PRODUCT_NAME = "test product";
	private static final String PASS_CATEGORY = "test product category";

	//private static final int HTTP_STATUS_CODE_NO_CONTENT = 500;
	private static final int HTTP_STATUS_CODE_CREATED = 200;

	@Mock
	private Connection mockConnection;

	@Mock
	private PreparedStatement mockStatement;

	@Mock
	private ResultSet rs;

	@Mock
	private Context context;

	@Mock
	private APIGatewayProxyRequestEvent request;

	@Mock
	private APIGatewayProxyResponseEvent response;

	@Mock
	LambdaLogger loggerMock;

	private Transcation getMockData() {
		Transcation testTranscation = new Transcation(TEST_ID);
		testTranscation.setGarageId(GARAGE_ID);
		testTranscation.setTransactionId(TRANSCATION_ID);
		testTranscation.setTransactionTimeStamp(TRANSACTION_TIME_STAMP);
		testTranscation.setGarageName(GARAGE_NAME);
		testTranscation.setTransitDay(TRANSIT_DAY);
		testTranscation.setTransactionTypeId(TRANSACTION_TYPE);
		testTranscation.setTransactionTypeDescription(TRANSACTION_TYPE_DESC);
		testTranscation.setDriverNumber(DRIVER_NUMBER);
		testTranscation.setBusNumber(BUS_NUMBER);
		testTranscation.setRouteNumber(ROUTE_NUMBER);
		testTranscation.setRunNumber(RUN_NUMBER);
		testTranscation.setTripNumber(TRIP_NUMBER);
		testTranscation.setProductName(PRODUCT_NAME);
		testTranscation.setPassCategory(PASS_CATEGORY);
		testTranscation.setRidership(true);

		return testTranscation;
	}

	@Test
	public void shouldCreateAuroraItemIfRequestIsValid() throws Exception {
		when(context.getLogger()).thenReturn(loggerMock);
		Transcation testTranscation = getMockData();
		when(request.getBody()).thenReturn(new Gson().toJson(testTranscation));
		PutAuroraItemFunction handler = new PutAuroraItemFunction();
		APIGatewayProxyResponseEvent response = handler.handleRequest(request, context);
		assertEquals(HTTP_STATUS_CODE_CREATED, (int) response.getStatusCode());
	}

}
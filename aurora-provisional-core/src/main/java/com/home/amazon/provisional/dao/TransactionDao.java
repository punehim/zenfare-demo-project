package com.home.amazon.provisional.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.amazonaws.services.lambda.runtime.Context;
import com.home.amazon.provisional.core.AuroraConnectionSimpleJDBC;
import com.home.amazon.provisional.model.Transcation;

public class TransactionDao extends AuroraConnectionSimpleJDBC {

	public int saveTransaction(Transcation item, Context context) {

		StringBuilder insertSQL = new StringBuilder();
		insertSQL.append(" insert into glpoc.all_trans_data_revised (garage_id, transaction_id,transaction_timestamp,");
		insertSQL.append(" garage_name,transit_day,transaction_type_id,transaction_type_description,");
		insertSQL.append(" driver_number,bus_number,route_number,run_number,trip_number,product_name,pass_category,ridership_flag)");
		insertSQL.append(" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); ");

		context.getLogger().log("insertSQL: " + insertSQL);

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int responseCode = 0;
		try {
			if (item != null) {
				connection = getConnection();
				preparedStatement = connection.prepareStatement(insertSQL.toString());
				preparedStatement.setLong(1, item.getGarageId());
				preparedStatement.setLong(2, item.getTransactionId());
				preparedStatement.setString(3, item.getTransactionTimeStamp());
				preparedStatement.setString(4, item.getGarageName());
				preparedStatement.setString(5, item.getTransitDay());
				preparedStatement.setLong(6, item.getTransactionTypeId());
				preparedStatement.setString(7, item.getTransactionTypeDescription());
				preparedStatement.setLong(8, item.getDriverNumber());
				preparedStatement.setLong(9, item.getBusNumber());
				preparedStatement.setLong(10, item.getRouteNumber());
				preparedStatement.setLong(11, item.getRunNumber());
				preparedStatement.setLong(12, item.getTripNumber());
				preparedStatement.setString(13, item.getProductName());
				preparedStatement.setString(14, item.getPassCategory());
				preparedStatement.setLong(15, !item.isRidership() ? 0 : 1);

				responseCode = preparedStatement.executeUpdate();
			}
		} catch (SQLException | ClassNotFoundException dataException) {
			context.getLogger().log("Execute statement failed: Error Message: {} " + dataException.getMessage()
					+ " Cause: {} " + dataException.getCause());
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException sqlException) {
				context.getLogger().log("Execute statement failed: Error Message: {} " + sqlException.getMessage()
						+ " Cause: {} " + sqlException.getCause());
			}
		}
		return responseCode;
	}

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import database.DBConnection;
import database.DBUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Customer;

/**
 * Service class for handle customer related functions
 *
 * @author DILUSHA
 */
public class CustomerService {

    public CustomerService() {

    }

    public List<Customer> getCustomers() {
        final List<Customer> customers = new ArrayList();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = DBConnection.getInstance().getConnection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM customers");

            while (resultSet.next()) {
                customers.add(new Customer(resultSet.getInt("customer_id"),
                        resultSet.getString("short_name"),
                        resultSet.getString("full_name")));
            }
            return customers;

        } catch (Exception e) {
            String msg = String.format("Error occured at retrieving"
                    + " customer list - %s", e.getMessage());
            System.out.println(msg);
        } finally {
            DBUtil.closeResources(resultSet, statement);
        }
        return customers;
    }
}

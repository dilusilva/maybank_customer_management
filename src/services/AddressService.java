/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import database.DBConnection;
import database.DBUtil;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Address;

/**
 * Service class for handle address related functions
 *
 * @author DILUSHA
 */
public class AddressService {

    private static final Logger LOGGER = Logger.getLogger(AddressService.class.getName());

    public AddressService() {

    }

    public List<Address> getAddresses(final int customerId) {
        List<Address> addresses = new ArrayList();
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = DBConnection.getInstance().getConnection().createStatement();
            rs = statement.executeQuery("SELECT * FROM addresses WHERE"
                    + " customer_id= '" + customerId + "'");

            while (rs.next()) {
                Address add = new Address();
                add.setId(rs.getInt("id"));
                add.setAddressLine1(rs.getString("address_line_1"));
                add.setAddressLine2(rs.getString("address_line_2"));
                add.setAddressLine3(rs.getString("address_line_3"));
                add.setCity(rs.getString("city"));
                add.setPostalCode(rs.getString("postal_code"));
                addresses.add(add);
            }
            System.out.println("address list size -" + String.valueOf(addresses.size()));

        } catch (Exception e) {
            String msg = String.format("Error occured at retriving address list - %s",
                    e.getMessage());
            System.out.println(msg);
            LOGGER.log(Level.WARNING, msg);
        } finally {
            DBUtil.closeResources(rs, statement);
        }
        return addresses;
    }

    public void saveAddress(final Address addres) {
        Statement statement = null;
        try {
            statement = DBConnection.getInstance().getConnection().createStatement();
            statement.executeUpdate("INSERT INTO `customer_management`.`addresses`\n"
                    + " (`address_line_1`,\n"
                    + "`address_line_2`,\n"
                    + "`address_line_3`,\n"
                    + "`city`,\n"
                    + "`postal_code`,\n"
                    + "`customer_id`)\n"
                    + " VALUES \n"
                    + "('" + addres.getAddressLine1() + "', \n"
                    + " '" + addres.getAddressLine2() + "', \n"
                    + " '" + addres.getAddressLine3() + "', \n"
                    + " '" + addres.getCity() + "', \n"
                    + " '" + addres.getPostalCode() + "', \n"
                    + " '" + addres.getCustomerId() + "') ");
        } catch (Exception ex) {
            String msg = String.format("Error occured at saving new address - %s",
                    ex.getMessage());
            System.out.println(msg);
            LOGGER.log(Level.WARNING, msg);
        } finally {
            DBUtil.closeResources(null, statement);
        }

    }

    public void updateAddress(final Address addres) {
        Statement statement = null;
        try {
            System.out.println("id---" + addres.getId());
            statement = DBConnection.getInstance().getConnection().createStatement();
            statement.executeUpdate("UPDATE `customer_management`.`addresses`\n"
                    + " SET\n"
                    + " `address_line_1` = '" + addres.getAddressLine1() + "',\n"
                    + " `address_line_2` = '" + addres.getAddressLine2() + "',\n"
                    + " `address_line_3` = '" + addres.getAddressLine3() + "',\n"
                    + " `city` = '" + addres.getCity() + "',\n"
                    + " `postal_code` = '" + addres.getPostalCode() + "'\n"
                    + "  WHERE `id` = '" + addres.getId() + "' ");
        } catch (Exception ex) {
            String msg = String.format("Error occured at update address - %s",
                    ex.getMessage());
            System.out.println(msg);
            LOGGER.log(Level.WARNING, msg);
        } finally {
            DBUtil.closeResources(null, statement);
        }
    }

    public void deleteAddress(final int addressId) {
        Statement statement = null;
        try {
            System.out.println("id---" + addressId);
            statement = DBConnection.getInstance().getConnection().createStatement();
            statement.executeUpdate("DELETE FROM `customer_management`.`addresses`\n"
                    + " WHERE `id` = '" + addressId + "'");
        } catch (Exception ex) {
            String msg = String.format("Error occured at delete address - %s",
                    ex.getMessage());
            System.out.println(msg);
            LOGGER.log(Level.WARNING, msg);
        } finally {
            DBUtil.closeResources(null, statement);
        }

    }
}

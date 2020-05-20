package com.Sagdatov.RestAPI.dao;

import com.Sagdatov.RestAPI.classesDB.Customer;
import com.Sagdatov.RestAPI.classesDB.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomersDAO {
    static Connection con = ConnectionDB.getDBConnection();

    public static List<Customer> GetAllCustomer() throws SQLException {
        ResultSet rs = con.prepareStatement("select * from customers ;").executeQuery();
        List<Customer> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Customer(rs.getString("id"),rs.getString("name"),rs.getString("phoneNumber")));
        }
        return  list;
    }

    public static Customer GetCustomer(String id)throws SQLException {
        Customer customer = new Customer();
        String sql = "select * from customers where id = '"+id+"';";
        ResultSet rs = con.prepareStatement(sql).executeQuery();
        while (rs.next()) {
            customer = new Customer(rs.getString("id"),rs.getString("name"),rs.getString("phoneNumber"));
        }
        return customer;
    }



    //через return возвращать с базы добавленный элемент (есть какой-то sql запрос c ретурнети )
    public static  void AddCustomer(Customer customer)throws SQLException{
        Boolean flag = con.prepareStatement("INSERT into customers (name,phonenumber) values ('"+customer.getName()+"','"+customer.getPhoneNumber()+"');").execute();
    }

    public static  void UpdateCustomer(Customer customer) throws SQLException {
        String sql = "UPDATE customers SET name = '"+customer.getName()+"' , phonenumber = '"+customer.getPhoneNumber()+"' WHERE id = "+customer.getId()+";";
        PreparedStatement st = con.prepareStatement(sql);
        int flag = st.executeUpdate();
    }

    public static void DeleteCustomer(Customer customer) throws SQLException {
        String sql = "DELETE FROM customers WHERE id = '"+customer.getId()+"';";
        PreparedStatement st = con.prepareStatement(sql);
        int rowsDeleted = st.executeUpdate();
    }
}

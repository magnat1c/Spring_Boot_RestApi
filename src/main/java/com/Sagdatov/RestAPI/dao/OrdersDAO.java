package com.Sagdatov.RestAPI.dao;

import com.Sagdatov.RestAPI.classesDB.Order;
import com.Sagdatov.RestAPI.classesDB.Worker;
import com.Sagdatov.RestAPI.controller.WorkersController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAO {
    static Connection con = ConnectionDB.getDBConnection();

    public static List<Order> GetAllOrders() throws SQLException {
        ResultSet rs = con.prepareStatement("select * from orders ;").executeQuery();
        List<Order> list = new ArrayList<>();
        while (rs.next()) {
            Worker wr= WorkersDAO.GetWorker(rs.getString("id_worker"));
            list.add(new Order(rs.getString("id"),rs.getString("order_date"),rs.getString("order_status"),rs.getString("id_worker"),rs.getString("order_price"),rs.getInt("quantity_product")));
        }
        return  list;
    }


    public static Order GetOrder(String id)throws SQLException {
        Order order = new Order();
        String sql = "select * from orders where id = '"+id+"';";
        ResultSet rs = con.prepareStatement(sql).executeQuery();
        while (rs.next()) {
            order = new Order(rs.getString("id"),rs.getString("order_date"),rs.getString("order_status"),rs.getString("id_worker"),rs.getString("order_price"),rs.getInt("quantity_product"));
        }
        return order;
    }

    public static  void AddOrder(Order order)throws SQLException{
        Boolean flag = con.prepareStatement("INSERT into orders (order_date, order_status,id_worker, order_price, quantity_product) values ('"+order.getOrder_date()+"','"+order.getOrder_status()+"','"+order.getWorker().getId()+"','"+order.getOrder_price()+"','"+order.getQuantity_product()+"');").execute();
    }

    public static  void UpdateOrder(Order order) throws SQLException {
        String sql = "UPDATE orders SET order_date = '"+order.getOrder_date()+"' , order_status = '"+order.getOrder_status()+"' , id_worker = '"+order.getWorker().getId()+"' , order_price = '"+order.getOrder_price()+"' , quantity_product = '"+order.getQuantity_product()+"' WHERE id = "+order.getId()+";";
        PreparedStatement st = con.prepareStatement(sql);
        int flag = st.executeUpdate();
    }

    public static void DeleteOrder(Order order) throws SQLException {
        String sql = "DELETE FROM orders WHERE id = '"+order.getId()+"';";
        PreparedStatement st = con.prepareStatement(sql);
        int rowsDeleted = st.executeUpdate();
    }
}

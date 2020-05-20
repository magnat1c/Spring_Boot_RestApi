package com.Sagdatov.RestAPI.controller;

import com.Sagdatov.RestAPI.classesDB.Order;
import com.Sagdatov.RestAPI.classesDB.Worker;
import com.Sagdatov.RestAPI.dao.OrdersDAO;
import com.Sagdatov.RestAPI.dao.WorkersDAO;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class OrdersController {

    Gson gson = new Gson();


    @RequestMapping(value = "/orders",
            method = RequestMethod.GET)
    @ResponseBody
    public List<Order> list(){
        List<Order> orders = new ArrayList<>();

        {
            try {
                orders = OrdersDAO.GetAllOrders();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return orders;
    }

    @RequestMapping(value = "/orders/{id}", //
            method = RequestMethod.GET)
    @ResponseBody
    public Order getOrder(@PathVariable("id") String id) throws SQLException {
        Order order = OrdersDAO.GetOrder(id);
        if(order.getId()!=""){return order;}
        return new Order();

    }



    @RequestMapping(value = "/orders",
            method = RequestMethod.POST)
    @ResponseBody
    public Order create(@RequestBody String orderStr) throws SQLException {
        Order order = gson.fromJson(orderStr, Order.class);
        OrdersDAO.AddOrder(order);
        return order;
    }

    @RequestMapping(value = "/orders/{id}", //
            method = RequestMethod.PUT)
    @ResponseBody
    public Order update(@PathVariable String id, @RequestBody String orderStr) throws SQLException {
        Order workerFromDB = getOrder(id);
        Order order = gson.fromJson(orderStr, Order.class);
        order.setId(workerFromDB.getId());
        OrdersDAO.UpdateOrder(order);
        return order;
    }

    @RequestMapping(value = "/orders/{id}",
            method = RequestMethod.DELETE)
    @ResponseBody
    public Order delete(@PathVariable("id") String id) throws SQLException {
        Order order = getOrder(id);
        OrdersDAO.DeleteOrder(order);
        return order;
    }

}

package com.Sagdatov.RestAPI.classesDB;

import com.Sagdatov.RestAPI.classesDB.Worker;
import com.Sagdatov.RestAPI.dao.WorkersDAO;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

//Заказ
public class Order{
    String id;
    String order_date;
    String order_status;
    Worker worker;
    String order_price;
    int quantity_product;

    public Order(String id, String order_date, String order_status, String id_worker, String order_price, int quantity_product) throws SQLException {
        this.id = id;
        this.order_date = order_date;
        this.order_status = order_status;
        this.worker = WorkersDAO.GetWorker(id_worker);
        this.order_price = order_price;
        this.quantity_product = quantity_product;
    }

    public Order(){};

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", order_date='" + order_date + '\'' +
                ", order_status='" + order_status + '\'' +
                ", worker=" + worker +
                ", order_price='" + order_price + '\'' +
                ", quantity_product=" + quantity_product +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public String getOrder_price() {
        return order_price;
    }

    public void setOrder_price(String order_price) {
        this.order_price = order_price;
    }

    public int getQuantity_product() {
        return quantity_product;
    }

    public void setQuantity_product(int quantity_product) {
        this.quantity_product = quantity_product;
    }
}

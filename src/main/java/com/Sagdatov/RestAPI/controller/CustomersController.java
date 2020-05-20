package com.Sagdatov.RestAPI.controller;

import com.Sagdatov.RestAPI.classesDB.Customer;
import com.Sagdatov.RestAPI.classesDB.Worker;
import com.Sagdatov.RestAPI.dao.CustomersDAO;
import com.Sagdatov.RestAPI.dao.WorkersDAO;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomersController {
    Gson gson = new Gson();


    @RequestMapping(value = "/customers",
            method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> list(){
        List<Customer> customers = new ArrayList<>();

        {
            try {
                customers = CustomersDAO.GetAllCustomer();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customers;
    }

    @RequestMapping(value = "/customers/{id}", //
            method = RequestMethod.GET)
    @ResponseBody
    public Customer getCustomer(@PathVariable("id") String id) throws SQLException {
        Customer customers = CustomersDAO.GetCustomer(id);
        if(customers.getId()!=null){return customers;}
        return new Customer();

    }



    @RequestMapping(value = "/customers",
            method = RequestMethod.POST)
    @ResponseBody
    public Customer create(@RequestBody String customerStr) throws SQLException {
        Customer customer = gson.fromJson(customerStr, Customer.class);
        CustomersDAO.AddCustomer(customer);
        return customer;
    }

    @RequestMapping(value = "/customers/{id}", //
            method = RequestMethod.PUT)
    @ResponseBody
    public Customer update(@PathVariable String id, @RequestBody String customerStr) throws SQLException {
        Customer customerFromDB = getCustomer(id);
        Customer customer = gson.fromJson(customerStr, Customer.class);
        customer.setId(customerFromDB.getId());
        if(customer.getName()==null){
            customer.setName(customerFromDB.getName());
        }
        if(customer.getPhoneNumber()==null){
            customer.setPhoneNumber(customerFromDB.getPhoneNumber());
        }
        CustomersDAO.UpdateCustomer(customer);
        return customer;
    }

    @RequestMapping(value = "/customers/{id}",
            method = RequestMethod.DELETE)
    @ResponseBody
    public Customer delete(@PathVariable("id") String id) throws SQLException {
        Customer customer = getCustomer(id);
        CustomersDAO.DeleteCustomer(customer);
        return customer;
    }
}

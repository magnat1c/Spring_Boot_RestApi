package com.Sagdatov.RestAPI.controller;

import com.Sagdatov.RestAPI.classesDB.Customer;
import com.Sagdatov.RestAPI.classesDB.Product;
import com.Sagdatov.RestAPI.dao.CustomersDAO;
import com.Sagdatov.RestAPI.dao.ProductsDAO;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductsController {
    Gson gson = new Gson();


    @RequestMapping(value = "/products",
            method = RequestMethod.GET)
    @ResponseBody
    public List<Product> list(){
        List<Product> products = new ArrayList<>();

        {
            try {
                products = ProductsDAO.GetAllProduct();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return products;
    }

    @RequestMapping(value = "/products/{id}", //
            method = RequestMethod.GET)
    @ResponseBody
    public Product getProduct(@PathVariable("id") String id) throws SQLException {
        Product product = ProductsDAO.GetProduct(id);
        if(product.getId()!=null){return product;}
        return new Product();

    }



    @RequestMapping(value = "/products",
            method = RequestMethod.POST)
    @ResponseBody
    public Product create(@RequestBody String productStr) throws SQLException {
        Product product = gson.fromJson(productStr, Product.class);
        ProductsDAO.AddProduct(product);
        return product;
    }

    @RequestMapping(value = "/products/{id}", //
            method = RequestMethod.PUT)
    @ResponseBody
    public Product update(@PathVariable String id, @RequestBody String productStr) throws SQLException {
        Product productFromDB = getProduct(id);
        Product product = gson.fromJson(productStr, Product.class);
        product.setId(productFromDB.getId());
        if(product.getName()==null){
            product.setName(productFromDB.getName());
        }
        ProductsDAO.UpdateProduct(product);
        return product;
    }

    @RequestMapping(value = "/products/{id}",
            method = RequestMethod.DELETE)
    @ResponseBody
    public Product delete(@PathVariable("id") String id) throws SQLException {
        Product product = getProduct(id);
        ProductsDAO.DeleteProduct(product);
        return product;
    }
}

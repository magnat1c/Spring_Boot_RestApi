package com.Sagdatov.RestAPI.dao;

import com.Sagdatov.RestAPI.classesDB.Customer;
import com.Sagdatov.RestAPI.classesDB.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsDAO {
    static Connection con = ConnectionDB.getDBConnection();

    public static List<Product> GetAllProduct() throws SQLException {
        ResultSet rs = con.prepareStatement("select * from products ;").executeQuery();
        List<Product> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Product(rs.getString("id"),rs.getString("name")));
        }
        return  list;
    }

    public static Product GetProduct(String id)throws SQLException {
        Product product = new Product();
        String sql = "select * from products where id = '"+id+"';";
        ResultSet rs = con.prepareStatement(sql).executeQuery();
        while (rs.next()) {
            product = new Product(rs.getString("id"),rs.getString("name"));
        }
        return product;
    }

    public static  void AddProduct(Product product)throws SQLException{
        Boolean flag = con.prepareStatement("INSERT into products (id , name) values ('"+product.getId()+"','"+product.getName()+"');").execute();
    }

    public static  void UpdateProduct(Product product) throws SQLException {
        String sql = "UPDATE products SET name = '"+product.getName()+"' WHERE id = "+product.getId()+";";
        PreparedStatement st = con.prepareStatement(sql);
        int flag = st.executeUpdate();
    }

    public static void DeleteProduct(Product product) throws SQLException {
        String sql = "DELETE FROM products WHERE id = '"+product.getId()+"';";
        PreparedStatement st = con.prepareStatement(sql);
        int rowsDeleted = st.executeUpdate();
    }
}

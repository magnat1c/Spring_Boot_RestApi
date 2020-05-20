package com.Sagdatov.RestAPI.dao;

import com.Sagdatov.RestAPI.classesDB.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkersDAO {
    static Connection con = ConnectionDB.getDBConnection();

    public static List<Worker> GetAllWorkers() throws SQLException {
        ResultSet rs = con.prepareStatement("select * from workers ;").executeQuery();
        List<Worker> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Worker(rs.getString("id"),rs.getString("name"),rs.getString("phoneNumber"),""));
        }
        return  list;
    }

//    public void UpdateWorkers(List<Worker> workers) throws SQLException {
//        List<Worker> workersDB = WorkersDAO.GetAllWorkers();
//        for
//    }

    public static Worker GetWorker(String id)throws SQLException {
        Worker wr = new Worker();
        String sql = "select * from workers where id = '"+id+"';";
        ResultSet rs = con.prepareStatement(sql).executeQuery();
        while (rs.next()) {
            wr = new Worker(rs.getString("id"),rs.getString("name"),rs.getString("phoneNumber"),"");
        }
        return wr;
    }

    public static  void AddWorker(Worker worker)throws SQLException{
        Boolean flag = con.prepareStatement("INSERT into workers (name,phonenumber) values ('"+worker.getName()+"','"+worker.getPhoneNumber()+"');").execute();
    }

    public static  void UpdateWorker(Worker worker) throws SQLException {
        String sql = "UPDATE workers SET name = '"+worker.getName()+"' , phonenumber = '"+worker.getPhoneNumber()+"' WHERE id = "+worker.getId()+";";
        PreparedStatement st = con.prepareStatement(sql);
            int flag = st.executeUpdate();
    }

    public static void DeleteWorker(Worker worker) throws SQLException {
        String sql = "DELETE FROM workers WHERE id = '"+worker.getId()+"';";
        PreparedStatement st = con.prepareStatement(sql);
        int rowsDeleted = st.executeUpdate();
    }

    public static Worker check(Worker worker) throws SQLException {
        Worker wr = new Worker();
        String sql = "select * from workers where id = '"+worker.getId()+"';";
        ResultSet rs = con.prepareStatement(sql).executeQuery();
        if (worker.getPassWorker().equals(rs.getString("password"))){
            return wr = new Worker(worker.getId(),worker.getName(),worker.getPhoneNumber(),"");
        }
        else return wr = new Worker("0","0","0","");
    }
}

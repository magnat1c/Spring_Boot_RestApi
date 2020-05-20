package com.Sagdatov.RestAPI.controller;

import com.Sagdatov.RestAPI.dao.WorkersDAO;
import com.Sagdatov.RestAPI.classesDB.Worker;
import com.Sagdatov.RestAPI.exception.NotFoundException;
import com.fasterxml.jackson.databind.annotation.NoClass;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class WorkersController {
    Gson gson = new Gson();


    @RequestMapping(value = "/workers",
            method = RequestMethod.GET)
    @ResponseBody
    public List<Worker> list(){
        List<Worker> workers = new ArrayList<>();

        {
            try {
                workers = WorkersDAO.GetAllWorkers();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return workers;
    }

    @RequestMapping(value = "/workers/{id}", //
            method = RequestMethod.GET)
    @ResponseBody
    public Worker getWorker(@PathVariable("id") String id) throws SQLException {
        Worker wr = WorkersDAO.GetWorker(id);
        if(wr.getId()!=""){return wr;}
        return new Worker("0","0","0","");

    }



    @RequestMapping(value = "/workers",
            method = RequestMethod.POST)
    @ResponseBody
    public Worker create(@RequestBody String workerStr) throws SQLException {
        Worker worker = gson.fromJson(workerStr, Worker.class);
        WorkersDAO.AddWorker(worker);
        return worker;
    }

    @RequestMapping(value = "/workers/{id}", //
            method = RequestMethod.PUT)
    @ResponseBody
    public Worker update(@PathVariable String id, @RequestBody String workerStr) throws SQLException {
        Worker workerFromDB = getWorker(id);
        Worker worker = gson.fromJson(workerStr, Worker.class);
        worker.setId(workerFromDB.getId());
        if(worker.getName()==null){
            worker.setName(workerFromDB.getName());
        }
        if(worker.getPhoneNumber()==null){
            worker.setPhoneNumber(workerFromDB.getPhoneNumber());
        }
        WorkersDAO.UpdateWorker(worker);
        return worker;
    }

    @RequestMapping(value = "/workers/{id}",
            method = RequestMethod.DELETE)
    @ResponseBody
    public Worker delete(@PathVariable("id") String id) throws SQLException {
        Worker worker = getWorker(id);
        WorkersDAO.DeleteWorker(worker);
        return worker;
    }



}

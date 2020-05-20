package com.Sagdatov.RestAPI.classesDB;

//Работник
public class Worker {

    String id;
    String name;
    String phoneNumber;
    String passWorker;

    @Override
    public String toString() {
        return "Worker{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", passWorker='" + passWorker + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassWorker() {
        return passWorker;
    }

    public void setPassWorker(String passWorker) {
        this.passWorker = passWorker;
    }

    public Worker(String id, String name, String phoneNumber, String passWorker) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.passWorker = passWorker;
    }

    public Worker(){};
}

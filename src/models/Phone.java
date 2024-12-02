package models;

import java.util.ArrayList;
import java.util.List;

public class Phone {


    private int id;
    private String name;
    private String brand;
    List<Contact> contacts = new ArrayList<>();

    public Phone(int id, String name, String brand, List<Contact> contacts) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.contacts = contacts;
    }



    public Phone(int id, String name, String brand) {
        this.id = id;
        this.name = name;
        this.brand = brand;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "\nPhone{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", contacts=" + contacts +
                '}';
    }
}

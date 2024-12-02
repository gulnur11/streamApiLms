package service;

import database.Database;
import models.Phone;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class PhoneServiceImpl implements PhoneService{


  Database database = new Database();



    @Override
    public String addPhone(Phone phone) {
        boolean exists = database.phones.stream()
                .anyMatch(p -> p.equals(phone));

        if (exists) {
            return "Phone already exists!";
        }

        database.phones.add(phone);
        return "Phone added successfully!";
    }




    @Override
    public Phone getPhoneById(int phoneId) {
        try {
            return database.phones.stream()
                    .filter(phone -> phone.getId() == phoneId)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Phone with ID " + phoneId + " not found!"));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }




      @Override
public Phone updatePhoneNameById(int phoneId, String newName) {
    try {
        Phone phone = database.phones.stream()
                .filter(p -> p.getId() == phoneId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Phone with ID " + phoneId + " not found!"));
        phone.setName(newName);
        return phone;
    } catch (RuntimeException e) {
        System.out.println(e.getMessage());
    }
    return null;
}



    @Override
    public List<Phone> getAllPhones() {
        return database.phones.stream().collect(Collectors.toList());
    }




@Override
public List<Phone> getAllPhonesByBrand(String brand) {
    List<Phone> phonesByBrand = new ArrayList<>();
    for (Phone phone : database.phones) {
        if (phone.getBrand().equalsIgnoreCase(brand)) {
            phonesByBrand.add(phone);
        }
    }
    if (phonesByBrand.isEmpty()) {
        System.out.println("No phones found for brand: " + brand);
        return List.of();
    }
    return phonesByBrand;
}






    @Override
    public void deletePhoneById(int phoneId) {
        try {
            Iterator<Phone> iterator = database.phones.iterator();
            while (iterator.hasNext()) {
                Phone phone = iterator.next();
                if (phone.getId() == phoneId) {
                    iterator.remove();
                    System.out.println("Phone deleted successfully!");
                    return;
                }
            }
            throw new RuntimeException("Phone with ID " + phoneId + " not found!");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }












}


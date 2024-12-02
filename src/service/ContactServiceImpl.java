package service;

import models.Contact;
import models.Phone;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ContactServiceImpl implements ContactService {


    private final PhoneService phoneService;


    public ContactServiceImpl(PhoneService phoneService) {
        this.phoneService = phoneService;
    }


    @Override
    public String addContactToPhone(int phoneId, Contact contact) {
        Phone phone = phoneService.getPhoneById(phoneId);
        if (phone == null) {
            return "Phone with ID " + phoneId + " not found!";
        }
        if (phone.getContacts().stream().anyMatch(c -> c.getName().equalsIgnoreCase(contact.getName()))) {
            return "Contact with name " + contact.getName() + " already exists!";
        }
        phone.getContacts().add(contact);
        return "Contact added successfully to phone with ID " + phoneId;
    }




    @Override
    public Contact findContactByName(int phoneId, String contactName) {
        Phone phone = phoneService.getPhoneById(phoneId);
        if (phone == null) {
            return null;
        }
        return phone.getContacts().stream()
                .filter(contact -> contact.getName().equalsIgnoreCase(contactName))
                .findFirst()
                .orElse(null);
    }





    // with stream (телефонду phoneId мн табып, ичинен контантактарды аттарын осуу тартибинде чыгарып берсин)

    @Override
    public Contact findContactByPhoneNumber(int phoneId, String phoneNumber) {
        Phone phone = phoneService.getPhoneById(phoneId);
        if (phone == null) {
            return null;
        }
        return phone.getContacts().stream()
                .filter(contact -> contact.getPhoneNumber().equals(phoneNumber))
                .sorted(Comparator.comparing(Contact::getName))
                .findFirst()
                .orElse(null);
    }




    @Override
    public List<Contact> sortContactsByName(int phoneId) {

        Phone phone = phoneService.getPhoneById(phoneId);
        if (phone == null) {
            return List.of();
        }
        List<Contact> contacts = phone.getContacts();
        Collections.sort(contacts, new Comparator<Contact>() {
            @Override
            public int compare(Contact c1, Contact c2) {
                return c1.getName().compareTo(c2.getName());
            }
        });
        return contacts;
    }




    @Override
    public void deleteContactByNameFromPhone(int phoneId, String contactName) {
        try {
            Phone phone = phoneService.getPhoneById(phoneId);
            if (phone == null) {
                throw new RuntimeException("Phone with ID " + phoneId + " not found!");
            }
            Iterator<Contact> iterator = phone.getContacts().iterator();
            boolean contactFound = false;

            while (iterator.hasNext()) {
                Contact contact = iterator.next();
                if (contact.getName().equalsIgnoreCase(contactName)) {
                    iterator.remove();
                    System.out.println("Contact " + contactName + " deleted successfully!");
                    contactFound = true;
                    break;
                }
            }
            if (!contactFound) {
                throw new RuntimeException("Contact with name " + contactName + " not found!");
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }






        }

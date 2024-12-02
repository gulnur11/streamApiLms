import database.GenerateId;
import models.Contact;
import models.Phone;
import service.ContactService;
import service.ContactServiceImpl;
import service.PhoneService;
import service.PhoneServiceImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        PhoneService phoneService = new PhoneServiceImpl();
        ContactService contactService = new ContactServiceImpl(phoneService);


        try {
            while (true) {

                System.out.println("1.Add phone: ");
                System.out.println("2.Get phone By Id: ");
                System.out.println("3.Update Phone Name By Id: ");
                System.out.println("4.Get all phones: ");
                System.out.println("5.Get All Phones By Brand: ");
                System.out.println("6.Delete phone by Id: ");
                System.out.println("7.Add Contact To Phone: ");
                System.out.println("8.Find Contact By Name: ");
                System.out.println("9.Find Contact By PhoneNumber: ");
                System.out.println("10.Sort Contacts By Name: ");
                System.out.println("11.Delete Contact By Name From Phone: ");
                System.out.println("SELECT THE METHOD: ");
                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();

                if (choice == 12) {
                    System.out.println("EXIT ! ");
                    break;
                }

                switch (choice) {
                    case 1 -> {
                        System.out.println("Write phone's name: ");
                        scanner.nextLine();
                        String name = scanner.nextLine();
                        System.out.println("Write phone's brand: ");
                        String brand = scanner.nextLine();
                        Phone phone = new Phone(GenerateId.getPhoneId(), name, brand);
                        System.out.println(phone);
                        System.out.println(phoneService.addPhone(phone));
                    }
                    case 2 -> {
                        System.out.println("Write ID ");
                        int phoneID = scanner.nextInt();
                        System.out.println(phoneService.getPhoneById(phoneID));
                    }
                    case 3 -> {
                        System.out.println("Write ID ");
                        int phoneID = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Write phone's new name: ");
                        String newName = scanner.nextLine();
                        System.out.println(phoneService.updatePhoneNameById(phoneID, newName));
                    }
                    case 4 -> {
                        System.out.println(phoneService.getAllPhones());
                    }
                    case 5 -> {
                        System.out.println("Write phone's brand: ");
                        scanner.nextLine();
                        String brand = scanner.nextLine();
                        System.out.println(phoneService.getAllPhonesByBrand(brand));
                    }
                    case 6 -> {
                        System.out.println("Write ID ");
                        int phoneID = scanner.nextInt();
                        phoneService.deletePhoneById(phoneID);
                    }
                    case 7 -> {
                        System.out.println("Write phone's ID ");
                        int phoneID = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Write contact's name: ");
                        String name = scanner.nextLine();
                        System.out.println("Write phone number: ");
                        String phoneNumber = scanner.nextLine();
                        Contact contact = new Contact(name, phoneNumber);
                        System.out.println(contact);
                        System.out.println(contactService.addContactToPhone(phoneID, contact));
                    }
                    case 8 -> {
                        System.out.println("Write ID ");
                        int phoneID = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Write contact's name: ");
                        String name = scanner.nextLine();
                        System.out.println(contactService.findContactByName(phoneID, name));
                    }
                    case 9 -> {
                        System.out.println("Write ID ");
                        int phoneID = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Write phone number: ");
                        String phoneNumber = scanner.nextLine();
                        System.out.println(contactService.findContactByPhoneNumber(phoneID, phoneNumber));
                    }
                    case 10 -> {
                        System.out.println("Write ID ");
                        int phoneID = scanner.nextInt();
                        System.out.println(contactService.sortContactsByName(phoneID));
                    }
                    case 11 -> {
                        System.out.println("Write ID ");
                        int phoneID = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Write contact's name: ");
                        String name = scanner.nextLine();
                        contactService.deleteContactByNameFromPhone(phoneID, name);
                    }
                    default -> System.out.println("Invalid operation. Try again.");
                }
            }
        } catch (InputMismatchException e){
            System.out.println("Error: Write only Numerals!");
        }finally {
            System.out.println("Operation is over!");
        }






    }
}
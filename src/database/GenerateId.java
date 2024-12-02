package database;

public class GenerateId {

    public static int phoneId= 0;

    public static int getPhoneId(){
        return ++phoneId;
    }



}

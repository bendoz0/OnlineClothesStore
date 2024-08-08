package org.example.onlineclothesstore;

public class UserSingleton {
    private static UserSingleton instance;
    private String userLetter, userName;

    private UserSingleton() {}

    public static synchronized UserSingleton getInstance() {
        if (instance == null) {
            instance = new UserSingleton();
        }
        return instance;
    }

    public void setUserName(String userName) {
        // Le successiva riga serve per prendere la prima lettera e renderla maiuscola
        char firstLetter = Character.toUpperCase(userName.charAt(0));
        //userName contiene la prima lettera maiuscola e il resto della stringa di "userName"
        this.userName = firstLetter + userName.substring(1);
        this.userLetter = firstLetter + "";
    }
    public String getUserLetter() {
        return userLetter;
    }
    public String getUserName() {
        return userName;
    }
    public void restUser(){
        userLetter = null;
        userName = null;
    }
}

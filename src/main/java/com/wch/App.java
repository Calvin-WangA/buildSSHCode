package com.wch;

/**
 * Hello world!
 */
public class App {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    private String passwrd;

    public String getPasswrd() {
        return passwrd;
    }

    public void setPasswrd(String passwrd) {
        this.passwrd = passwrd;
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        App app = new App();
        app.setName("Calvin Wang");

        System.out.println(app.getName());

        System.out.println("I want to get a lot of Money.");

        System.out.println("The hengtiansoft is my opportulity!, got it!");

    }
}

package com.axlsreborn;

public class Person {

    private final String firstName;
    private final String lastName;

    private Person(String newFirstName, String newLastName) {
        firstName = newFirstName;
        lastName = newLastName;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    static class Builder {
        private String newFirstName;
        public Person build() {
            Person result = new Person(newFirstName, "Bob");
            System.out.println("Builder.build(): result = [" + result + "]");
            return result;
        }
    }
}

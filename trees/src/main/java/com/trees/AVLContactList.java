package com.trees;

import java.util.List;

public class AVLContactList {
    public static void main(String[] args) {
        ContactList cl = new ContactList();

        // Adding contacts
        cl.addContact(new Contact("Alice", "alice@example.com", "123-456-7890"));
        cl.addContact(new Contact("Bob", "bob@example.com", "234-567-8901"));
        cl.addContact(new Contact("Charlie", "charlie@example.com", "345-678-9012"));
        cl.addContact(new Contact("David", "david@example.com", "456-789-0123"));

        System.out.println("Contacts added.\n");
        
        // Searching for a contact by name
        String searchName = "Bob";
        Contact foundContact = cl.getClosestContact(searchName);
        if (foundContact != null && foundContact.getName().equals(searchName)) {
            System.out.println("Found contact: " + foundContact);
        } else {
            System.out.println("Contact not found: " + searchName);
        }
        System.out.println();

        // Deleting a contact by name
        cl.deleteContact("Charlie");
        System.out.println("Deleted contact: Charlie\n");

        // Display all contacts in alphabetical order
        System.out.println("All Contacts (Alphabetical Order):");
        List<Contact> allContacts = cl.getAllContacts();
        allContacts.sort((c1, c2) -> c1.getName().compareTo(c2.getName())); // Sorting manually
        for (Contact c : allContacts) {
            System.out.println(c);
        }
        System.out.println();

        // Retrieve contacts starting with a specific letter
        char letter = 'A';
        System.out.println("Contacts starting with '" + letter + "':");
        List<Contact> contactsByLetter = cl.getAllContactsBeginningWith(letter);
        for (Contact c : contactsByLetter) {
            System.out.println(c);
        }
        System.out.println();

        // Retrieve contacts containing a specific string
        String searchString = "o";
        System.out.println("Contacts containing '" + searchString + "':");
        List<Contact> matchingContacts = cl.getMatchingContacts(searchString);
        for (Contact c : matchingContacts) {
            System.out.println(c);
        }
    }
}

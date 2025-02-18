package com.trees;

import java.util.ArrayList;
import java.util.List;

import com.trees.TreeNodes.BinarySearchTreeNode;

public class ContactList {
    private AvlTree<Contact> contacts;

    ContactList() {
        contacts = new AvlTree<>();
    }

    public void addContact(Contact contact) {
        // ToDo:
        if (contact != null) {
            contacts.insert(contact);
        }
    }

    public void deleteContact(String name) {
        // ToDo:
        Contact removeContact = contacts.find(new Contact(name, "", ""));
        if (removeContact != null) {
            contacts.remove(removeContact);
        }
    }

    public List<Contact> getAllContacts() {
        // ToDo:
        List<Contact> result = new ArrayList<>();
        for (BinarySearchTreeNode<Contact> node : contacts.preOrderTraverse()) {
            result.add(node.getValue());
        }
        return result;
    }

    public List<Contact> getAllContactsBeginningWith(char letter) {
        // ToDo:        
        List<Contact> result = new ArrayList<>();
        for (BinarySearchTreeNode<Contact> node : contacts.preOrderTraverse()) {
            if (node.getValue().getName().charAt(0) == letter) {
                result.add(node.getValue());
            }
        }
        return result;
    }

    public List<Contact> getMatchingContacts(String searchString) {
        // ToDo:
        List<Contact> result = new ArrayList<>();
        for (BinarySearchTreeNode<Contact> node : contacts.preOrderTraverse()) {
            if (node.getValue().getName().contains(searchString)) {
                result.add(node.getValue());
            }
        }
        return result;
    }

    public Contact getClosestContact(String name) {
        return contacts.findClosest(new Contact(name, "", ""));
    }
}

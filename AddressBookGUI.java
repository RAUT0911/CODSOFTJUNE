package addressbookgui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Contact {
    private String name;
    private String phoneNumber;
    private String emailAddress;

    public Contact(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}

class AddressBook {
    private List<Contact> contacts;

    public AddressBook() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
    }

    public Contact searchContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null;
    }

    public List<Contact> getAllContacts() {
        return contacts;
    }
}

public class AddressBookGUI extends JFrame {
    private JTextField nameTextField;
    private JTextField phoneNumberTextField;
    private JTextField emailAddressTextField;
    private JTextArea contactListTextArea;
    private AddressBook addressBook;

    public AddressBookGUI() {
        addressBook = new AddressBook();

        setTitle("Address Book");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        createComponents();

        pack();
    }

    private void createComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        JLabel nameLabel = new JLabel("Name:");
        nameTextField = new JTextField();
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberTextField = new JTextField();
        JLabel emailAddressLabel = new JLabel("Email Address:");
        emailAddressTextField = new JTextField();

        inputPanel.add(nameLabel);
        inputPanel.add(nameTextField);
        inputPanel.add(phoneNumberLabel);
        inputPanel.add(phoneNumberTextField);
        inputPanel.add(emailAddressLabel);
        inputPanel.add(emailAddressTextField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addButton = new JButton("Add Contact");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addContact();
            }
        });
        JButton displayButton = new JButton("Display All Contacts");
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAllContacts();
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(displayButton);

        JPanel listPanel = new JPanel(new BorderLayout());
        JLabel contactListLabel = new JLabel("Contact List:");
        contactListTextArea = new JTextArea(10, 30);
        contactListTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(contactListTextArea);

        listPanel.add(contactListLabel, BorderLayout.NORTH);
        listPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(listPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void addContact() {
        String name = nameTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        String emailAddress = emailAddressTextField.getText();

        if (name.isEmpty() || phoneNumber.isEmpty() || emailAddress.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter all contact details.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Contact contact = new Contact(name, phoneNumber, emailAddress);
        addressBook.addContact(contact);

        nameTextField.setText("");
        phoneNumberTextField.setText("");
        emailAddressTextField.setText("");

        JOptionPane.showMessageDialog(this, "Contact added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void displayAllContacts() {
        List<Contact> contacts = addressBook.getAllContacts();
        if (contacts.isEmpty()) {
            contactListTextArea.setText("No contacts found.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Contact contact : contacts) {
                sb.append("Name: ").append(contact.getName()).append("\n");
                sb.append("Phone Number: ").append(contact.getPhoneNumber()).append("\n");
                sb.append("Email Address: ").append(contact.getEmailAddress()).append("\n");
                sb.append("----------\n");
            }
            contactListTextArea.setText(sb.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AddressBookGUI addressBookGUI = new AddressBookGUI();
                addressBookGUI.setVisible(true);
            }
        });
    }
}

package com.contact.main;

import java.util.Scanner;
import java.util.List;

import com.contact.model.Contact;
import com.contact.service.ContactManager;
import com.contact.user.User;

public class MainContact {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ContactManager manager = new ContactManager();

        while (true) {

            System.out.println("\n==== CONTACT APPLICATION ====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Select Option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            // ================= REGISTER =================
            if (choice == 1) {

                System.out.println("\n--- User Registration ---");
                System.out.println("1. Free User");
                System.out.println("2. Premium User");
                System.out.print("Select User Type: ");

                int type = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Email: ");
                String email = sc.nextLine();

                System.out.print("Enter Password: ");
                String password = sc.nextLine();

                manager.registerUser(type, name, email, password);

            }

            // ================= LOGIN =================
            else if (choice == 2) {

                System.out.println("\n--- Login ---");
                System.out.println("1. Basic Login");
                System.out.println("2. OAuth Login");
                System.out.print("Select Login Type: ");

                int loginType = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter Email: ");
                String email = sc.nextLine();

                User loggedInUser = null;

                if (loginType == 1) {

                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();

                    loggedInUser = manager.loginBasic(email, password);

                } else if (loginType == 2) {

                    System.out.print("Enter OAuth Token: ");
                    String token = sc.nextLine();

                    loggedInUser = manager.loginOAuth(email, token);
                }

                // ================= AFTER LOGIN =================
                if (loggedInUser != null) {

                    while (true) {

                        System.out.println("\n--- Welcome " + loggedInUser.getName() + " ---");
                        System.out.println("1. Update Name");
                        System.out.println("2. Change Password");
                        System.out.println("3. Add Contact");
                        System.out.println("4. View All Contacts");
                        System.out.println("5. View Contact By ID");
                        System.out.println("6. Edit Contact");
                        System.out.println("7. Delete Contact");
                        System.out.println("8. Bulk Delete by Email Domain");
                        System.out.println("9. Bulk Update Phone Prefix");
                        System.out.println("10. Bulk Export (Last 5 Minutes)");
                        System.out.println("11. Search by Name");
                        System.out.println("12. Search by Phone");
                        System.out.println("13. Search by Email");
                        System.out.println("14. Logout");

                        int opt = sc.nextInt();
                        sc.nextLine();

                        if (opt == 1) {
                            System.out.print("Enter New Name: ");
                            String newName = sc.nextLine();
                            loggedInUser.setName(newName);
                            System.out.println("Name Updated Successfully!");

                        } else if (opt == 2) {
                            System.out.print("Enter New Password: ");
                            String newPass = sc.nextLine();
                            loggedInUser.setPassword(newPass);
                            System.out.println("Password Updated Successfully!");

                        } else if (opt == 3) {
                            System.out.print("Enter Contact Name: ");
                            String cname = sc.nextLine();

                            System.out.print("Enter Phone: ");
                            String phone = sc.nextLine();

                            System.out.print("Enter Email: ");
                            String cemail = sc.nextLine();

                            loggedInUser.addContact(new Contact(cname, phone, cemail));
                            System.out.println("Contact Added Successfully!");

                        } else if (opt == 4) {
                            loggedInUser.viewContacts();

                        } else if (opt == 5) {
                            System.out.print("Enter Contact ID: ");
                            String id = sc.nextLine();
                            loggedInUser.viewContactById(id);

                        }else if (opt == 6) {

                            System.out.print("Enter Contact ID: ");
                            String id = sc.nextLine();

                            System.out.print("Enter New Name: ");
                            String newName = sc.nextLine();

                            System.out.print("Enter New Phone (10 digits): ");
                            String newPhone = sc.nextLine();

                            System.out.print("Enter New Email: ");
                            String newEmail = sc.nextLine();

                            loggedInUser.editContact(id, newName, newPhone, newEmail);
                        } else if (opt == 7) {

                            System.out.print("Enter Contact ID to Delete: ");
                            String id = sc.nextLine();

                            loggedInUser.deleteContactById(id);
                        }else if (opt == 8) {

                            System.out.print("Enter Email Domain (example: @gmail.com): ");
                            String domain = sc.nextLine();

                            loggedInUser.bulkDeleteByEmailDomain(domain);

                        }
                        else if (opt == 9) {

                            System.out.print("Enter Old Phone Prefix (example: 98): ");
                            String oldPrefix = sc.nextLine();

                            System.out.print("Enter New Phone Prefix (example: 99): ");
                            String newPrefix = sc.nextLine();

                            loggedInUser.bulkUpdatePhonePrefix(oldPrefix, newPrefix);

                        }
                        else if (opt == 10) {

                            System.out.println("Exporting contacts created in last 5 minutes...");

                            java.util.List<com.contact.model.Contact> exported =
                                    loggedInUser.bulkExportAfter(java.time.LocalDateTime.now().minusMinutes(5));

                            for (com.contact.model.Contact c : exported) {
                                c.display();
                                System.out.println("--------------------");
                            }

                        }else if (opt == 11) {

                            System.out.print("Enter Name to Search: ");
                            String name = sc.nextLine();

                            List<Contact> results = loggedInUser.searchByName(name);

                            if (results.isEmpty()) {
                                System.out.println("No contacts found!");
                            } else {
                                for (Contact c : results) {
                                    c.display();
                                    System.out.println("--------------------");
                                }
                            }
                        }else if (opt == 12) {

                            System.out.print("Enter Phone to Search: ");
                            String phone = sc.nextLine();

                            List<Contact> results = loggedInUser.searchByPhone(phone);

                            if (results.isEmpty()) {
                                System.out.println("No contacts found!");
                            } else {
                                for (Contact c : results) {
                                    c.display();
                                    System.out.println("--------------------");
                                }
                            }
                        }else if (opt == 13) {

                            System.out.print("Enter Email to Search: ");
                            String emailSearch = sc.nextLine();

                            List<Contact> results = loggedInUser.searchByEmail(emailSearch);

                            if (results.isEmpty()) {
                                System.out.println("No contacts found!");
                            } else {
                                for (Contact c : results) {
                                    c.display();
                                    System.out.println("--------------------");
                                }
                            }
                        }
                        else if (opt == 14) {

                            System.out.println("Logged Out Successfully!");
                            break;
                        }
                    }

                } else {
                    System.out.println("Login Failed!");
                }
            }

            // ================= EXIT =================
            else if (choice == 3) {
                System.out.println("Thank You for using Contact App!");
                break;
            }
        }

        sc.close();
    }
}
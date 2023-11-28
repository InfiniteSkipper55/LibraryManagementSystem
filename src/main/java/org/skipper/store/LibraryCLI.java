package org.skipper.store;

import java.util.List;
import java.util.Scanner;

public class LibraryCLI {
    private Library library;
    private final Scanner scanner;

    public LibraryCLI() {
        library = new Library();
        scanner = new Scanner(System.in);
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            System.out.println("=== Library Management System ===");
            System.out.println("1. Add a Book");
            System.out.println("2. Find a Book by ID");
            System.out.println("3. Search Books");
            System.out.println("4. Update Book Information");
            System.out.println("5. Remove a Book");
            System.out.println("6. Add a User");
            System.out.println("7. Find a User by ID");
            System.out.println("8. Update User Information");
            System.out.println("9. Remove a User");
            System.out.println("10. Issue Book");
            System.out.println("11. Return Book");
            System.out.println("12. Calculate Fine");
            System.out.println("13. Accept Fine Payment");
            System.out.println("14. Serialize Data");
            System.out.println("15. Deserialize Data");
            System.out.println("16. Display Deserialized Library Data");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> library.addBook();
                case 2 -> {
                    System.out.print("Enter Book ID: ");
                    int bookID = scanner.nextInt();
                    scanner.nextLine();
                    Book foundBook = library.findBookById(bookID);
                    if (foundBook != null) {
                        System.out.println("Book found: " + foundBook);
                    } else {
                        System.out.println("Book not found.");
                    }
                }
                case 3 -> {
                    System.out.print("Enter search keyword: ");
                    String keyword = scanner.nextLine();
                    List<Book> searchResults = library.searchBooks(keyword);
                    if (!searchResults.isEmpty()) {
                        System.out.println("Search Results:");
                        for (Book book : searchResults) {
                            System.out.println(book);
                        }
                    } else {
                        System.out.println("No books found matching the search criteria.");
                    }
                }
                case 4 -> {
                    System.out.print("Enter Book ID: ");
                    int updateBookID = scanner.nextInt();
                    scanner.nextLine();
                    Book updateBook = library.findBookById(updateBookID);
                    if (updateBook != null) {
                        library.updateBook(updateBook);
                    } else {
                        System.out.println("Book not found.");
                    }
                }
                case 5 -> {
                    System.out.print("Enter Book ID: ");
                    int removeBookID = scanner.nextInt();
                    scanner.nextLine();
                    Book removeBook = library.findBookById(removeBookID);
                    if (removeBook != null) {
                        library.removeBook(removeBook);
                        System.out.println("Book removed successfully.");
                    } else {
                        System.out.println("Book not found.");
                    }
                }
                case 6 -> library.addUser();
                case 7 -> {
                    System.out.print("Enter User ID: ");
                    int userID = scanner.nextInt();
                    scanner.nextLine();
                    User foundUser = library.findUserById(userID);
                    if (foundUser != null) {
                        System.out.println("User found: " + foundUser);
                    } else {
                        System.out.println("User not found.");
                    }
                }
                case 8 -> {
                    System.out.print("Enter User ID: ");
                    int updateUserID = scanner.nextInt();
                    scanner.nextLine();
                    User updateUser = library.findUserById(updateUserID);
                    if (updateUser != null) {
                        library.updateUser(updateUser);
                    } else {
                        System.out.println("User not found.");
                    }
                }
                case 9 -> {
                    System.out.print("Enter User ID: ");
                    int removeUserID = scanner.nextInt();
                    scanner.nextLine();
                    User removeUser = library.findUserById(removeUserID);
                    if (removeUser != null) {
                        library.removeUser(removeUser);
                        System.out.println("User removed successfully.");
                    } else {
                        System.out.println("User not found.");
                    }
                }
                case 10 -> {
                    System.out.println("=== Issue Book ===");
                    System.out.print("Enter Book ID: ");
                    int issueBookID = scanner.nextInt();
                    scanner.nextLine();
                    Book issueBook = library.findBookById(issueBookID);

                    System.out.print("Enter User ID: ");
                    int issueUserID = scanner.nextInt();
                    scanner.nextLine();
                    User issueUser = library.findUserById(issueUserID);

                    if (issueBook != null && issueUser != null) {
                        // Check if the book is available
                        if (library.isBookAvailable(issueBook)) {
                            // Issue the book to the user
                            library.issueBook(issueBook, issueUser);
                            System.out.println("Book issued successfully.");
                        } else {
                            System.out.println("Book is not available for issuing.");
                        }
                    } else {
                        System.out.println("Book or User not found.");
                    }
                }
                case 11 -> {
                    System.out.println("=== Return a Book ===");
                    System.out.print("Enter the book ID: ");
                    int bookID = scanner.nextInt();

                    Book bookToReturn = library.findBookById(bookID);
                    if (bookToReturn != null) {
                        User currentUser = bookToReturn.getBorrower();
                        library.returnBook(bookToReturn, currentUser);
                        System.out.println("Book returned successfully.");
                    } else {
                        System.out.println("Book not found.");
                    }
                }
                case 12 -> {
                    System.out.println("=== Calculate Fine ===");
                    System.out.print("Enter the book ID: ");
                    int bookID = scanner.nextInt();

                    Book bookToCalculateFine = library.findBookById(bookID);
                    if (bookToCalculateFine != null) {
                        double fineAmount = library.calculateFine(bookToCalculateFine);
                        System.out.println("Fine amount: " + fineAmount);
                    } else {
                        System.out.println("Book not found.");
                    }
                }
                case 13 -> {
                    System.out.println("=== Accept Fine Payment ===");
                    System.out.print("Enter User ID: ");
                    int userID = scanner.nextInt();

                    System.out.print("Enter Fine Amount: ");
                    double fineAmount = scanner.nextDouble();

                    User user = library.findUserById(userID);
                    if (user != null) {
                        library.acceptFinePayment(user, fineAmount);
                    } else {
                        System.out.println("User not found.");
                    }
                }
                case 14 -> {
                    System.out.println("=== Serialize Library ===");
                    System.out.print("Enter the filename for serialization: ");
                    String filename = scanner.nextLine();
                    try {
                        library.serializeLibrary(filename);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                case 15 -> {
                    System.out.println("=== Deserialize Library ===");
                    System.out.print("Enter the filename for deserialization: ");
                    String filename = scanner.nextLine();
                    Library deserializedLibrary = Library.deserializeLibrary(filename);
                    if (deserializedLibrary != null) {
                        library = deserializedLibrary;
                    }
                }
                case 16 -> {
                    System.out.println("=== Deserialized Library Data ===");
                    library.displayDeserializedData();
                }
                case 0 -> {
                    exit = true;
                    System.out.println("Exiting Library Management System.");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }

            System.out.println();
        }
    }
}


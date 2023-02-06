/*
Copyright 2023 Heather Tweedie

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package rht.bookdb;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class BookCommander {
    private final BookInterface bookDB;

    public BookCommander(BookInterface bookDB) {
        this.bookDB = bookDB;
    }

    public static void main(String args[]) {
        try (Scanner sc = new Scanner(System.in)) {
            BookInterface bookDB = new SQLBookDB("books.sqlite");
            BookCommander commander = new BookCommander(bookDB);
            commander.mainMenu(sc);

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void mainMenu(Scanner sc) throws IOException, SQLException {
        while (true) {
            System.out.println("What would you like to do today?");
            System.out.println("0: Quit");
            System.out.println("1: List all books");
            System.out.println("2: Add a book");
            System.out.println("3: Edit a book");
            System.out.println("4: Remove a book");
            System.out.print("Option: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 0:
                    return;
                case 1:
                    listAllBooks();
                    break;
                case 2:
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    addBook(title, author);
                    break;
                case 3:
                    System.out.print("What is the ID of the book to be edited? ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.print("New title: ");
                    String newTitle = sc.nextLine();
                    System.out.print("New author: ");
                    String newAuthor = sc.nextLine();
                    editBook(id, newTitle, newAuthor);
                    break;
                case 4:
                    System.out.println("Which book would you like to remove?");
                    int removeID = Integer.parseInt(sc.nextLine());
                    removeBook(removeID);
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

    }

    private void listAllBooks() throws IOException, SQLException {
        ArrayList<Book> books = bookDB.getAllBooks();
        for (Book book : books) {
            System.out.println(book.getID() + ": " + book.getTitle() + " by " + book.getAuthor());
        }
    }

    private void addBook(String title, String author) throws IOException, SQLException {
        bookDB.addBook(title, author);
        System.out.println("Added " + title + " by " + author);
    }

    private void editBook(int id, String newTitle, String newAuthor) throws IOException, SQLException {
        if (bookDB.editBook(id, newTitle, newAuthor)) {
            System.out.println("Changed entry " + id + " to " + newTitle + " by " + newAuthor + ".");
        } else {
            System.out.println("No book found with ID " + id + ".");
        }
    }

    private void removeBook(int id) throws IOException, SQLException {
        bookDB.removeBook(id);
        System.out.println("Removed entry " + id + ".");
    }
}

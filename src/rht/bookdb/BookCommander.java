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
                    System.out.print("What is the title of the book to be edited? ");
                    String oldTitle = sc.nextLine();
                    System.out.print("New title: ");
                    String newTitle = sc.nextLine();
                    System.out.print("New author: ");
                    String newAuthor = sc.nextLine();
                    editBook(oldTitle, newTitle, newAuthor);
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
    }

    private void editBook(String oldTitle, String newTitle, String newAuthor) throws IOException, SQLException {
        bookDB.editBook(oldTitle, newTitle, newAuthor);
    }
}

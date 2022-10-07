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

            int choice = sc.nextInt();

            switch (choice) {
                case 0:
                    return;
                case 1:
                    listAllBooks();
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

    }

    private void listAllBooks() throws IOException, SQLException {
        ArrayList<Book> books = bookDB.getAllBooks();
        for (Book book : books) {
            System.out.println(book.getTitle() + " by " + book.getAuthor());
        }
    }
}

package rht.bookdb;

import java.util.ArrayList;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
import java.util.Scanner;

public class TextFileBookDatabase implements BookInterface {

    private String fileName;

    public TextFileBookDatabase(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void addBook(String title, String author) throws IOException {
        String stringToAdd = title + ", " + author + "\n";
        Files.write(Paths.get(fileName), stringToAdd.getBytes(), StandardOpenOption.APPEND);
    }

    @Override
    public void editBook(String oldTitle, String newTitle, String newAuthor) {
    }

    public String[] searchBooks() {
        return null;
    }

    @Override
    public ArrayList<Book> getAllBooks() throws IOException {

        ArrayList<Book> books = new ArrayList<Book>();

        File bookFile = new File(fileName);
        Scanner sc = new Scanner(bookFile);
        while (sc.hasNextLine()) {
            String bookData = sc.nextLine();
            String[] bookDataSplit = bookData.split(",");
            String title = bookDataSplit[0];
            String author = bookDataSplit[1];
            Book book = new Book(title, author);
            books.add(book);
        }
        sc.close();

        return books;

    }

}

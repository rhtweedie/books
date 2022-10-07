import java.util.ArrayList;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TextFileBookDatabase implements BookInterface {

    private String fileName;

    public TextFileBookDatabase(String fileName) {
        this.fileName = fileName;
    }

    public void addBook(String title, String author) {
        String stringToAdd = title + ", " + author + "\n";
        try {
            Files.write(Paths.get(fileName), stringToAdd.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }

    public void editBook(String oldTitle, String newTitle, String newAuthor) {
    }

    public String[] searchBooks() {
        return null;
    }

    public ArrayList<Book> getAllBooks() {

        ArrayList<Book> books = new ArrayList<Book>();

        try {
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
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }

        return books;

    }

}

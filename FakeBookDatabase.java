import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FakeBookDatabase {

    HashMap<Integer, Book> bookList = new HashMap<Integer, Book>();

    public void addBook(Integer ID, Book Booklist) {
        bookList.put(ID, Booklist);
    }

    public void editBook(String oldTitle, String newTitle, String newAuthor) {
        bookList.remove(oldTitle);
        bookList.put(newTitle, newAuthor);
    }

    public String[] searchBooks() {
        return null;
    }

    public Book[] getAllBooks() {
        try {
            File bookFile = new File("booklist.txt");
            Scanner reader = new Scanner(bookFile);
            int count = 1;
            while (reader.hasNextLine()) {
                String bookData = reader.nextLine();
                String[] bookDataSplit = bookData.split(",");
                String title = bookDataSplit[0];
                String author = bookDataSplit[1];
                Book book = new Book(title, author);
                bookList.put(count, book);
                count += 1;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }

        Book[] books = (bookList.values()).toArray(new Book[bookList.size()]);
        return books;

    }

}

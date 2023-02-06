package rht.bookdb;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

interface BookInterface {

    /**
     * Adds a new book to the database.
     * 
     * @param title  The title of the book
     * @param author The author of the book
     */
    public void addBook(String title, String author) throws IOException, SQLException;

    /**
     * Edits an entry in the database.
     * 
     * @param oldTitle  The current title of the book entry to be changed
     * @param newTitle  The new title of the book
     * @param newAuthor The new author of the book
     */
    public void editBook(int id, String newTitle, String newAuthor) throws IOException, SQLException;

    public void removeBook(int rowid) throws IOException, SQLException;

    public String[] searchBooks();

    /**
     * Gets all the books in the database.
     */
    public ArrayList<Book> getAllBooks() throws IOException, SQLException;
}

package rht.bookdb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLBookDB implements BookInterface {

    public Connection con = null;

    public SQLBookDB(String filename) throws IOException, SQLException {

        con = DriverManager.getConnection("jdbc:sqlite:" + filename);
        System.out.println("Connected to database " + filename + ".");

        String createTable = "CREATE TABLE IF NOT EXISTS books (\n"
                + "                 title text NOT NULL, \n"
                + "                 author text\n"
                + ");";

        try (Statement stmt = con.createStatement()) {
            stmt.execute(createTable);
        }
    }

    @Override
    public void addBook(String title, String author) throws SQLException {
        String insert = "INSERT INTO books (title, author) VALUES (?,?)";
        PreparedStatement ps = null;
        int numRowsInserted = 0;

        ps = con.prepareStatement(insert);
        ps.setString(1, title);
        ps.setString(2, author);
        numRowsInserted = ps.executeUpdate();
        System.out.println("Added " + title + " by " + author);
        System.out.println("Number of rows inserted: " + numRowsInserted);
    }

    @Override
    public void editBook(String oldTitle, String newTitle, String newAuthor) throws SQLException {
        String update = "UPDATE books SET title = ? WHERE title = ? AND author = ?";
        PreparedStatement ps = null;

        ps = con.prepareStatement(update);
        ps.setString(1, newTitle);
        ps.setString(2, oldTitle);
        ps.setString(3, newAuthor);
        System.out.println("Changed entry for " + oldTitle + " to " + newTitle + " by " + newAuthor + ".");
    }

    @Override
    public String[] searchBooks() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<Book> getAllBooks() throws SQLException {
        String query = "SELECT rowid, title, author FROM books";
        ArrayList<Book> books = new ArrayList<Book>();

        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                Book book = new Book(title, author);
                books.add(book);
            }
        }

        return books;
    }

    public static void main(String[] args) {
        String filename = "bookDB";
        try {
            SQLBookDB bookDB = new SQLBookDB(filename);
            bookDB.addBook("Catch-22", "Joseph Heller");
            ArrayList<Book> books = bookDB.getAllBooks();
            System.out.println("--- All books contained in database ---");
            for (int i = 0; i < books.size(); i++) {
                System.out.println((books.get(i)).getTitle() + ", " + (books.get(i)).getAuthor());
            }
            bookDB.editBook("Catch-22", "The Honourable Schoolboy", "John le Carre");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}

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

    private final Connection con;

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
        PreparedStatement ps = con.prepareStatement("INSERT INTO books (title, author) VALUES (?,?)");
        ps.setString(1, title);
        ps.setString(2, author);
        int numRowsInserted = ps.executeUpdate();
        System.out.println("Added " + title + " by " + author);
        System.out.println("Number of rows inserted: " + numRowsInserted);
    }

    @Override
    public void editBook(String id, String newTitle, String newAuthor) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE books SET title = ?, author = ? WHERE rowid = ?");
        ps.setString(1, newTitle);
        ps.setString(2, newAuthor);
        ps.setString(3, id);
        int numRowsChanged = ps.executeUpdate();
        System.out.println("Changed entry " + id + " to " + newTitle + " by " + newAuthor + ".");
        System.out.println("Number of rows updated: " + numRowsChanged);
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
                int id = rs.getInt("rowid");
                String title = rs.getString("title");
                String author = rs.getString("author");
                Book book = new Book(id, title, author);
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

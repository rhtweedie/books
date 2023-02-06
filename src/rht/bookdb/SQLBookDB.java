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
    public void editBook(int id, String newTitle, String newAuthor) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE books SET title = ?, author = ? WHERE rowid = ?");
        ps.setString(1, newTitle);
        ps.setString(2, newAuthor);
        ps.setInt(3, id);
        int numRowsChanged = ps.executeUpdate();
        if (numRowsChanged == 0) {
            System.out.println("No book found with ID " + id + ".");
        } else {
            System.out.println("Changed entry " + id + " to " + newTitle + " by " + newAuthor + ".");
        }
    }

    @Override
    public void removeBook(int id) throws IOException, SQLException {
        PreparedStatement ps = con.prepareStatement("DELETE FROM books WHERE rowid = ?");
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("Removed entry " + id + ".");
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
}

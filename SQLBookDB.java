import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLBookDB implements BookInterface {

    public Connection con = null;

    public SQLBookDB(String filename) {

        try {
            con = DriverManager.getConnection("jdbc:sqlite:" + filename);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        System.out.println("Connected to database " + filename + ".");

        String createTable = "CREATE TABLE IF NOT EXISTS books (\n"
                + "                 title text NOT NULL, \n"
                + "                 author text\n"
                + ");";

        try (Statement stmt = con.createStatement()) {
            stmt.execute(createTable);
        } catch (SQLException e) {
            System.out.println(e.getClass().getName() + ":" + e.getMessage());
        }
    }

    public void addBook(String title, String author, String fileToAddTo) {
        String insert = "INSERT INTO " + fileToAddTo + " (title, author) VALUES (?,?)";
        System.out.println(insert);
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(insert);
            ps.setString(1, title);
            ps.setString(2, author);
            System.out.println("Added " + title + " by " + author);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editBook(String oldTitle, String newTitle, String newAuthor) {
        // TODO Auto-generated method stub

    }

    @Override
    public String[] searchBooks() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<Book> getAllBooks(String listToPrint) {
        String query = "SELECT * FROM " + listToPrint + ";";
        System.out.println(query);

        ArrayList<Book> books = new ArrayList<Book>();

        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            System.out.println(rs);
            while (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                Book book = new Book(title, author);
                books.add(book);
            }

        } catch (SQLException e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return books;
    }

    public static void main(String[] args) {
        String filename = "bookDB";
        SQLBookDB bookDB = new SQLBookDB(filename);
        bookDB.addBook("Catch-22", "Joseph Heller", "books");
        bookDB.getAllBooks("books");
    }
}

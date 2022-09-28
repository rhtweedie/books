import java.sql.Connection;
import java.sql.DriverManager;
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
    }

    public void addBook(String title, String author, String fileToAddTo) {
        String query = "INSERT INTO book.db VALUES (" + title + ", " + author + ")";

        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            System.out.println(rs);
        } catch (SQLException e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
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
        // TODO Auto-generated method stub
        return null;
    }

    public static void main(String[] args) {
        String filename = "books.db";
        SQLBookDB bookDB = new SQLBookDB(filename);
    }
}

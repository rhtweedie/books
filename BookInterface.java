import java.util.ArrayList;

interface BookInterface {

    /**
     * Adds a new book to the database.
     * 
     * @param title       The title of the book
     * @param author      The author of the book
     * @param fileToAddTo The file to which the book will be added
     */
    public void addBook(String title, String author);

    /**
     * Edits an entry in the database.
     * 
     * @param oldTitle  The current title of the book entry to be changed
     * @param newTitle  The new title of the book
     * @param newAuthor The new author of the book
     */
    public void editBook(String oldTitle, String newTitle, String newAuthor);

    public String[] searchBooks();

    /**
     * Gets all the books in the database.
     * 
     * @param listToPrint The list of books to be returned
     */
    public ArrayList<Book> getAllBooks();
}

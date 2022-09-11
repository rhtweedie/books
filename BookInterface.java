import java.util.HashMap;

interface BookInterface {

    HashMap<String, String> bookList = new HashMap<String, String>();

    public void addBook(String title, String author);

    public void editBook(String oldTitle, String newTitle, String newAuthor);

    public String[] searchBooks();
}

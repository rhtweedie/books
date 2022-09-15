import java.util.ArrayList;

public class PrintBooks {

    public static void main(String[] args) {
        BookInterface database = new TextFileBookDatabase();
        ArrayList<Book> books = database.getAllBooks(args[0]);
        System.out.println("--- All books contained in database ---");
        for (int i = 0; i < books.size(); i++) {
            System.out.println((books.get(i)).getTitle() + ", " + (books.get(i)).getAuthor());
        }
    }
}

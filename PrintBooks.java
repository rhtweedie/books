import java.util.ArrayList;

public class PrintBooks {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage:");
            System.err.println("  PrintBooks <path of file containing books to be printed>");
            System.exit(1);
        }

        try {
            BookInterface database = new TextFileBookDatabase(args[0]);
            ArrayList<Book> books = database.getAllBooks();
            System.out.println("--- All books contained in database ---");
            for (int i = 0; i < books.size(); i++) {
                System.out.println((books.get(i)).getTitle() + ", " + (books.get(i)).getAuthor());
            }
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }
}

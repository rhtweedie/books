public class PrintBooks {

    public static void main(String[] args) {
        Book[] books = FakeBookDatabase.getAllBooks();
        System.out.println("--- All books contained in database ---");
        for (int i = 0; i < books.length; i++) {
            System.out.println(books[i].getTitle() + ", " + books[i].getAuthor());
        }
    }
}

public class AddBook {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage:");
            System.err.println("  AddBook <title> <author>");
            System.exit(1);
        }

        BookInterface database = new FakeBookDatabase();
        database.addBook(args[0], args[1]);
    }

}

public class AddBook {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage:");
            System.err.println("  AddBook <title> <author> <file to which book will be added>");
            System.exit(1);
        }

        BookInterface database = new TextFileBookDatabase(args[2]);
        database.addBook(args[0], args[1]);
    }

}

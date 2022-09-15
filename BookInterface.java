interface BookInterface {

    /**
     * Adds a new book to the database.
     * 
     * @param title       The title of the book
     * @param author      The author of the book
     * @param fileToAddTo The file to which the book will be added
     */
    public void addBook(String title, String author, String fileToAddTo);

    public void editBook(String oldTitle, String newTitle, String newAuthor);

    public String[] searchBooks();

    /**
     * Gets all the books in the database.
     */
    public Book[] getAllBooks();
}

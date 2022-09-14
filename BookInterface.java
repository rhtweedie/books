interface BookInterface {

    /**
     * Adds a new book to the database.
     * 
     * @param title  The title of the book
     * @param author The author of the book
     */
    public void addBook(String title, String author);

    public void editBook(String oldTitle, String newTitle, String newAuthor);

    public String[] searchBooks();

    /**
     * Gets all the books in the database.
     */
    public Book[] getAllBooks();
}

interface BookInterface {

    public void addBook(String title, String author);

    public void editBook(String oldTitle, String newTitle, String newAuthor);

    public String[] searchBooks();
}

public class Owner extends User {

    public Owner(String username, String password) {
        super(username, password);
    }

    public void addBook(BookStore store, Book book) {
        store.addBook(book);
    }

    public void deleteBook(BookStore store, Book book) {
        store.removeBook(book);
    }

    public void addCustomer(BookStore store, Customer customer) {
        store.addCustomer(customer);
    }

    public void deleteCustomer(BookStore store, Customer customer) {
        store.removeCustomer(customer);
    }

    public Book[] getBooks(BookStore store) {
        return store.getBooks();
    }

    public Customer[] getCustomers(BookStore store) {
        return store.getCustomers();
    }
}
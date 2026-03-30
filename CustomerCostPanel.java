import java.io.*;
import java.util.ArrayList;

public class BookStore {

    private ArrayList<Book> books;
    private ArrayList<Customer> customers;

    public BookStore() {
        books = new ArrayList<>();
        customers = new ArrayList<>();
    }

    // ---------------- BOOK METHODS ----------------

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public Book[] getBooks() {
        return books.toArray(new Book[0]);
    }

    // ---------------- CUSTOMER METHODS ----------------

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }

    public Customer[] getCustomers() {
        return customers.toArray(new Customer[0]);
    }

    public Customer findCustomer(String username, String password) {
        for (Customer c : customers) {
            if (c.getUsername().equals(username) &&
                c.getPassword().equals(password)) {
                return c;
            }
        }
        return null;
    }

    // ---------------- FILE HANDLING ----------------

    public void loadData() {
        loadBooks();
        loadCustomers();
    }

    public void saveData() {
        saveBooks();
        saveCustomers();
    }

    // ---------- BOOK FILE ----------

    private void loadBooks() {
        books.clear();

        File file = new File("books.txt");
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 2) {
                    String name = parts[0];
                    double price = Double.parseDouble(parts[1]);

                    books.add(new Book(name, price));
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading books");
        }
    }

    private void saveBooks() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("books.txt"))) {
            for (Book b : books) {
                writer.println(b.getName() + "," + b.getPrice());
            }
        } catch (Exception e) {
            System.out.println("Error saving books");
        }
    }

    // ---------- CUSTOMER FILE ----------

    private void loadCustomers() {
        customers.clear();

        File file = new File("customers.txt");
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 3) {
                    String username = parts[0];
                    String password = parts[1];
                    int points = Integer.parseInt(parts[2]);

                    Customer c = new Customer(username, password);
                    c.setPoints(points);

                    customers.add(c);
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading customers");
        }
    }

    private void saveCustomers() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("customers.txt"))) {
            for (Customer c : customers) {
                writer.println(
                    c.getUsername() + "," +
                    c.getPassword() + "," +
                    c.getPoints()
                );
            }
        } catch (Exception e) {
            System.out.println("Error saving customers");
        }
    }
}
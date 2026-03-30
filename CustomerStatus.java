public class BookStoreApp {

    public static void main(String[] args) {
        BookStore store = new BookStore();
        store.loadData();

        Owner owner = new Owner("admin", "admin");

        MainFrame frame = new MainFrame(store, owner);
        frame.setVisible(true);
    }
}
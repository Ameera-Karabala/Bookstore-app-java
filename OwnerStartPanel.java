import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {

    private BookStore store;
    private Owner owner;
    private Customer currentCustomer;

    public MainFrame(BookStore store, Owner owner) {
        this.store = store;
        this.owner = owner;

        setTitle("BookStore App");
        setSize(700, 500);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                store.saveData();
                System.exit(0);
            }
        });

        showLoginPanel();
    }

    public BookStore getStore() {
        return store;
    }

    public Owner getStoreOwner() {
        return owner;
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

    public void showLoginPanel() {
        setContentPane(new LoginPanel(this));
        revalidate();
        repaint();
    }

    public void showOwnerStartPanel() {
        setContentPane(new OwnerStartPanel(this));
        revalidate();
        repaint();
    }

    public void showOwnerBooksPanel() {
        setContentPane(new OwnerBooksPanel(this));
        revalidate();
        repaint();
    }

    public void showOwnerCustomersPanel() {
        setContentPane(new OwnerCustomersPanel(this));
        revalidate();
        repaint();
    }

    public void showCustomerStartPanel() {
        setContentPane(new CustomerStartPanel(this));
        revalidate();
        repaint();
    }
    
   

    public void showCustomerCostPanel(double totalCost) {
        setContentPane(new CustomerCostPanel(this, totalCost));
        revalidate();
        repaint();
}
}
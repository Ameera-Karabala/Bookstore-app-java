import javax.swing.*;
import java.awt.*;

public class CustomerCostPanel extends JPanel {

    public CustomerCostPanel(MainFrame frame, double totalCost) {
        setLayout(new GridLayout(4, 1, 10, 10));

        Customer customer = frame.getCurrentCustomer();

        JLabel totalLabel = new JLabel("Total Cost: " + totalCost, SwingConstants.CENTER);
        JLabel infoLabel = new JLabel(
                "Points: " + customer.getPoints() + ", Status: " + customer.getStatus(),
                SwingConstants.CENTER
        );

        JButton backButton = new JButton("Back");
        JButton logoutButton = new JButton("Logout");

        add(totalLabel);
        add(infoLabel);
        add(backButton);
        add(logoutButton);

        
        backButton.addActionListener(e -> {
            frame.showCustomerStartPanel();
        });

        
        logoutButton.addActionListener(e -> {
            frame.setCurrentCustomer(null);
            frame.showLoginPanel();
        });
    }
}
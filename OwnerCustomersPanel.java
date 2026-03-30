import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    public LoginPanel(MainFrame frame) {
        setLayout(new GridLayout(4, 2, 10, 10));

        JLabel titleLabel = new JLabel("Welcome to the BookStore App");
        JLabel emptyLabel = new JLabel("");

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField();

        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();

        JButton loginButton = new JButton("Login");

        add(titleLabel);
        add(emptyLabel);
        add(userLabel);
        add(userField);
        add(passLabel);
        add(passField);
        add(new JLabel(""));
        add(loginButton);

        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());

            if (username.equals("admin") && password.equals("admin")) {
                frame.showOwnerStartPanel();
            } else {
                Customer customer = frame.getStore().findCustomer(username, password);

                if (customer != null) {
                    frame.setCurrentCustomer(customer);
                    frame.showCustomerStartPanel();
                } else {
                    JOptionPane.showMessageDialog(frame,
                            "Invalid username or password.");
                }
            }
        });
    }
}
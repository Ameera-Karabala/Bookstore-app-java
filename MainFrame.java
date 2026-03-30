import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CustomerStartPanel extends JPanel {

    private DefaultTableModel tableModel;
    private JTable table;

    public CustomerStartPanel(MainFrame frame) {
        setLayout(new BorderLayout(10, 10));

        Customer customer = frame.getCurrentCustomer();

        String message = "Welcome " + customer.getUsername()
                + ". You have " + customer.getPoints()
                + " points. Your status is " + customer.getStatus();

        JLabel topLabel = new JLabel(message, SwingConstants.CENTER);
        add(topLabel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"Book Name", "Book Price", "Select"}, 0) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 2) {
                    return Boolean.class;
                }
                return super.getColumnClass(column);
            }
        };

        table = new JTable(tableModel);
        refreshTable(frame);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());

        JButton buyButton = new JButton("Buy");
        JButton redeemButton = new JButton("Redeem points and Buy");
        JButton logoutButton = new JButton("Logout");

        bottomPanel.add(buyButton);
        bottomPanel.add(redeemButton);
        bottomPanel.add(logoutButton);

        add(bottomPanel, BorderLayout.SOUTH);

        buyButton.addActionListener(e -> {
    double total = getSelectedTotal();
    if (total == 0) {
        JOptionPane.showMessageDialog(frame, "Please select at least one book.");
        return;
    }

    customer.buyBooks(total);
    frame.showCustomerCostPanel(total);
});

        redeemButton.addActionListener(e -> {
    double total = getSelectedTotal();
    if (total == 0) {
        JOptionPane.showMessageDialog(frame, "Please select at least one book.");
        return;
    }

    int oldPoints = customer.getPoints();
    double discount = oldPoints / 100.0;
    double finalCost = total - discount;

    if (finalCost < 0) {
        finalCost = 0;
    }

    customer.redeemAndBuy(total);
    frame.showCustomerCostPanel(finalCost);
});

        logoutButton.addActionListener(e -> {
            frame.setCurrentCustomer(null);
            frame.showLoginPanel();
        });
    }

    private void refreshTable(MainFrame frame) {
        tableModel.setRowCount(0);
        for (Book b : frame.getStore().getBooks()) {
            tableModel.addRow(new Object[]{b.getName(), b.getPrice(), false});
        }
    }

    private double getSelectedTotal() {
        double total = 0;

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Boolean selected = (Boolean) tableModel.getValueAt(i, 2);
            if (selected != null && selected) {
                total += (double) tableModel.getValueAt(i, 1);
            }
        }

        return total;
    }
}
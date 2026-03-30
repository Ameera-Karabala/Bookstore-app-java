import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class OwnerBooksPanel extends JPanel {

    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField nameField;
    private JTextField priceField;

    public OwnerBooksPanel(MainFrame frame) {
        setLayout(new BorderLayout(10, 10));

        // Top panel: table
        tableModel = new DefaultTableModel(new Object[]{"Book Name", "Book Price"}, 0);
        table = new JTable(tableModel);
        refreshTable(frame);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(550, 150));
        add(scrollPane, BorderLayout.NORTH);

        // Middle panel: form
        JPanel middlePanel = new JPanel(new GridLayout(2, 2, 10, 10));

        middlePanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        middlePanel.add(nameField);

        middlePanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        middlePanel.add(priceField);

        add(middlePanel, BorderLayout.CENTER);

        // Bottom panel: buttons
        JPanel bottomPanel = new JPanel(new FlowLayout());

        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        JButton backButton = new JButton("Back");

        bottomPanel.add(addButton);
        bottomPanel.add(deleteButton);
        bottomPanel.add(backButton);

        add(bottomPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String priceText = priceField.getText().trim();

            if (name.isEmpty() || priceText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill all fields.");
                return;
            }

            try {
                double price = Double.parseDouble(priceText);
                frame.getStore().addBook(new Book(name, price));
                refreshTable(frame);
                nameField.setText("");
                priceField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Price must be a number.");
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a book to delete.");
                return;
            }

            String name = (String) tableModel.getValueAt(selectedRow, 0);
            double price = (double) tableModel.getValueAt(selectedRow, 1);

            for (Book b : frame.getStore().getBooks()) {
                if (b.getName().equals(name) && b.getPrice() == price) {
                    frame.getStore().removeBook(b);
                    break;
                }
            }

            refreshTable(frame);
        });

        backButton.addActionListener(e -> frame.showOwnerStartPanel());
    }

    private void refreshTable(MainFrame frame) {
        tableModel.setRowCount(0);
        for (Book b : frame.getStore().getBooks()) {
            tableModel.addRow(new Object[]{b.getName(), b.getPrice()});
        }
    }
}
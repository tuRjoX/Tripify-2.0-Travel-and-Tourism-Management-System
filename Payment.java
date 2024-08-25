import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Payment extends JFrame implements ActionListener {

    private JLabel nameLabel;
    private JLabel numberLabel;
    private JLabel amountLabel;
    private JTextField cardNumber;

    public Payment() {
        setTitle("Payment");

        JLabel heading = new JLabel("PAYMENT");
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setBounds(300, 50, 200, 30);
        add(heading);

        // Icon
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/ico.jpg"));
        setIconImage(icon);

        // Frame setup
        getContentPane().setBackground(Color.WHITE);
        setBounds(500, 200, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        // Payment Image
        ImageIcon i1 = new ImageIcon(getClass().getResource("/images/pay.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(150, 40, 800, 550);
        add(image);

        // Components
        nameLabel = new JLabel();
        numberLabel = new JLabel();
        amountLabel = new JLabel();

        nameLabel.setBounds(105, 150, 130, 35);
        numberLabel.setBounds(105, 250, 130, 35);
        amountLabel.setBounds(105, 300, 130, 35);

        JLabel l = new JLabel("Gateway");
        JLabel l2 = new JLabel("Name");
        JLabel l3 = new JLabel("Number");
        JLabel l4 = new JLabel("Amount");
        JLabel l5 = new JLabel("Card Number");

        l.setBounds(20, 200, 100, 30);
        l2.setBounds(20, 150, 100, 30);
        l3.setBounds(20, 250, 100, 30);
        l4.setBounds(20, 300, 100, 30);
        l5.setBounds(20, 350, 100, 30);

        amountLabel.setText(fetchAmountFromDatabase());

        String[] options = {"Visa", "MasterCard", "Amex"};
        JComboBox<String> dropdown = new JComboBox<>(options);
        dropdown.setBounds(105, 200, 130, 30);

        cardNumber = new JTextField();
        cardNumber.setBounds(105, 350, 130, 30);
        setupDocumentFilter(cardNumber, 16);

        add(l);
        add(l2);
        add(nameLabel);
        add(numberLabel);
        add(l3);
        add(dropdown);
        add(l4);
        add(amountLabel);
        add(l5);
        add(cardNumber);

        // Buttons
        JButton pay = new JButton("Pay");
        pay.setBounds(200, 500, 100, 25);
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.WHITE);
        pay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (nameLabel.getText().isEmpty() || numberLabel.getText().isEmpty() || amountLabel.getText().isEmpty() || cardNumber.getText().isEmpty()) {
                    StringBuilder missingFields = new StringBuilder();
                    if (nameLabel.getText().isEmpty()) missingFields.append("Name\n");
                    if (numberLabel.getText().isEmpty()) missingFields.append("Number\n");
                    if (amountLabel.getText().isEmpty()) missingFields.append("Amount\n");
                    if (cardNumber.getText().isEmpty()) missingFields.append("Card Number\n");
                    JOptionPane.showMessageDialog(null, "Please fill in the following fields:\n" + missingFields);
                } else if (numberLabel.getText().length() != 11 || cardNumber.getText().length() != 16) {
                    JOptionPane.showMessageDialog(null, "Card number must be 16 digits.");
                } else {
                    // Insert payment details into the database
                    insertPaymentDetails(nameLabel.getText(), dropdown.getSelectedItem().toString(), numberLabel.getText(), amountLabel.getText(), cardNumber.getText());
                    JOptionPane.showMessageDialog(null, "Payment done. You can go back.");
                    new Dashboard("");  // Navigate back to Dashboard after successful payment
                    dispose();  // Close the current window
                }
            }
        });
        add(pay);

        JButton back = new JButton("Back");
        back.setBounds(320, 500, 100, 25);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        // Fetch customer details
        fetchCustomerDetails();

        setVisible(true);
    }

    private void setupDocumentFilter(JTextField textField, int maxLength) {
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if ((fb.getDocument().getLength() + string.length()) <= maxLength && string.matches("\\d*")) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if ((fb.getDocument().getLength() + text.length() - length) <= maxLength && text.matches("\\d*")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }

    private String fetchAmountFromDatabase() {
        String amount = "0";
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("SELECT SUM(total_price) AS total_price FROM (SELECT SUM(price) AS total_price FROM bookpackage UNION ALL SELECT SUM(price) AS total_price FROM bookhotel) AS total");
            if (rs.next()) {
                amount = rs.getString("total_price");
            }
            rs.close();
            conn.c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return amount;
    }

    private void insertPaymentDetails(String name, String gateway, String number, String amount, String cardNumber) {
        try {
            Conn conn = new Conn();
            String query = "INSERT INTO payment (name, gateway, number, amount, cardno) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.c.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, gateway);
            pstmt.setString(3, number);
            pstmt.setString(4, amount);
            pstmt.setString(5, cardNumber);
            pstmt.executeUpdate();
            pstmt.close();
            conn.c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fetchCustomerDetails() {
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("SELECT name, number FROM customer");
            if (rs.next()) {
                nameLabel.setText(rs.getString("name"));
                numberLabel.setText(rs.getString("number"));
            }
            rs.close();
            conn.c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Dashboard(""); // Navigate back to Dashboard
    }

    public static void main(String[] args) {
        new Payment();
    }
}

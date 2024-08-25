import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Rate extends JFrame {

    private Container d;
	private ImageIcon icon, logo;
    private JLabel title, label, ratingLabel, imgLabel1;
    private JSlider ratingSlider;
    private JTextArea commentBox;
    private JButton btn4, submitButton;
    private Font f1, f2, f3, f4;
    
    Rate() {
        // Frame Layout
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Review Us");
        this.setSize(850, 550);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        d = this.getContentPane();
        d.setLayout(null);
        d.setBackground(Color.decode("#D4F1F4"));
		
		// Icon
        icon = new ImageIcon(getClass().getResource("/images/ico.jpg"));
        this.setIconImage(icon.getImage());
		
		// Logo and Images
        logo = new ImageIcon(getClass().getResource("/images/rate.png"));
        imgLabel1 = new JLabel(logo);
        imgLabel1.setBounds(500, 235, logo.getIconWidth(), logo.getIconHeight());
        d.add(imgLabel1);
		
        // Fonts
        f1 = new Font("Tahoma", Font.BOLD, 15);
        f2 = new Font("Times New Roman", Font.BOLD, 45);
        f3 = new Font("Tahoma", Font.PLAIN, 15);
		f4 = new Font("Times New Roman", Font.BOLD, 30);
        
        setVisible(true);
        
        // Title
        title = new JLabel("Rate Us");
        title.setBounds(325, 10, 300, 50);
        title.setFont(f2);
        title.setForeground(Color.decode("#FF0000"));
        d.add(title);
        
		String text1 = "<html>Please review our<br>" +
				"<html>Travel Agency<br>" +
              "with your  ratings. <br>" +
              "Also drop a comment.</html>";
		label = new JLabel(text1);
		label.setBounds(510, -30, 300, 400);
		label.setFont(f4);
		label.setForeground(Color.decode("#000000"));
		d.add(label);
		
        // Name Label
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(100, 75, 100, 25); // Adjust the position as needed
        nameLabel.setFont(f1);
        nameLabel.setForeground(Color.decode("#000000"));
        d.add(nameLabel);
        
        // Name Field
        JTextField nameField = new JTextField(); // Create a field to display the name
        nameField.setBounds(100, 100, 300, 25); // Adjust the position as needed
        nameField.setFont(f1);
        nameField.setForeground(Color.decode("#000000"));
        nameField.setEditable(false); // Set it as read-only
        d.add(nameField);
		
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT name FROM customer");
            if (rs.next()) {
                String customerName = rs.getString("name");
                nameField.setText(customerName);
            }
            rs.close();
            c.s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        // Rating Slider
        ratingLabel = new JLabel("Rating:");
        ratingLabel.setBounds(100, 125, 100, 25);
        ratingLabel.setFont(f1);
        ratingLabel.setForeground(Color.decode("#000000"));
        d.add(ratingLabel);
        
        ratingSlider = new JSlider(JSlider.HORIZONTAL, 0, 5, 0);
        ratingSlider.setMajorTickSpacing(1);
        ratingSlider.setPaintTicks(true);
        ratingSlider.setPaintLabels(true);
        ratingSlider.setBounds(100, 150, 300, 50);
        ratingSlider.setFont(f1);
        ratingSlider.setForeground(Color.decode("#000000"));
        ratingSlider.setBackground(Color.decode("#D4F1F4"));
        d.add(ratingSlider);
        
        // Comment Box
        JLabel commentLabel = new JLabel("Comment:");
        commentLabel.setBounds(100, 225, 100, 25);
        commentLabel.setFont(f1);
        commentLabel.setForeground(Color.decode("#000000"));
        d.add(commentLabel);
        
        commentBox = new JTextArea();
        commentBox.setBounds(100, 250, 300, 150);
        commentBox.setFont(f1);
        commentBox.setForeground(Color.decode("#000000"));
        d.add(commentBox);
        
        // Submit Button
        submitButton = new JButton("Submit");
        submitButton.setBounds(100, 425, 100, 25);
        submitButton.setFont(f1);
        submitButton.setForeground(Color.WHITE);
        submitButton.setBackground(Color.decode("#000000"));
        d.add(submitButton);
        
        // Thank You Popup
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				String ratingString = Integer.toString(ratingSlider.getValue()); // Convert the slider value to a string
				int rating = Integer.parseInt(ratingString); // Convert the string to an integer
				try {
				    Conn c = new Conn();
				    String query = "insert into review values ('" + nameField.getText() + "','" + ratingString + "', '" + commentBox.getText() + "')";
				    c.s.executeUpdate(query);
				
				    JOptionPane.showMessageDialog(Rate.this, "Thank You for Rating!", "Message", JOptionPane.PLAIN_MESSAGE);
				    setVisible(false);
                    Dashboard frame = new Dashboard("");
                    frame.setVisible(true);
				} catch (Exception ae) {
                    ae.printStackTrace();
				}
            }
        });
		
		// Back Button
		btn4 = new JButton("Back");
        btn4.setBounds(250, 425, 100, 25);
        btn4.setFont(f1);
        btn4.setForeground(Color.WHITE);
        btn4.setBackground(Color.decode("#000000"));
        d.add(btn4);
		
		// Back Action
		btn4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                Dashboard frame = new Dashboard("");
                frame.setVisible(true);
            }
        });
    }
}

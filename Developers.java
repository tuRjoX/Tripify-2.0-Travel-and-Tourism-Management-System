import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Developers extends JFrame {
    private Container c;
    private ImageIcon icon, logo1, logo2, logo3, logo4;
    private JLabel imgLabel1, imgLabel2, imgLabel3, imgLabel4;
    private Font f1, f2;
    private JButton btn3;

    Developers() {
        // Frame Layout
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Developers");
        this.setSize(850, 550);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.decode("#F6FCFF"));

        // Icon
        icon = new ImageIcon(getClass().getResource("/images/ico.jpg"));
        this.setIconImage(icon.getImage());

        // Developer images (Replace these with your actual developer images)
        logo1 = new ImageIcon(getClass().getResource("/images/devs/turjo.jpg"));
        logo2 = new ImageIcon(getClass().getResource("/images/devs/shaily.jpg"));
        logo3 = new ImageIcon(getClass().getResource("/images/devs/pretom.jpg"));
        logo4 = new ImageIcon(getClass().getResource("/images/devs/takia.jpg"));

        int imageWidth = 150;  // Adjust width as per your requirement
        int imageHeight = 150; // Adjust height as per your requirement

        logo1 = resizeImageIcon(logo1, imageWidth, imageHeight);
        logo2 = resizeImageIcon(logo2, imageWidth, imageHeight);
        logo3 = resizeImageIcon(logo3, imageWidth, imageHeight);
        logo4 = resizeImageIcon(logo4, imageWidth, imageHeight);

        imgLabel1 = new JLabel(logo1);
        imgLabel1.setBounds(50, 100, imageWidth, imageHeight);
        c.add(imgLabel1);

        // Adding text below imgLabel1
        JLabel labelTurjo = new JLabel("<html><center>Turjo Das Dip<br>22-48558-3<br>22-48558-3@student.aiub.edu</center></html>", JLabel.CENTER);
        labelTurjo.setBounds(40, 260, 170, 50); // Adjust Y position and height as needed
        labelTurjo.setFont(new Font("Tahoma", Font.PLAIN, 12));
        c.add(labelTurjo);

        imgLabel2 = new JLabel(logo2);
        imgLabel2.setBounds(250, 100, imageWidth, imageHeight);
        c.add(imgLabel2);

        // Adding text below imgLabel2
        JLabel labelShaily = new JLabel("<html><center>Shaily Saha<br>22-48530-3<br>22-48530-3@student.aiub.edu</center></html>", JLabel.CENTER);
        labelShaily.setBounds(240, 260, 170, 50); // Adjust Y position and height as needed
        labelShaily.setFont(new Font("Tahoma", Font.PLAIN, 12));
        c.add(labelShaily);

        imgLabel3 = new JLabel(logo3);
        imgLabel3.setBounds(450, 100, imageWidth, imageHeight);
        c.add(imgLabel3);

        // Adding text below imgLabel3
        JLabel labelPretom = new JLabel("<html><center>Pretom Chandra Roy<br>22-48556-3<br>22-48556-3@student.aiub.edu</center></html>", JLabel.CENTER);
        labelPretom.setBounds(440, 260, 170, 50); // Adjust Y position and height as needed
        labelPretom.setFont(new Font("Tahoma", Font.PLAIN, 12));
        c.add(labelPretom);

        imgLabel4 = new JLabel(logo4);
        imgLabel4.setBounds(650, 100, imageWidth, imageHeight);
        c.add(imgLabel4);

        // Adding text below imgLabel4
        JLabel labelTakia = new JLabel("<html><center>Mutahhara Takia Jaman<br>22-48334-3<br>22-48334-3@student.aiub.edu</center></html>", JLabel.CENTER);
        labelTakia.setBounds(640, 260, 170, 50); // Adjust Y position and height as needed
        labelTakia.setFont(new Font("Tahoma", Font.PLAIN, 12));
        c.add(labelTakia);

        // Fonts
        f1 = new Font("Tahoma", Font.BOLD, 15);
        f2 = new Font("Times New Roman", Font.BOLD, 45);

        // Back button
        btn3 = new JButton("<html><center>Back</center></html>");
        btn3.setBounds(375, 430, 100, 30); // Center bottom position
        btn3.setFont(f1);
        btn3.setForeground(Color.WHITE);
        btn3.setBackground(Color.decode("#000000"));
        c.add(btn3);

        // Title
        JLabel label1 = new JLabel("Meet With Our Developers");
        label1.setBounds(150, 10, 600, 50);
        label1.setFont(f2);
        label1.setForeground(Color.decode("#FF0000"));
        c.add(label1);

        // Back button action
        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                setVisible(false); // Hide the current frame
                About frame = new About();
                frame.setVisible(true); // Show the About frame
            }
        });
    }

    private ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    public static void main(String[] args) {
        Developers frame = new Developers();
        frame.setVisible(true);
    }
}

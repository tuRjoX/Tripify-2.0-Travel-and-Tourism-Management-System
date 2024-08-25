import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckPackage extends JFrame implements ActionListener {
    JButton booknow;

    public CheckPackage() {
        setTitle("Offered Packages");
        setBounds(450, 200, 900, 600);

        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/ico.jpg"));
        setIconImage(icon);

        String[] package1 = {"	GOLD PACKAGE", "	6 Days & 7 Nights", "	Airport Assistance", "	Half Day City Tour",
                "	Daily Buffet", "	Soft Drinks Free", "	Speaking Guide", "	Summer Special", "		Rs 12000/-", "/images/package1.jpg"};
        String[] package2 = {"	SILVER PACKAGE", "	5 Days & 7 Nights", "	Toll Free ", "	Entrance Free Tickets",
                "	Meet & Greet at Airport", "		Night Safari", "	Cruise with Dinner", "		Winter Special", "		Rs 24000/-",
                "/images/package2.jpg"};
        String[] package3 = {"	BRONZE PACKAGE", "	6 Days & 5 Nights ", "	Return Airfare", "	Free Clubbing & Horse Riding",
                "	Hard Drinks Free", "	Daily Buffet", "	BBQ Dinner", "	Winter Special", "		Rs 32000/-", "/images/package3.jpg"};

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel p1 = createPackage(package1);
        tabbedPane.addTab("Package 1", null, p1);

        JPanel p2 = createPackage(package2);
        tabbedPane.addTab("Package 2", null, p2);

        JPanel p3 = createPackage(package3);
        tabbedPane.addTab("Package 3", null, p3);

        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        booknow = new JButton("Book Now");
        booknow.setBackground(Color.YELLOW);
        booknow.setForeground(Color.BLACK);
        booknow.setFont(new Font("Tahoma", Font.BOLD, 20));
        booknow.addActionListener(this);
        buttonPanel.add(booknow);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public JPanel createPackage(String[] pack) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(0, 1));
        infoPanel.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel(pack[0]);
        nameLabel.setForeground(Color.MAGENTA);
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        infoPanel.add(nameLabel);

        for (int i = 2; i < 9; i++) {
            JLabel label = new JLabel(pack[i]);
            label.setForeground(i % 2 == 0 ? Color.BLUE : Color.BLACK);
            label.setFont(new Font("Tahoma", Font.BOLD, 20));
            infoPanel.add(label);
        }

        JPanel pricePanel = new JPanel();
        pricePanel.setBackground(Color.WHITE);
        JLabel priceLabel = new JLabel(pack[8]);
        priceLabel.setForeground(Color.MAGENTA);
        priceLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        pricePanel.add(priceLabel);

        panel.add(infoPanel, BorderLayout.CENTER);
        panel.add(pricePanel, BorderLayout.SOUTH);

        ImageIcon imageIcon = new ImageIcon(getClass().getResource(pack[9]));
        Image image = imageIcon.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        panel.add(imageLabel, BorderLayout.EAST);

        return panel;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == booknow) {
            Register register = new Register();
            register.setVisible(true);
            setVisible(false);
        }
    }
}

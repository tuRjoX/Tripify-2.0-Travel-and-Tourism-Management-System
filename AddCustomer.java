import javax.swing.*;    
import java.awt.*; 
import java.io.File;
import java.util.Scanner;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTextField;
import java.sql.*;
import javax.swing.JOptionPane;

public class AddCustomer extends JFrame implements ActionListener{
    JLabel labelusername;
    JTextField tfnumber, tfaddress, tfcountry, tfemail, labelname, comboid;
    JRadioButton rmale, rfemale;
    JButton add, back;
    ResultSet rs;

    public AddCustomer(String username){
        setTitle("Add Customer Details");
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/ico.jpg"));    
        setIconImage(icon); 

        JLabel text = new JLabel("ADD CUSTOMER DETAILS");
        text.setFont(new Font("Tahoma", Font.BOLD, 20));
        text.setBounds(50, 15, 300, 25);
        add(text);

        setBounds(0, 0, 850, 550);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(30, 50, 150, 25);
        add(lblusername);

        labelusername = new JLabel();
        labelusername.setBounds(220, 50, 150, 25);
        add(labelusername);

        JLabel iblid = new JLabel("NID");
        iblid.setBounds(30, 90, 150, 25);
        add(iblid);

        comboid = new JTextField();
        comboid.setBounds(220, 90, 150, 25);
        comboid.setBackground(Color.WHITE);
        add(comboid);

        JLabel lblnumber = new JLabel("Number");
        lblnumber.setBounds(30, 130, 150, 25);
        add(lblnumber);

        tfnumber = new JTextField();
        tfnumber.setBounds(220, 130, 150, 25);
        add(tfnumber);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(30, 170, 150, 25);
        add(lblname);

        labelname = new JTextField();
        labelname.setBounds(220, 170, 150, 25);
        add(labelname);

        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(30, 210, 150, 25);
        add(lblgender);

        rmale = new JRadioButton("Male");
        rmale.setBounds(220, 210, 70, 25);
        rmale.setBackground(Color.WHITE);
        add(rmale);

        rfemale = new JRadioButton("Female");
        rfemale.setBounds(300, 210, 70, 25);
        rfemale.setBackground(Color.WHITE);
        add(rfemale);

        ButtonGroup bg = new ButtonGroup();
        bg.add(rmale);
        bg.add(rfemale);

        JLabel lblcountry = new JLabel("Country");
        lblcountry.setBounds(30, 250, 150, 25);
        add(lblcountry);

        tfcountry = new JTextField();
        tfcountry.setBounds(220, 250, 150, 25);
        add(tfcountry);

        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(30, 290, 150, 25);
        add(lbladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(220, 290, 150, 25);
        add(tfaddress);

        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(30, 330, 150, 25);
        add(lblemail);

        tfemail = new JTextField();
        tfemail.setBounds(220, 330, 150, 25);
        add(tfemail);

        add = new JButton("Add");
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add.setBounds(70, 430, 100, 25);
        add.addActionListener(this);
        add(add);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(220, 430, 100, 25);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(getClass().getResource("/images/nc.png"));
        Image i2 = i1.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 0, 450, 520);
        add(image);

        try{
            Conn c =  new Conn();
            rs = c.s.executeQuery("select * from account");
            while(rs.next()){
                labelusername.setText(rs.getString(1));
            }
        }catch(Exception e){
            System.out.println(e);
        }
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == add){
            String username = labelusername.getText();
            String id = comboid.getText();
            String number = tfnumber.getText();
            String name = labelname.getText();
            String gender = null;
            if(rmale.isSelected()){
                gender = "Male";
            }else{
                gender = "Female";
            }
            String country = tfcountry.getText();
            String address = tfaddress.getText();
            String email = tfemail.getText();

            if (!id.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(null, "NID must be a 10-digit number.");
                return;
            }
            if (!number.matches("\\d{11}")) {
                JOptionPane.showMessageDialog(null, "Number must be an 11-digit number.");
                return;
            }
			
			if (!email.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")) {
            JOptionPane.showMessageDialog(null, "Email is not in correct format.");
            return;
			}

            try{
                Conn c = new Conn();
                String query = "insert into customer values ('" + username + "', '" + id + "', '" + number + "', '" + name + "', '" + gender + "', '" + country + "', '" + address + "', '" + email + "')";
                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");
                setVisible(false);
                new Account("");
            }catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            new Account("");
        }
    }

    public static void main(String[] args) {
        new AddCustomer("");
    }
}

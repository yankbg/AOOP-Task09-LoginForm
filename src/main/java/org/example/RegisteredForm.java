package org.example;

import com.toedter.calendar.JCalendar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JFrame;
import java.lang.String;

public class RegisteredForm {
    //The implementation of the JFrame, JPanel, JTextField, JPasswordField, JLabel, JCalendar,
    // JCalendar, JButton, JComboBox

    JFrame frame;
    JPanel panel;
    JTextField Firstname, Lastname, Username, Email_Address;
    JPasswordField Password;
    JLabel FirstnameLab, LastnameLab, UsernameLab, Email_AddressLab, PasswordLab,GenderLabel, DOB;
    JComboBox<String> Gender;
    JCalendar DateOfBirth;
    JButton Submit;
    JButton Reset;
    String [] GenderLab = { "Male" , "Female" };
    String DB_URL= "jdbc:mysql://127.0.0.1:3306/SwingApp";

    public RegisteredForm() {
        this.cFrame();
    }

    // The implementation of the public method
    public JFrame cFrame(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Registration Form");
        frame.setSize(800, 800);
        frame.add(this.panel());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    // The add of the panel in the JPanel for all the necessary panel
    public JPanel panel(){
        panel = new JPanel();
        panel.setLayout(null);

        panel.add(this.FirstnameLab());
        panel.add(this.Firstname());
        panel.add(this.LastnameLab());
        panel.add(this.Lastname());
        panel.add(this.UsernameLab());
        panel.add(this.Username());
        panel.add(this.Email_AddressLab());
        panel.add(this.Email_Address());
        panel.add(this.PasswordLab());
        panel.add(this.Password());
        panel.add(this.DOBLabel());
        panel.add(this.DateOfBirth());
        panel.add(this.GenderLabel());
        panel.add(this.Gender());
        panel.add(this.Submit());
        panel.add(this.Reset());

        return panel;
    }

    // The implementation of the JLabel of the Firstname
    public JLabel FirstnameLab() {
        FirstnameLab = new JLabel("Firstname");
        FirstnameLab.setBounds(20,30,100, 30);
        return FirstnameLab;
    }

    // The implementation of the JTextField of the Firstname
    public JTextField Firstname() {
        Firstname = new JTextField();
        Firstname.setBounds(130,30,200,30);
        return Firstname;
    }

    // The implementation of the JLabel of the EmailLabel
    public JLabel LastnameLab() {
        LastnameLab = new JLabel("Lastname");
        LastnameLab.setBounds(20, 70, 100, 30);
        return LastnameLab;
    }

    // The implementation of the JTextField of the Lastname
    public JTextField Lastname(){
        Lastname = new JTextField();
        Lastname.setBounds(130, 70, 200, 30);
        return Lastname;
    }

    // The implementation of the JLabel of the Username
    public JLabel UsernameLab() {
        UsernameLab = new JLabel("Username");
        UsernameLab.setBounds(20, 110, 100, 30);
        return UsernameLab;
    }

    // The implementation of the JTextField of the Username
    public JTextField Username() {
        Username = new JTextField();
        Username.setBounds(130, 110, 200, 30);
        return Username;
    }

    // The implementation of the JLabel of the EmailAddresLab
    public JLabel Email_AddressLab() {
        Email_AddressLab = new JLabel("Email_Address");
        Email_AddressLab.setBounds(20, 150, 100, 30);
        return Email_AddressLab;
    }

    // The implementation of the JTextField of the EmailLAddress
    public JTextField Email_Address() {
        Email_Address = new JTextField();
        Email_Address.setBounds(130, 150, 200, 30);
        return Email_Address;
    }

    // The implementation of the JLabel of the PasswordLab
    public JLabel PasswordLab() {
        PasswordLab = new JLabel("Password");
        PasswordLab.setBounds(20, 190, 200, 30);
        return PasswordLab;
    }

    // The implementation of the JPasswordField of the Password
    public JPasswordField Password() {
        Password = new JPasswordField();
        Password.setBounds(130, 190, 200, 30);
        return Password;
    }

    // The implementation of the JLabel of the PasswordLab
    public JLabel GenderLabel() {
        GenderLabel = new JLabel("Gender");
        GenderLabel.setBounds(20, 230, 200, 30);
        return GenderLabel;
    }


    // The implementation of the JComboBox of the Gender
    public JComboBox Gender() {
        Gender = new JComboBox<String>(GenderLab);
        Gender.setBounds(130, 230, 200, 30);
        return Gender;
    }

    // The implementation of the JLabel of the PasswordLab
    public JLabel DOBLabel() {
        DOB = new JLabel("Date of Birth");
        DOB.setBounds(20, 260, 200, 30);
        return DOB;
    }
    // The implementation of the JCalendar of the DateOfBirth
    public JCalendar DateOfBirth() {
        DateOfBirth = new JCalendar();
        DateOfBirth.setBounds(130, 270, 250, 200);
        return DateOfBirth;
    }

    // The implementation of the JButton of the Submit
    public JButton Submit() {
        Submit = new JButton("Submit");
        Submit.setBounds(130, 470, 100, 30);
        Submit.addActionListener(new ActionListener() {
            // The logic behind the click
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == Submit) {
                    String firstname = Firstname.getText().trim();
                    String lastname = Lastname.getText().trim();
                    String username = Username.getText().trim();
                    String password =  new String(Password.getPassword());
                    String address = Email_Address.getText().trim();
                    String gender = String.valueOf(Gender.getSelectedItem());
                    String birth = String.valueOf(DateOfBirth.getDate());

                    //The verification logic of the input
                    if (firstname.isEmpty() || lastname.isEmpty() || username.isEmpty()
                            || password.isEmpty() || address.isEmpty() || gender.isEmpty() ||
                            birth.isEmpty()) {
                        JOptionPane.showMessageDialog(panel, "All are required");
                    } else {
                        try{
                            //The logic behind the connection of the verification of the users within the database
                            Connection conn= DriverManager.getConnection(DB_URL,"root","1234yan#$");
                            PreparedStatement sqlVerification =conn.prepareStatement("SELECT * FROM UserInformation WHERE Firstname=? AND Lastname=? AND Username=? AND Email_Address=?");
                            sqlVerification.setString(1, firstname);
                            sqlVerification.setString(2, lastname);
                            sqlVerification.setString(3, username);
                            sqlVerification.setString(4, address);
                            ResultSet result = sqlVerification.executeQuery();
                            if (result.next()) {
                                JOptionPane.showMessageDialog(null, "Your account creation failed");
                            } else {
                                String sql = "INSERT INTO UserInformation VALUES (null, ?,?,?,?,?,?,?)";
                                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                                preparedStatement.setString(1,firstname);
                                preparedStatement.setString(2,lastname);
                                preparedStatement.setString(3,username);
                                preparedStatement.setString(4,password);
                                preparedStatement.setString(5,address);
                                preparedStatement.setString(6,gender);
                                preparedStatement.setString(7,birth);
                                int rowAffected = preparedStatement.executeUpdate();
                                if (rowAffected > 0) {
                                    JOptionPane.showMessageDialog(null,"Your account was created");
                                    Dashboard Dash = new Dashboard();
                                    Dash.setVisible(true);

                                }
                                else {
                                    JOptionPane.showMessageDialog(null,"The user already exist");
                                }
                            }
                        }
                        //The catch of the exception within the input
                        catch (SQLException exception) {
                            exception.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Please try again"+ exception.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }

            }
        });
        return Submit;
    }
    // The Reset logic of the Reset Button
    public JButton Reset() {
        Reset = new JButton("Reset");
        Reset.setBounds(240, 470, 90, 30);

        // The implementation of the ActionListener for the logic
        Reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == Reset) {
                    Firstname.setText("");
                    Lastname.setText("");
                    Username.setText("");
                    Email_Address.setText("");
                    Password.setText("");
                    DateOfBirth.setDate(new java.util.Date());
                }
            }
        });
        return Reset;
    }

    // The add of the main method where the method will run
    public static void main(String[] args) {
        new RegisteredForm();
    }

    public void setVisible(boolean b) {
    }
}
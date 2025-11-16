package org.example;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class LoginForm {

    // The implementation of the necessary JFrame, JPanel, JTextField, JPasswordField,
    // JLabel, JButton
    JFrame frame;
    JPanel panel;
    JTextField Username;
    JPasswordField Password;
    JLabel UsernameLabel, PasswordLabel, RegisteredLabel;
    JButton Submit, Reset;
    String DB_URL= "jdbc:mysql://127.0.0.1:3306/SwingApp";

    // The implementation of the public method
    public LoginForm() {
        this.Cframe();
    }

    //The implementation of the Cframe and its features
    public JFrame Cframe() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Login Form");
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

        panel.add(this.UsernameLabel());
        panel.add(this.Username());
        panel.add(this.PasswordLabel());
        panel.add(this.Password());
        panel.add(this.Link());
        panel.add(this.Submit());
        panel.add(this.Reset());

        return panel;
    }

    //The implementation of the JLabel UsernameLabel
    public JLabel UsernameLabel() {
        UsernameLabel = new JLabel("Username/Gmail");
        UsernameLabel.setBounds(20,30,100, 30);
        return UsernameLabel;
    }

    //The implementation of the JTextField Username
    public JTextField Username() {
        Username = new JTextField();
        Username.setBounds(130,30,200,30);
        return Username;
    }

    //The implementation of the JLabel PasswordLabel
    public JLabel PasswordLabel() {
        PasswordLabel = new JLabel("Password");
        PasswordLabel.setBounds(20, 70, 100,30);
        return PasswordLabel;
    }

    //The implementation of the JPasswordField Password
    public JPasswordField Password() {
        Password = new JPasswordField();
        Password.setBounds(130,70,200,30);
        return Password;
    }

    //The implementation of the JLabel Link
    public JLabel Link() {
        JLabel AccountLabel = new JLabel("Create an account");
        AccountLabel.setBounds(180, 110, 200,30);
        //The add of the addMouseListener for clicking on the Registered form link
        AccountLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                RegisteredForm RegForm = new RegisteredForm();
                RegForm.setVisible(true);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        return AccountLabel;
    }


    // The implementation of the JButton Submit
    public JButton Submit() {
        Submit = new JButton("Submit");
        Submit.setBounds(20,150,200,30);
        Submit.addActionListener(new ActionListener() {
            @Override
            //The implementation of the logic behind the click
            public void actionPerformed(ActionEvent e) {
                if ( e.getSource() == Submit) {
                    String username = Username.getText().trim();
                    String password = new String(Password.getPassword());
                    //The implementation of the verification of the different Input
                    if (username.isEmpty() || password.isEmpty()) {
                        JOptionPane.showMessageDialog(null,"All field are required");
                    } else {
                        try {
                            //The verification of the existing user within the database
                            Connection conn= DriverManager.getConnection(DB_URL,"root","Mamanlucie1906@@");;
                            String sqlVerification = "SELECT Username, Email_Address, Password FROM UserInformation WHERE Username=? || Email_Address=? & Password=?";
                            if(sqlVerification.isEmpty()) {
                                JOptionPane.showMessageDialog(panel,"The user account does not exist");
                            } else {
                                JOptionPane.showMessageDialog(panel,"Login successfully");
                                Dashboard Form = new Dashboard();
                                Form.setVisible(true);
                            }
                        }
                        //The catch of the exception
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
    public JButton Reset() {
        Reset = new JButton("Reset");
        Reset.setBounds(220,150,200,30);
        Reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == Reset) {
                    Username.setText("");
                    Password.setText("");
                }
            }
        });
        return Reset;
    }
    public static void main (String[] args) {
        new LoginForm();
    }
}
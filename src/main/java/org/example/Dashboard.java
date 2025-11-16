package org.example;

import javax.swing.*;

public class Dashboard {
    JFrame frame;
    JPanel panel;
    JLabel Dashboard;

    public Dashboard() {
        this.Cframe();
    }

    // The implementation of the public method
    public JFrame Cframe() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Dashboard");
        frame.setSize(800, 800);
        frame.add(this.panel());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    //The implementation of the add of different panel within the SwingApp
    public JPanel panel(){
        panel = new JPanel();
        panel.setLayout(null);

        panel.add(this.Dashboard());
        return panel;
    }

    //The implementation of the Dashboard panel
    public JLabel Dashboard(){
        Dashboard = new JLabel("DASHBOARD");
        Dashboard.setBounds(300,30,500,400);
        return Dashboard;
    }

    //The method for the setVisible of the Dashboard
    public void setVisible(boolean b) {
    }
}

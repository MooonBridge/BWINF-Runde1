package de.moonbridge;

import javax.swing.*;
import javax.swing.plaf.PanelUI;
import java.awt.*;

public class ProgramFrame extends JFrame {

    ProgramFrame(){

        JLabel label = new JLabel();
        label.setText("Press Buttons below or insert URL");
        label.setHorizontalTextPosition(0);
        label.setFont(new Font("Times New Roman", Font.BOLD, 25));
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(-10, 10, 500, 100);

        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setBounds(50, 200, 380, 250);

        this.setTitle("Wuerfel Tester - Mensch aergere dich nicht");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(500,500);
        this.setLayout(null);
        this.add(label);
        this.add(panel);
        this.setVisible(true);

    }
}

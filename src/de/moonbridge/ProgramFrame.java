package de.moonbridge;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class ProgramFrame extends JFrame implements ActionListener {

    JButton bt1 = new JButton();
    JButton bt2 = new JButton();
    JButton bt3 = new JButton();
    JButton bt4 = new JButton();
    JButton submit = new JButton();
    JTextField textField = new JFormattedTextField();
    JTextArea panel = new JTextArea();

    ProgramFrame(){

        JLabel label = new JLabel();
        label.setText("Knopf drücken oder eigene URL einfügen");
        label.setHorizontalTextPosition(0);
        label.setFont(new Font("Times New Roman", Font.BOLD, 25));
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(-10, 10, 500, 100);

        bt1.setBounds(48, 50, 190, 40);
        bt1.setText("Beispiel 1");
        bt1.addActionListener(this);

        bt2.setBounds(242, 50, 190, 40);
        bt2.setText("Beispiel 2");
        bt2.addActionListener(this);

        bt3.setBounds(48, 100, 190, 40);
        bt3.setText("Beispiel 3");
        bt3.addActionListener(this);

        bt4.setBounds(242, 100, 190, 40);
        bt4.setText("Beispiel 4");
        bt4.addActionListener(this);

        textField.setBounds(48, 150, 285, 20);
        textField.setText("URL hier einfügen");
        textField.setCaretColor(Color.lightGray);

        submit.setText("Submit");
        submit.setBounds(332, 150, 100, 19);
        submit.addActionListener(this);

        JLabel label2 = new JLabel();
        label2.setText("Ergebnis:");
        label2.setHorizontalTextPosition(0);
        label2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        label2.setBounds(200, 180, 100, 20);

        panel.setBackground(Color.LIGHT_GRAY);
        panel.setBounds(48, 200, 383, 250);
        panel.setFont(new Font("Times New Roman", Font.PLAIN, 12));

        this.setTitle("Wuerfel Tester - Mensch aergere dich nicht");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(500,500);
        this.setLayout(null);
        this.add(label);
        this.add(panel);
        this.add(textField);
        this.add(bt1);
        this.add(bt2);
        this.add(bt3);
        this.add(bt4);
        this.add(label2);
        this.add(submit);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e){

        AnalyzeWuerfel analyzeWuerfel = new AnalyzeWuerfel();

        if (e.getSource() == submit){
            try{
                URL url = new URL(textField.getText());
                try {
                    analyzeWuerfel.startAnalyis(textField.getText());
                    panel.setText("");
                    panel.append("Analysiere folgenden Link: " + textField.getText() + "\n");
                } catch (Exception e1){
                    panel.setText("");
                    panel.append("Es gab einen unbekannten Fehler mit der Analyse der URL");
                    return;
                }
            } catch (Exception exception){
                panel.setText("");
                panel.append("Der eingegebene Text ist keine URL");
                return;
            }
        }

        if (e.getSource() == bt1){
            analyzeWuerfel.startAnalyis("https://bwinf.de/fileadmin/user_upload/wuerfel0.txt");
            panel.setText("");
            panel.append("Analysiere folgenden Link: https://bwinf.de/fileadmin/user_upload/wuerfel0.txt\n");
        }

        if (e.getSource() == bt2){
            analyzeWuerfel.startAnalyis("https://bwinf.de/fileadmin/user_upload/wuerfel1.txt");
            panel.setText("");
            panel.append("Analysiere folgenden Link: https://bwinf.de/fileadmin/user_upload/wuerfel1.txt\n");
        }

        if (e.getSource() == bt3){
            analyzeWuerfel.startAnalyis("https://bwinf.de/fileadmin/user_upload/wuerfel2.txt");
            panel.setText("");
            panel.append("Analysiere folgenden Link: https://bwinf.de/fileadmin/user_upload/wuerfel2.txt\n");
        }

        if (e.getSource() == bt4){
            analyzeWuerfel.startAnalyis("https://bwinf.de/fileadmin/user_upload/wuerfel3.txt");
            panel.setText("");
            panel.append("Analysiere folgenden Link: https://bwinf.de/fileadmin/user_upload/wuerfel3.txt\n");
        }

        panel.append("\n");
        int[][] sortierteWuerfel = analyzeWuerfel.getErgebnis();
        panel.append("Sortierte Liste der besten Wuerfel:\n");
        for (int[] ints : sortierteWuerfel) {
            panel.append("Wuerfel Nr. " + ints[0]);
            panel.append(" mit " + ints[1] + " Siegen\n");
        }

    }

}

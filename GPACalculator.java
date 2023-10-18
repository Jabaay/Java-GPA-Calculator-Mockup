/*
 * @author gongj3@miamioh.edu
 * 
 * This project is heavily inspired by the web version of the GPA Calculator provided by 
 * the Academic Advising office of Miami University. It can precisely calculate the total
 * credit hours and cumulative GPA based on user inputs.
 * 
 */

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public abstract class GPACalculator implements ActionListener {

    private static JLabel gpaCalculator;
    private static JTextArea transcriptOutput;
    private static JTextArea forecasterOutput;

    private static JTextField pvCredits;
    private static JTextField pvGPA;
    private static JTextField csName;
    private static JTextField ttCredits;
    private static JTextField ttGPA;

    private static JComboBox creditBox; // for selecting credit(s)
    private static JComboBox gradeBox; // for selecting grade letters
    private static JComboBox themeBox;

    private static JButton addNewClass;
    private static JButton removeClass;
    private static JButton reset;

    protected static Font fontBold; // bold fonts for body
    protected static Font fontPlain; // plain fonts for body
    protected static Font fontTitle; // fonts for main titles
    protected static Font fontSub; // fonts for sub-tiles

    protected static Color rhColor; // red hawk color

    private static Integer[] creditHour = { 1, 2, 3, 4, 5 };
    private static String[] gradeLetter = { "A+", "A", "A-", "B+", "B", "B-",
            "C+", "C", "C-", "D+", "D", "D-", "F" };
    private static Double[] gradeNum = { 4.0, 4.0, 3.7, 3.3, 3.0, 2.7, 2.3, 2.0,
            1.7, 1.3, 0.7, 0.3, 0.0 };

    // the main method
    public static void main(String[] args) {

        // initialize the DataHolder object
        DataHolder dh = new DataHolder();

        // creates a frame object
        JFrame frame = new JFrame();
        // set a title for the frame
        frame.setTitle("GPA Calculator Miami University ©2022");
        // set a size for the frame (width, height)
        frame.setSize(530, 600);
        // exit out of application
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(false);
        // frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        // prevent the frame from being closed
        // frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // DO_NOTHING_ON_CLOSE won't close the window unless force it
        // prevent the frame from being resized
        frame.setResizable(false);
        // clear the layout. No Panel
        frame.setLayout(null);

        // defining a new Font object
        fontBold = new Font("Comic Sans MS", Font.BOLD, 14);
        fontPlain = new Font("Comic Sans MS", Font.PLAIN, 14);
        fontTitle = new Font("Comic Sans MS", Font.BOLD, 26);
        fontSub = new Font("Comic Sans MS", Font.BOLD, 20);
        rhColor = new Color(191, 20, 42); // RedHawk color
        // set the frame bg color to white
        frame.getContentPane().setBackground(Color.white);
        frame.setFont(fontPlain);

        // this JLabel is used to display "GPA Calculator"
        // font style is bold and the size is set to 26
        JLabel gpaCalculator = new JLabel();
        gpaCalculator.setBounds(30, 15, 300, 40);
        gpaCalculator.setText("GPA Calculator");
        gpaCalculator.setForeground(rhColor);
        gpaCalculator.setFont(fontTitle);

        // this JLabel is used to display "Previous Credits"
        // font style is bold and the size is set to 14
        JLabel previousCredits = new JLabel();
        previousCredits.setBounds(30, 60, 120, 20);
        previousCredits.setText("Previous Credits");
        previousCredits.setForeground(Color.black);
        previousCredits.setFont(fontBold);

        // this JLabel is used to display "Previous GPA"
        // font style is bold and the size is set to 14
        JLabel previousGPA = new JLabel();
        previousGPA.setBounds(250, 60, 100, 20);
        previousGPA.setText("Previous GPA");
        previousGPA.setForeground(Color.black);
        previousGPA.setFont(fontBold);

        // this JLabel is used to display "Selected Class"
        // font style is bold and the size is set to 19
        JLabel selectedClass = new JLabel();
        selectedClass.setBounds(30, 120, 300, 40);
        selectedClass.setText("Selected Class");
        selectedClass.setForeground(rhColor);
        selectedClass.setFont(fontSub);

        // this JLabel is used to display "Course Name"
        // font style is bold and the size is set to 14
        JLabel courseName = new JLabel();
        courseName.setBounds(30, 160, 90, 20);
        courseName.setText("Couse Name");
        courseName.setForeground(Color.black);
        courseName.setFont(fontBold);

        // this JLabel is used to display "Credit(s)"
        // font style is bold and the size is set to 14
        JLabel credits = new JLabel();
        credits.setBounds(30, 210, 90, 20);
        credits.setText("Credit(s)");
        credits.setForeground(Color.black);
        credits.setFont(fontBold);

        // this JLabel is used to display "Grade"
        // font style is bold and the size is set to 14
        JLabel grades = new JLabel();
        grades.setBounds(130, 210, 90, 20);
        grades.setText("Grade");
        grades.setForeground(Color.black);
        grades.setFont(fontBold);

        // this JLabel is used to display "Your Transcript"
        // font style is bold and the size is set to 14
        JLabel urTranscript = new JLabel();
        urTranscript.setBounds(235, 120, 300, 40);
        urTranscript.setText("Your Transcript");
        urTranscript.setFont(fontBold);
        urTranscript.setForeground(rhColor);
        urTranscript.setFont(fontSub);

        // this JLabel is used to display "Total Credits"
        // font style is bold and the size is set to 14
        JLabel totalCredits = new JLabel();
        totalCredits.setBounds(30, 400, 120, 20);
        totalCredits.setText("Total Credits: ");
        totalCredits.setForeground(Color.black);
        totalCredits.setFont(fontBold);

        // this JLabel is used to display "Total GPA"
        // font style is bold and the size is set to 14
        JLabel totalGPA = new JLabel();
        totalGPA.setBounds(250, 400, 90, 20);
        totalGPA.setText("Total GPA: ");
        totalGPA.setForeground(Color.black);
        totalGPA.setFont(fontBold);

        // this JLabel is used to display "Total GPA"
        // font style is bold and the size is set to 14
        JLabel msg = new JLabel();
        msg.setBounds(20, 525, 500, 20);
        msg.setText("At any time, you can clear all fields "
                + "by clicking this button.");
        msg.setForeground(Color.black);
        msg.setFont(fontPlain);

        // for the users to enter the course name
        // font style is bold and the size is set to 14
        // default input is set to 0
        pvCredits = new JTextField();
        pvCredits.setText("0");
        pvCredits.setFont(fontBold);
        pvCredits.setBackground(Color.white);
        pvCredits.setForeground(Color.black);
        pvCredits.setBounds(30, 80, 210, 25);
        pvCredits.setBorder(
                BorderFactory.createLineBorder(Color.black, 1, true));

        // for the users to enter the course name
        // font style is bold and the size is set to 14
        // default input is set to 0
        pvGPA = new JTextField();
        pvGPA.setText("0");
        pvGPA.setFont(fontBold);
        pvGPA.setBackground(Color.white);
        pvGPA.setForeground(Color.black);
        pvGPA.setBounds(250, 80, 210, 25);
        pvGPA.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));

        // for the users to enter the course name
        // font style is bold and the size is set to 14
        // default input is set to null
        csName = new JTextField();
        csName.setText("");
        csName.setFont(fontBold);
        csName.setBackground(Color.white);
        csName.setForeground(Color.black);
        csName.setBounds(30, 183, 150, 23);
        csName.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));

        // to show the users the result of their total credits
        // font style is bold and the size is set to 14
        // default input is set to null
        ttCredits = new JTextField();
        ttCredits.setText("0");
        ttCredits.setFont(fontBold);
        ttCredits.setBackground(Color.white);
        ttCredits.setForeground(Color.black);
        ttCredits.setBounds(30, 423, 210, 25);
        ttCredits.setBorder(
                BorderFactory.createLineBorder(Color.black, 1, true));

        // to show the users the result of their total GPA
        // font style is bold and the size is set to 14
        // default input is set to null
        ttGPA = new JTextField();
        ttGPA.setText("0");
        ttGPA.setFont(fontBold);
        ttGPA.setBackground(Color.white);
        ttGPA.setForeground(Color.black);
        ttGPA.setBounds(250, 423, 210, 25);
        ttGPA.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));

        // for the users to select the credit hour(s)
        // font style is bold and the size is set to 14
        // default input is set to 1
        creditBox = new JComboBox(creditHour);
        creditBox.addActionListener(creditBox);
        creditBox.setFont(fontBold);
        creditBox.setBackground(Color.white);
        creditBox.setForeground(Color.black);
        creditBox.setBounds(25, 230, 60, 30);

        // for the users to select their grade letter
        // font style is bold and the size is set to 14
        // default input is set to A+
        gradeBox = new JComboBox(gradeLetter);
        gradeBox.addActionListener(gradeBox);
        gradeBox.setFont(fontBold);
        gradeBox.setBackground(Color.white);
        gradeBox.setForeground(Color.black);
        gradeBox.setBounds(125, 230, 70, 30);

        // a button for the users to add new classes
        // button font style is plain and size is 14
        addNewClass = new JButton();
        addNewClass.setText("Add New Class");
        addNewClass.setFont(fontPlain);
        addNewClass.setBounds(30, 275, 125, 25);
        addNewClass.setBackground(new Color(222, 222, 222));
        addNewClass.setEnabled(true);
        // it's an interface so a method needs to be implemented
        addNewClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                pvCredits.setEnabled(false);
                pvGPA.setEnabled(false);

                if (csName.getText().length() > 10) {
                    transcriptOutput.setText(dh.invalidName());
                } else if (pvCredits.getText().isEmpty()) {
                    transcriptOutput.setText(dh.emptyCredits());
                } else if (pvGPA.getText().isEmpty()) {
                    transcriptOutput.setText(dh.emptyGPA());
                } else if (csName.getText().isEmpty()) {
                    transcriptOutput.setText(dh.invalidCSName());
                } else {
                    // transcriptOutput.setText(transcriptOutput.getText() + " "
                    // + csName.getText()
                    // + " | Credits: " + creditBox.getSelectedItem()
                    // + " | Grade: " + gradeBox.getSelectedItem() + "\n");
                    transcriptOutput.setText(dh.format1(
                            transcriptOutput.getText(), csName.getText(),
                            creditBox.getSelectedItem(),
                            gradeBox.getSelectedItem()));

                    int selectedCredits = 0;
                    try {
                        // System.out.println(creditBox.getSelectedItem());
                        selectedCredits += (Integer) creditBox
                                .getSelectedItem();
                        // System.out.println(pvCredits.getText());
                    } catch (Exception er) {
                        er.getStackTrace();
                    }

                    // adds up the credits and write the total to the text field
                    ttCredits.setText(dh.convertCredits(
                            Integer.parseInt(ttCredits.getText()),
                            selectedCredits));

                    // double selectedVP = selectedCredits
                    // * gradeNum[gradeBox.getSelectedIndex()];
                    double selectedVP = dh.calculateVP(selectedCredits,
                            gradeNum[gradeBox.getSelectedIndex()]);

                    // double tolGPA = selectedVP / selectedCredits;
                    double tolGPA = dh.calculateTolGPA(selectedVP,
                            selectedCredits);

                    // String.valueOf(Integer.parseInt(pvCredits.getText())
                    // + selectedCredits));
                    if (Double.parseDouble(ttGPA.getText()) != 0) {
                        ttGPA.setText(String.format("%.2f",
                                (dh.calculateMid(
                                        Double.parseDouble(ttGPA.getText()),
                                        tolGPA))));
                        // ttGPA.setText(String.format("%.2f",
                        // (Double.parseDouble(ttGPA.getText()) + tolGPA)
                        // / 2.0));
                    } else {
                        ttGPA.setText(String.format("%.3f", tolGPA));
                    }

                }
            }

        });

        // set up a text area for transcript output
        transcriptOutput = new JTextArea();
        transcriptOutput.setBounds(200, 165, 225, 200);
        transcriptOutput.setBorder(
                BorderFactory.createLineBorder(Color.black, 1, true));
        // change the color of the text
        transcriptOutput.setForeground(Color.black);
        transcriptOutput.setText("");

        // set up a scroll pane for the text area from above
        JScrollPane scrollPaneTranscript = new JScrollPane(transcriptOutput);
        // scrollPaneTranscript.setAutoscrolls(true);
        scrollPaneTranscript.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneTranscript.setBounds(230, 165, 260, 200);
        scrollPaneTranscript.setVisible(true);
        scrollPaneTranscript.setEnabled(true);

        // a button for the users to remove selected class
        // button font style is plain and size is 14
        removeClass = new JButton();
        removeClass.setText("Clear Class");
        removeClass.setFont(fontPlain);
        removeClass.setBounds(30, 300, 105, 25);
        removeClass.setBackground(new Color(222, 222, 222));
        removeClass.setEnabled(true);
        removeClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transcriptOutput.setText("");
                addNewClass.setEnabled(true);
                csName.setText("");
                pvCredits.setEnabled(true);
                pvGPA.setEnabled(true);
            }

        });

        // a button for the users to edit selected class
        // button font style is plain and size is 14
        reset = new JButton();
        reset.setText("Reset Calculations");
        reset.setFont(fontPlain);
        reset.setBounds(15, 500, 145, 25);
        reset.setBackground(new Color(222, 222, 222));
        reset.setEnabled(true);
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewClass.setEnabled(true);
                removeClass.setEnabled(true);
                transcriptOutput.setText("");
                pvCredits.setText("0");
                pvCredits.setEnabled(true);
                pvGPA.setText("0");
                pvGPA.setEnabled(true);
                ttCredits.setText("0");
                ttGPA.setText("0");
                csName.setText("");

            }

        });

        // adding the label to the frame
        frame.add(gpaCalculator);
        frame.add(previousCredits);
        frame.add(previousGPA);
        frame.add(selectedClass);
        frame.add(courseName);
        frame.add(credits);
        frame.add(grades);
        frame.add(urTranscript);
        frame.add(totalCredits);
        frame.add(totalGPA);
        frame.add(msg);
        frame.add(pvCredits);
        frame.add(pvGPA);
        frame.add(csName);
        frame.add(ttCredits);
        frame.add(ttGPA);
        frame.add(creditBox);
        frame.add(gradeBox);
        frame.add(addNewClass);
        frame.add(removeClass);
        frame.add(reset);
        // frame.add(transcriptOutput);
        frame.add(scrollPaneTranscript);
        // showing the frame
        frame.setVisible(true);
    }

}

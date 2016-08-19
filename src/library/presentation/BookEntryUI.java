/**
* Author: Chad Clayton
* email:  cclayton@regis.edu
* Date:   2016.07.12
* Description: BookEntryUI builds and displays a graphical user interface(GUI)
* which enables the user to add a Book (object) to the library system.
 */

/*
 * The MIT License
 *
 * Copyright 2016 kcwaverider.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package library.presentation;
import library.domain.Book;
import library.business.BookMgr;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
public class BookEntryUI extends JFrame{
    
    private String emptyAlert = "               ";
    private JTextField isbnField;
    private JTextField authorField;
    private JLabel authorAlertLabel;
    private JLabel isbnAlertLabel;
    
    private JButton addButton;
    private JButton resetButton;
    
    private static int screenWidth = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
    private static int screenHeight = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
    
    
    public BookEntryUI() {
        super("Library Book Entry");
        
        int windowHeight = 150;
        int windowWidth = 385; //screenWidth / 3;
        int windowX = (screenWidth - windowWidth) / 2;
        int windowY = (screenHeight - windowHeight) / 2;
        setBounds(windowX, windowY, windowWidth, windowHeight);
        
        // JPanel to hold text fields and labels
        JPanel panel = new JPanel();
        
        // ISBN
        JLabel isbnLabel = new JLabel("ISBN   ", JLabel.RIGHT);
        isbnAlertLabel = new JLabel(emptyAlert);
        isbnField = new JTextField(20);
        /*
        panel.add(isbnLabel);
        panel.add(isbnField);
        panel.add(isbnAlertLabel);
        */
        
        // Author(s)
        JLabel authorLabel = new JLabel("   Author(s)   ", JLabel.RIGHT);
        authorAlertLabel = new JLabel(emptyAlert);
        authorField = new JTextField(20);
        JLabel authorInstructions = new JLabel("  Separate multiple authors by commas");
        authorInstructions.setFont(new Font("Courier", Font.ITALIC, 10));
        authorInstructions.setAlignmentY(TOP_ALIGNMENT);
       
        
        /*
        panel.add(authorLabel);
        panel.add(authorField);
        panel.add(authorAlertLabel);
        */
    
        add(panel, BorderLayout.CENTER);
        
        // buttons
        addButton = new JButton("Add");
        resetButton = new JButton("Reset");
        
        // Layout magic!
        JPanel buttonPanel = new JPanel();
        JPanel labelPanel = new JPanel();
        JPanel fieldPanel = new JPanel();
        JPanel alertPanel = new JPanel();
        
        GridLayout labelLayout = new GridLayout(3,1);
        labelPanel.add(isbnLabel);
        labelPanel.add(authorLabel);
        labelPanel.setLayout(labelLayout);
        add(labelPanel, BorderLayout.WEST);
        
        GridLayout fieldLayout = new GridLayout(3,1);
        fieldPanel.add(isbnField);
        fieldPanel.add(authorField);
        fieldPanel.add(authorInstructions);
        fieldPanel.setLayout(fieldLayout);
        add(fieldPanel, BorderLayout.CENTER);
        
        
        GridLayout alertLayout = new GridLayout(3,1);
        alertPanel.add(isbnAlertLabel);
        alertPanel.add(authorAlertLabel);
        alertPanel.setLayout(alertLayout);
        add(alertPanel, BorderLayout.EAST);
        
        buttonPanel.add(addButton);
        buttonPanel.add(resetButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
        
        
        addButton.addActionListener(new ButtonListener());
        resetButton.addActionListener(new ButtonListener());
        
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addButton) {
                String isbn = isbnField.getText();
                String authors = authorField.getText();
                
                // Check for inptu errors before doing any actual work (bouncer technique)
                boolean isbn10 = isbn.length() == 10;
                boolean isbn13 = isbn.length() == 13;
                boolean authorBlank = authors == ""; //this should be imporoved to require at least one non-whitespace character to be entered
                boolean errorEncountered = false;
                
                   
                // If we've made it this far we know the input is good, time to process it
                String[] authorsArray = authors.split(",");
                for (String entry : authorsArray) {
                    entry = entry.trim();
                }
                ArrayList<String> authorsList = new ArrayList<String>(Arrays.asList(authorsArray));
                System.out.println(authorsList);
                Book book = new Book(authorsList, isbn);
                if ( !book.validateIsbn(book.getIsbn()) ){
                    String isbnAlert = "ISBN must be 10 or 13 digits   ";
                    isbnAlertLabel.setText(isbnAlert);
                    System.out.println("isbn error");
                    errorEncountered = true;
                }
                if (authorBlank) {
                    String authorAlert = "Need Author Name";
                    authorAlertLabel.setText(authorAlert);
                    
                    errorEncountered = true;
                }
                //if we've encountered an error return w/o doing anything
                if(errorEncountered) {
                    
                    return;
                }
                
                if (book.validate()){
                    BookMgr bookMgr = new BookMgr();
                    
                    try{
                        System.out.println("Adding Book");
                        bookMgr.storeBook(book);
                        System.out.println(book.toString());
                        System.out.println("Add Button");
                    } catch(Exception ex) {
                        String exceptionAlert = "Unable to Store that book, sorry.";
                        System.out.println(exceptionAlert);
                        System.out.println(ex);
                        isbnAlertLabel.setText(exceptionAlert);
                        authorAlertLabel.setText(exceptionAlert);
                    }
                    
                }
                
                
                //clean up UI from any error messages
                isbnAlertLabel.setText(emptyAlert);
                authorAlertLabel.setText(emptyAlert);
                
                
                
            } else if (e.getSource() == resetButton) {
                
                authorField.setText("");
                isbnField.setText("");
                isbnAlertLabel.setText(emptyAlert);
                authorAlertLabel.setText(emptyAlert);
                System.out.println("Reset Button");
            }
        }
    }
    
    public static void main (String args[]) {
        BookEntryUI window = new BookEntryUI();
    }
}

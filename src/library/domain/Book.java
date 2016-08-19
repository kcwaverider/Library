/**
* Author: Chad Clayton
* email:  cclayton@regis.edu
* Date:   2016.07.12
* Description: A class for creating and managing a Book object in a library catalog system, 
* in the Library program. Each Book as an Isbn and a list of Authors.
* 
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


package library.domain;
import java.io.Serializable;
import java.util.*;

/**
 * This class defines and object, Book, to be used in the Library program. 
 * each Book has and Isbn and list of authors.
 * @author Chad clayton
 */
public class Book implements Serializable{
    
    // The Isbn number of the book
    private String Isbn;
    private ArrayList<String> authors;
   
    // Valid Isbn numbers
    final static private int ISBN_LENGTHS[] = {10, 13};
    
    /** Book()
     *Book default constructor
     */
    public Book(){
        Isbn = "";
        authors = new ArrayList<String>();
    }
    
    /** Book()
     * Book constructor with an author name and Isbn
     * @param author
     * @param Isbn
     */
    public Book(String author, String Isbn) {
        
        this();
        authors.add(author);
        this.Isbn = Isbn;
    }
    
    public Book(ArrayList<String> authors, String Isbn) {
        this();
        this.authors = authors;
        this.Isbn = Isbn;
    }
    
    /** addAuthor()
     * Receives and author name as a parameter & checks to see if that name
     * is already associated with the book. If the name is already associated 
     * with the book addAuthor() returns false. If the name is not already
     * associated with the book addAuthor() adds the name to the ArrayList
     * of authors and returns true.
     * @param author
     * @return boolean
     */
    public boolean addAuthor(String author) {
        boolean containsAuthor = authors.contains(author);
        boolean nameTooShort = author.length() < 1;
        if (containsAuthor || nameTooShort) {
            return false;
        } else {
            return authors.add(author);
        }
        
    }
    /**
     * Receives an ArrayList of Strings containing author names and uses it
     * to set the authors list. 
     * @param authorList
     * @return boolean
     */
    public void setAuthors(ArrayList<String> authorList) {
        this.authors = authorList;
    }
    
    /** getAuthors()
     * Returns an ArrayList of strings (authors)
     * @return authors
     */
    public ArrayList<String> getAuthors() {
        
        return this.authors;
    }
    
    /** setIsbn()
     * Assigns the value of the parameter Isbn to the instance variable
     * Isbn
     * @param Isbn
     */
    public void setIsbn(String Isbn) {
        this.Isbn = Isbn;
    } 
    
    /** getIsbn()
     * returns the value of the Isbn variable
     * @return Isbn
     */
    public String getIsbn() {
        return Isbn;
    }
    
     /** equals()
     * Overrides the Object class method of the same name. Compares the value
     * of the Isbn, returning ture if matched false if not.
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        // Test if obj is itself
        if (this == obj) return true;
        // Test if obj is not a Book
        if (! (obj instanceof Book)) return false;
        
        Book book = (Book)obj;
        if (! this.getIsbn().equals(book.getIsbn())) return false;
        
        //Compare the lists of authors, ensuring neither book has all
        //plus more than the other
        if (!this.authors.containsAll(book.authors)) return false;
        if (!book.authors.containsAll(this.authors)) return false;
        
        // if none of the above conditions throw a false flag, the books
        // are the same.
        return true;
    }
    
    /** validate()
     * Validates the information in the ivars. Ensures Isbn is 10 or 13
     * numeric characters and that no author name is null or ""
     * @return boolean
     */
    public boolean validate() {
        if (validateIsbn(this.Isbn) && validateAuthorList(this.authors)) {
            System.out.println("Book Validated");
            return true;
        } else {
            System.out.println("Book NOT Validated");
            return false;
        } 
    }
    /* Validates the book's ISBN, ensurring that it is 10 or 13
        number only characters.
    */
   
    public boolean validateIsbn(String Isbn) {
        if (Isbn == null || Isbn.equals("")){
            System.out.println("176");
            return false;
        }
        
        // Check the structure of the Isbn (length and all #'s)
        boolean validIsbn = false;
        //Check the length
        for (int n = 0; n < ISBN_LENGTHS.length; n++) {
            
            if (Isbn.length() == ISBN_LENGTHS[n]) {
                validIsbn = true;
            } else {
                System.out.println("188");
                System.out.println("ISBN Length = " + Isbn.length());
                System.out.println("ISBN: " + Isbn);
                System.out.println("Check against: " + ISBN_LENGTHS[n]);
            }
        }
        //Check to see that each character is a number
        for (int n = 0; n < Isbn.length(); n++) {
            if (Isbn.charAt(n) < '0' || Isbn.charAt(n) > '9') {
                System.out.println("194");
                validIsbn = false;
            }
        }
        if(!validIsbn) {
            System.out.println("199");
            return false;
        }
        return true;
    }
    /*validates the Author array list, ensureing that there are not empty
        strings and that at least 1 author is in the list
    */
    public boolean validateAuthorList(ArrayList<String> authors) {
        // Check to see if authors contains illegal values
        if (authors.contains(null) || authors.contains("")) return false;
        // Check to see if authors has at least one value
        if (authors.size() < 1) return false;
        return true;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString(){
        return "ISBN: " + this.Isbn.toString() +  " - " + "Authors :" + this.authors.toString();
        
    }
    
}

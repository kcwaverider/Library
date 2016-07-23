package library.domain;

/**
* Author: Chad Clayton
* email:  cclayton@regis.edu
* Date:   2016.07.12
* Description: A class for creating and managing a Book object in a library catalog system, 
* in the Library program. Each Book as an Isbn and a list of Authors.
* 
 */
/*
Netbeans 8.1
Mac OSX 10.11.5
 */



import java.util.*;

/**
 * This class defines and object, Book, to be used in the Library program. 
 * each Book has and Isbn and list of authors.
 * @author Chad clayton
 */
public class Book {
    
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
            return true;
        } else {
            return false;
        } 
    }
    
    private boolean validateIsbn(String Isbn) {
        if (Isbn == null || Isbn.equals("")) return false;
        
        // Check the structure of the Isbn (length and all #'s)
        boolean validIsbn = false;
        //Check the length
        for (int n = 0; n < ISBN_LENGTHS.length; n++) {
            
            if (Isbn.length() == ISBN_LENGTHS[n]) validIsbn = true;
        }
        //Check to see that each character is a number
        for (int n = 0; n < Isbn.length(); n++) {
            if (Isbn.charAt(n) < '0' || Isbn.charAt(n) > '9') {
                validIsbn = false;
            }
        }
        if(!validIsbn) return false;
        return true;
    }
    
    private boolean validateAuthorList(ArrayList<String> authors) {
        // Check to see if authors contains illegal values
        if (authors.contains(null) || authors.contains("")) return false;
        // Check to see if authors has at least one value
        if (authors.size() < 1) return false;
        return true;
    }
    
}

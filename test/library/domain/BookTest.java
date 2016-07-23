/**
* Author: Chad Clayton
* email:  cclayton@regis.edu
* Date:   2016.07.12
* Description: JUnit testing of a class for creating and managing a Book object in a library catalog system, 
* in the Library program. Each Book as an Isbn and a list of Authors.
* 
 */
package library.domain;

import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class BookTest {
    
    public BookTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of equals method, of class Book, to see if matching books 
 return true
     */
    @Test
    public void testEqualsTrue() {
        System.out.println("equals");
        Object obj = new Book(new ArrayList<String>(Arrays.asList(new String[] {"John Madden", "Brett Favre"})), "1234567890");
        Book instance = new Book(new ArrayList<String>(Arrays.asList(new String[] {"John Madden", "Brett Favre"})), "1234567890");
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Book, to see if mismatched Isbns 
 return false
     */
    @Test
    public void testEqualsFalseIsbn() {
        System.out.println("equals");
        Object obj = new Book(new ArrayList<String>(Arrays.asList(new String[] {"John Madden", "Brett Favre"})), "1234567800");
        Book instance = new Book(new ArrayList<String>(Arrays.asList(new String[] {"John Madden", "Brett Favre"})), "1234567890");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    /**
     * Test of equals method, of class Book to see if mismatched names but 
 matched Isbns return true
     */
    @Test
    public void testEqualsTrueNameChange() {
        System.out.println("equals");
        Object obj = new Book(new ArrayList<String>(Arrays.asList(new String[] {"John Madden", "Ted Faurer"})), "1234567890");
        Book instance = new Book(new ArrayList<String>(Arrays.asList(new String[] {"John Madden", "Brett Favre"})), "1234567890");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of validate method, of class Book to see that a name and 10
 digit Isbn are valid
     */
    @Test
    public void testValidate10() {
        System.out.println("validate");
        Book instance = new Book("name", "1234567890");
        boolean expResult = true;
        boolean result = instance.validate();
        assertEquals(expResult, result);

    }
     /**
     * Test of validate method, of class Book to see that a name and 13
 digit Isbn are valid
     */
    @Test
    public void testValidate13() {
        System.out.println("validate");
        Book instance = new Book("name", "1234567890123");
        boolean expResult = true;
        boolean result = instance.validate();
        assertEquals(expResult, result);

    }
     /**
     * Test of validate method, of class Book to see that a name and 3
 digit Isbn are invalid
     */
    @Test
    public void testValidateFAILonIsbnsmall() {
        System.out.println("validate");
        Book instance = new Book("name", "123");
        boolean expResult = false;
        boolean result = instance.validate();
        assertEquals(expResult, result);
    }
     /**
     * Test of validate method, of class Book to see that a name and 12
 digit Isbn are invalid
     */
    @Test
    public void testValidateFAILonIsbnmiddle() {
        System.out.println("validate");
        Book instance = new Book("name", "123456789012");
        boolean expResult = false;
        boolean result = instance.validate();
        assertEquals(expResult, result);
    }
     /**
     * Test of validate method, of class Book to see that a name and 15
 digit Isbn are invalid
     */
    @Test
    public void testValidateFAILonIsbnlarge() {
        System.out.println("validate");
        Book instance = new Book("name", "12345678912345");
        boolean expResult = false;
        boolean result = instance.validate();
        assertEquals(expResult, result);
    }
     /**
     * Test of validate method, of class Book to see that a no name and 10
 digit Isbn are invalid
     */
    @Test
    public void testValidateFAILonAuthor() {
        System.out.println("validate");
        Book instance = new Book("", "1234567890");
        boolean expResult = false;
        boolean result = instance.validate();
        assertEquals(expResult, result);
    } 
    
    public void testAddAuthorPass() {
        System.out.println("add author");
        Book instance = new Book(new ArrayList<String>(Arrays.asList(new String[] {"John Madden", "Ted Faurer"})),"1234567890");
        boolean expResult = true;
        boolean result = instance.addAuthor("Steve Young");
        assertEquals(expResult, result);
    }
    
    public void testAddAuthorFail() {
        System.out.println("add author");
        Book instance = new Book(new ArrayList<String>(Arrays.asList(new String[] {"John Madden", "Ted Faurer"})),"1234567890");
        boolean expResult = false;
        boolean result = instance.addAuthor("");
        assertEquals(expResult, result);
    }
}
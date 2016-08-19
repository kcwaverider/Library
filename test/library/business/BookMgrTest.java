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
package library.business;

import library.domain.Book;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kcwaverider
 */
public class BookMgrTest {
    
    public BookMgrTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of storeBook method, of class BookMgr.
     */
    @Test
    public void testStoreBook() throws Exception {
        System.out.println("storeBook");
        Book book = new Book("Name", "1234567890");
        BookMgr instance = new BookMgr();
        Book expResult = book;
        Book result = instance.storeBook(book);
        assertEquals(expResult, result);

    }
    
    /**
     * Test of storeBook method FAILURE, of class BookMgr.
     */
    @Test
    public void testStoreBookFAIL() throws Exception {
        System.out.println("storeBookFAIL");
        Book book = new Book("Name", "1234567890");
        BookMgr instance = new BookMgr();
        Book expResult = new Book("Name1", "1234567899");;
        Book result = instance.storeBook(book);
        assertFalse(expResult == result);

    }
    
}

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

package library.services;
import java.io.*;
import library.domain.*;





public class BookSvcSerializedIOImpl implements Serializable, IBookSvc {
   
    /**
     * add() adds & stores a book in the output file
     * @param book
     * @return book - the Book that is passed in
     * @throws Exception 
     */
   @Override
   public Book add(Book book) throws Exception {
       FileOutputStream fileOut = new FileOutputStream("storage");
       ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
       objectOut.writeObject(book);
       objectOut.flush();
       objectOut.close();
       return book;
   }
    
}

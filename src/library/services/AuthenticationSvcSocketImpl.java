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
import library.domain.*;

import java.io.*;
import java.net.*;
/**
 *
 * @author kcwaverider
 */
public class AuthenticationSvcSocketImpl implements Serializable, IAuthenticationSvc {
    
    public boolean authenticate(Login login) {

        
        Socket socket = null;
        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        boolean validated = false;
        try {
            
            socket = new Socket(InetAddress.getLocalHost(), 8000);
            
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            
            out.writeObject(login);
            
            Boolean validate = (Boolean)in.readObject();
            validated = validate.booleanValue();
            
            System.out.println(validated);
            return validated;
        } catch (Exception e) {
            // log the error
            
        } finally {
          try {
              /*
              in.close();
              out.close();
              socket.close();
              */
          } catch (Exception e) {
           // log the error
           
            }
        }
        return validated;
    }
}

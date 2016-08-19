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

import library.domain.Login;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kcwaverider
 */
public class AuthenticationMgrTest {
    
    public AuthenticationMgrTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of authenticate method, of class AuthenticationMgr.
     */
    @Test
    public void testAuthenticatePass() {
        System.out.println("authenticate Pass");
        Login login = new Login("admin", "rosebud");
        AuthenticationMgr authMgr = new AuthenticationMgr();
        boolean expResult = true;
        try {
            Boolean value = authMgr.authenticate(login);
            boolean result = value.booleanValue();
            assertEquals(expResult, result);
        } catch (Exception e) {
            fail("Exception Thrown");
        }
        
    }
    
    @Test
    public void testAuthenticateFail() {
        System.out.println("authenticate Pass");
        Login login = new Login("notadmin", "notrosebud");
        AuthenticationMgr authMgr = new AuthenticationMgr();
        boolean expResult = false;
        try {
            Boolean value = authMgr.authenticate(login);
            boolean result = value.booleanValue();
        assertEquals(expResult, result);
        } catch (Exception e) {
            fail("Exception Thrown");
        }
        
    }
    
}

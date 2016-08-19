/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.services;
import library.domain.*;
import java.sql.*;
import javax.sql.*;

/**
 *
 * @author kcwaverider
 */
public class BookSvcJDBCImpl implements IBookSvc{
     //INSERT INTO `claytonlibrary`.`book` (`isbn`, `title`) VALUES ('1234554321123', 'Moby Dick');
    
    private String connString = 
            "jdbc:mysql://localhost:3306/claytonlibrary?user=root&password=admin";
    
    private Connection getConnection() throws Exception {
        
        return DriverManager.getConnection(connString);
    }
    
    public Book add(Book book) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(connString);
        
        try {
            
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO book (isbn, author) VALUES ('" + book.getIsbn() + 
                    "', '" + book.getAuthors() + "')";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            if (conn != null) conn.close();
        }
        return book;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventaris.barang;
import java.sql.*;
import java.sql.DriverManager;


/**
 *
 * @author Huda Putra
 */
public class koneksi {
  public koneksi(){
        
    }
    public Connection getConnection() throws SQLException{
        Connection cn;
        try {
            String server = "jdbc:mysql://localhost/inventarisbarang";
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
            cn = DriverManager.getConnection(server, "root", "");
            return cn;
        } catch (SQLException se) {
            System.out.println(se.toString());
            System.out.println("konek");
            return null;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }   
}


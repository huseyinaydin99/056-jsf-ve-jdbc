/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdbc.control;

import static com.jdbc.control.JDBCBaglantisi.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author husey
 */
public class JDBCBaglantisi_Read {

    private Connection connection;
    private String data;

    public JDBCBaglantisi_Read() {
        try {
            Object object = getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JDBCBaglantisi_Read.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(JDBCBaglantisi_Read.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCBaglantisi_Read.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(JDBCBaglantisi_Read.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/firmaveritabani", "root", "toor");
        if (connection.isClosed()) {
            return null;
        } else {
            return connection;
        }
    }

    public String getData() throws SQLException {
        PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("select * from personel");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            //System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4));
            data += resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4) + " <br>";
        }
        resultSet.close();
        preparedStatement.close();
        return data;
    }
    
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        JDBCBaglantisi_Read jdbcBaglantisi = new JDBCBaglantisi_Read();
        System.err.println(jdbcBaglantisi.getData());
    }

}

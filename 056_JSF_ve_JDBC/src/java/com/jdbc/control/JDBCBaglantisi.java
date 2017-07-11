/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdbc.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author husey
 */
public class JDBCBaglantisi {
    static Connection connection = null;
    
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/firmaveritabani","root","toor");
        if(connection.isClosed()) System.out.println("Bağlantı Başarısız");
        else{ 
            System.out.println("Bağlantı Başarılı");
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("select * from personel");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }
    }
}

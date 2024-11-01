/*
 * @ (#) ConnectionDB.java       1.0     10/31/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.labweek01.connectionDB;
/*
 * @description:
 * @author: Luong Tan Dat
 * @date: 10/31/2024
 */

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {
    public Connection connection;

    public Connection getConnection() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://localhost:3306/mydb";
            connection = DriverManager.getConnection(url,"root", "root");
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}

package com.amazon.busPassManagement.DB;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
    JDBC Procedure:
 		1. Load the Driver
 			1.1 Add the DataBase Library in your project
 			1.2 Use API -> Class.forname

 		2. Connect to DataBase
 			URL
 			Username
 			Password
 			API -> DriverManager.getConnection(....)
 			API -> Connection

 		3. Execute SQL Query
 			API -> Using the API Statement

 		4. Close Connection

 */
public class DB {

    public static String FILEPATH = "C:/Users/vkams/Desktop/DeskTop/ATLAS/Final project/Project-1/BusPassVKAMS/busPassManagement/src/main/java/dbconfig.txt";
    public static String URL = "";
    public static String USER = "";
    public static String PASSWORD = "";
    Connection connection; // API from JDBC package to store connection.
    Statement statement; // API from JDBC package to execute SQL statement.

    private static DB db = new DB(); // Single object.

    public static DB getInstance()
    {
        return db;
    }

    private DB(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // For SQLServer.
            //Class.forName("com.mysql.cj.jdbc.Driver"); // For MySQL
            System.out.println("[DB] Driver Loaded Successfully....");
            createConnection();
        }catch (Exception e){
            System.err.println("[DB]Something went wrong..."+e);
        }
    }

    private void createConnection()
    {
        try {
            File file = new File(FILEPATH);
            if(file.exists()) {
                FileReader reader = new FileReader(file);
                BufferedReader buffer = new BufferedReader(reader);

                URL = buffer.readLine();
                USER = buffer.readLine();
                PASSWORD = buffer.readLine();

                buffer.close();
                reader.close();

                System.out.println("DB Configured using File ..");
            }else {
                System.err.println("Cannot Read the DB Config File...");
            }


            /*String DB_URI = "jdbc:sqlserver://localhost:61643;databaseName=buspassdb;trustServerCertificate=true;";
            String user = "vkams";
            String Password = "Mkv#1023";*/
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("[createConnection] Connection Created Successfully....");
        }catch (Exception e)
        {
            System.err.println("[createConnection]Something went wrong....."+e);
        }

    }

    public int executeSQL(String sql)
    {
        int result=0;
        try{
            System.out.println("[executeSQL]Executing SQL Query| "+sql);
            statement = connection.createStatement();
            result = statement.executeUpdate(sql); // executeUpdate -> is used to perform insert/update/delete in table
            System.out.println("[executeSQL] SQL Query Executed...");

        }catch (Exception e)
        {
            System.err.println("[executeSQL]Something went wrong..."+e);
        }

        return result;
    }

    public ResultSet executeQuery(String sql)
    {
        ResultSet result=null;
        try{
            System.out.println("[executeSQL]Executing SQL Query| "+sql);
            statement = connection.createStatement();
            result = statement.executeQuery(sql); // which will retrieve data from the table into java application
            System.out.println("[executeSQL] SQL Query Executed...");

        }catch (Exception e)
        {
            System.err.println("[executeSQL]Something went wrong..."+e);
        }

        return result;
    }

    public void closeConnection()
    {
        try{
            connection.close();
            System.out.println("[closeConnection] Connection Closed...");
        }catch (Exception e)
        {
            System.err.println("[closeConnection] Something went wrong..."+e);
        }
    }


}



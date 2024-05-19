package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	public static Connection makeConnection(){
        //db.properties ���ϰ��
        String filePath = "C:/lost-and-found/lost_and_found/src/db.properties";
        Connection con = null;
        try {
            //db.properties ����ּ�,����ڸ�,����ھ�ȣ ��������
            Properties properties = new Properties();
            properties.load(new FileReader(filePath));
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            //ORACLE JDBC LOADING
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //DATABASE CONNECT
            con = DriverManager.getConnection(url, user, password);
            System.out.println("����Ÿ���̽����� ����");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("����Ÿ���̽� ����̹� �ε� ����");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("����Ÿ���̽� ���� ����");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("DB.PROPERTIES �������");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("DB.PROPERTIES �������");
        }
        return con;
    }
}
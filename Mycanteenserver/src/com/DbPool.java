package com;

//import javax.naming.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DbPool {
  private static Connection conn;
  public static Connection createConn()
  {
    try
    {
      String url1="jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_canteenhit?useSSL=false";
      //String url1="jdbc:mysql://localhost:3306/mycanteendb?useSSL=false";
      String username="z00nwj35o4";
      //String password="pooh510cx77ace";
      String password="3m5jk3h4hh4l0k4zmyhxllzm30ykyl22ymjz415k";
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      conn=DriverManager.getConnection(url1,username,password);

    }
    catch (Exception e)
    {
      System.out.println("failed----------------");
      e.printStackTrace();
    }
    return conn;
  }
  public ResultSet executeQuery(String sql)
  {
    ResultSet rs=null;
    if (conn==null)
    {
      conn=createConn();
    }
    try
    {
      Statement st=conn.createStatement();
      rs=st.executeQuery(sql);
    }
    catch(SQLException e)
    {
      e.printStackTrace();
    }
    return rs;
  }
  public static Connection getConn() {
    return conn;
  }
  public static void setConn(Connection conn) {
    DbPool.conn = conn;
  }
  public DbPool()
  {
    DbPool.conn =createConn();
  }
}

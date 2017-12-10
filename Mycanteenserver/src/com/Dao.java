package com;

import java.sql.*;
import java.util.*;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;

public class Dao {
  DbPool dbp=new DbPool();
  /*public List<Book> allbooks(int pageNumber,int pageSize,String s)
  {
    List<Book> booklist=new ArrayList<Book>();
    String sql="select * from book b,author a where b.authorid=a.authorid and a.name=\""+s+"\" limit ?,?";
    PreparedStatement ps=null;
    try
    {
      //DbPool.createConn();
      ps=DbPool.getConn().prepareStatement(sql);
      ps.setInt(1, (pageNumber-1)*pageSize);
      ps.setInt(2, (pageSize));
      ResultSet rs=ps.executeQuery();
      while (rs.next())
      {
        Book book=new Book();
        //isbn!!!!!!!!!!!!!!!
        book.setIsbn(rs.getInt("isbn"));
        book.setTitle(rs.getString("title"));
        book.setAuthorid(rs.getInt("authorid"));
        book.setPublisher(rs.getString("publisher"));
        book.setPublishdata(rs.getDate("publishdata"));
        book.setPrice(rs.getInt("price"));
        book.setName(rs.getString("name"));
        book.setAge(rs.getInt("age"));
        book.setCountry(rs.getString("country"));
        booklist.add(book);
      }
      ps.close();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return booklist;
  }
  public int getbooksamount(String s)
  {
    int i=0;
    String sql="select count(*) from book b,author a where b.authorid=a.authorid and a.name=\""+s+"\"";
    PreparedStatement ps=null;
    try
    {
      ps=DbPool.getConn().prepareStatement(sql);
      ResultSet rs=ps.executeQuery();
      if (rs.next())
      {
        i=rs.getInt(1);
      }
      ps.close();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return i;
  }
  public Book findbook(String s)
  {
    Book thebook=new Book();
    String sql="select * from book b,author a where b.authorid=a.authorid and b.title=\""+s+"\"";
    PreparedStatement ps=null;
    try
    {
      //DbPool.createConn();
      ps=DbPool.getConn().prepareStatement(sql);
      ResultSet rs=ps.executeQuery();
      if (rs.next())
      {
        thebook.setIsbn(rs.getInt("isbn"));
        thebook.setTitle(rs.getString("title"));
        thebook.setAuthorid(rs.getInt("authorid"));
        thebook.setPublisher(rs.getString("publisher"));
        thebook.setPublishdata(rs.getDate("publishdata"));
        thebook.setPrice(rs.getInt("price"));
        thebook.setName(rs.getString("name"));
        thebook.setAge(rs.getInt("age"));
        thebook.setCountry(rs.getString("country"));
      }
      ps.close();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return thebook;
  }
  public int deletebook(int i)
  {
    String sql="delete from book where isbn=?";
    PreparedStatement ps=null;
    int iflag=0;
    try
    {
      //DbPool.createConn();
      ps=DbPool.getConn().prepareStatement(sql);
      ps.setInt(1, i);
      iflag=ps.executeUpdate();
      ps.close();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return iflag;
  }
  public String findauthor(int i)
  {
    String name="";
    String sql="select name from book b,author a where b.authorid=a.authorid and b.isbn=?";
    PreparedStatement ps=null;
    try
    {
      ps=DbPool.getConn().prepareStatement(sql);
      ps.setInt(1, i);
      ResultSet rs=ps.executeQuery();
      if (rs.next())
      {
        name=rs.getString("name");
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return name;
  }
  public int updata(String title,String publisher,java.util.Date publishdata,int price,int isbn)
  {
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");   
    String str=sdf.format(publishdata);  
    String sql="update book set title=\""+title+"\",publisher=\""+publisher+"\",publishdata=\""+str
        +"\",price="+price+" where title=\""+title+"\"";
    PreparedStatement ps=null;
    int iflag=0;
    try
    {
      //DbPool.createConn();
      ps=DbPool.getConn().prepareStatement(sql);
      //ps.setString(1, );
      iflag=ps.executeUpdate();
      ps.close();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return iflag;
  }*/
  public DbPool getDbp() {
    return dbp;
  }
  
  public void setDbp(DbPool dbp) {
    this.dbp = dbp;
  }
  
  public int login(String id,String password)
  {
    int canteenid=0;
    String sql="select canteenid from workerlogin where id=\""+id+"\" and password=\""+password+"\"";
    System.out.println(sql);
    PreparedStatement ps=null;
    System.out.println(canteenid);
    try
    {
      //DbPool.createConn();
      ps=DbPool.getConn().prepareStatement(sql);
      ResultSet rs=ps.executeQuery();
      if (rs.next())
      {
        canteenid=rs.getInt("canteenid");
      }
      //ps.close();
      System.out.println(canteenid);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return canteenid;
  }
  
  public String getdesc(String canteenid) {
    String canteendesc="";
    String desc2="";
    String sql="select * from canteendesc where canteenid="+canteenid;
    System.out.println(sql);
    PreparedStatement ps=null;
    try
    {
      //DbPool.createConn();
      ps=DbPool.getConn().prepareStatement(sql);
      ResultSet rs=ps.executeQuery();
      if (rs.next())
      {
        canteendesc+=rs.getString("canteendesc");
        //desc2="{\"desc\":\""+canteendesc+"\"}";
      }
      ps.close();
      System.out.println(canteendesc);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return canteendesc;
  }

  public int register(String id,String password,String canteenid) {
    int rflag=0;
    String sql="insert into workerlogin values(\""+id+"\",\""+password+"\","+canteenid+")";
    System.out.println(sql);
    PreparedStatement ps=null;
    try
    {
      //DbPool.createConn();
      ps=DbPool.getConn().prepareStatement(sql);
      rflag=ps.executeUpdate();
      ps.close();
      System.out.println("rflag="+rflag);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return rflag;
  }
  
  public List<Dish> getdish(String canteenid){
    List<Dish> dishlist=new ArrayList<Dish>();
    String sql="select * from menu where canteenid="+canteenid;
    PreparedStatement ps=null;
    try
    {
      //DbPool.createConn();
      ps=DbPool.getConn().prepareStatement(sql);
      ResultSet rs=ps.executeQuery();
      while (rs.next())
      {
        Dish dish=new Dish();
        dish.setCanteenid(Integer.valueOf(canteenid));
        dish.setDishintrodiction(rs.getString("dishintroduction"));
        dish.setDishprice(rs.getInt("dishprice"));
        dish.setDishscore(rs.getFloat("dishscore"));
        dish.setPicturename(rs.getString("picturename"));
        dish.setCommentpeople(rs.getInt("commentpeople"));
        dish.setDishname(rs.getString("dishname"));
        dishlist.add(dish);
      }
      ps.close();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return dishlist;
  }
  
  public int addcomment(int commentnumber,String comment,String dishname) {   
    int rflag=0;
    String sql="insert into dishcomment values("+commentnumber+",\""+comment+"\",\""+dishname+"\")";
    System.out.println(sql);
    PreparedStatement ps=null;
    try
    {
      //DbPool.createConn();
      ps=DbPool.getConn().prepareStatement(sql);
      rflag=ps.executeUpdate();
      ps.close();
      System.out.println("rflag="+rflag);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return rflag;
  }
  
  public int getdishamount()
  {
    int i=0;
    String sql="select count(*) from dishcomment";
    PreparedStatement ps=null;
    try
    {
      ps=DbPool.getConn().prepareStatement(sql);
      ResultSet rs=ps.executeQuery();
      if (rs.next())
      {
        i=rs.getInt(1);
      }
      ps.close();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return i;
  }
  
  public int changescore(String score,String commentpeople,String dishname) {
    int rflag=0;
    String sql="update menu set dishscore="+score+",commentpeople="+commentpeople+" where picturename=\""+dishname+"\"";
    System.out.println(sql);
    PreparedStatement ps=null;
    try
    {
      //DbPool.createConn();
      ps=DbPool.getConn().prepareStatement(sql);
      rflag=ps.executeUpdate();
      ps.close();
      System.out.println("rflag="+rflag);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return rflag;
  } 
  
  public Dish getadish(String picturename){
    Dish dish=new Dish();
    String sql="select * from menu where picturename=\""+picturename+"\"";
    PreparedStatement ps=null;
    try
    {
      //DbPool.createConn();
      ps=DbPool.getConn().prepareStatement(sql);
      ResultSet rs=ps.executeQuery();
      while (rs.next())
      { 
        dish.setCanteenid(Integer.valueOf(rs.getString("canteenid")).intValue());
        dish.setDishintrodiction(rs.getString("dishintroduction"));
        dish.setDishprice(rs.getInt("dishprice"));
        dish.setDishscore(rs.getFloat("dishscore"));
        dish.setPicturename(rs.getString("picturename"));
        dish.setCommentpeople(rs.getInt("commentpeople"));
        dish.setDishname(rs.getString("dishname"));     
      }
      ps.close();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return dish;
  }
  
  public int getmessageamount()
  {
    int i=0;
    String sql="select count(*) from canteenmessage";
    PreparedStatement ps=null;
    try
    {
      ps=DbPool.getConn().prepareStatement(sql);
      ResultSet rs=ps.executeQuery();
      if (rs.next())
      {
        i=rs.getInt(1);
      }
      ps.close();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return i;
  }

  public int addmessage(int messagenumber,String msg,String canteenid) {   
    int rflag=0;
    String sql="insert into canteenmessage values("+messagenumber+",\""+msg+"\","+canteenid+")";
    System.out.println(sql);
    PreparedStatement ps=null;
    try
    {
      //DbPool.createConn();
      ps=DbPool.getConn().prepareStatement(sql);
      rflag=ps.executeUpdate();
      ps.close();
      System.out.println("rflag="+rflag);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return rflag;
  }
  
  public List<String> getdishcomment(String dishname){
    List<String> commentlist=new ArrayList<String>();
    String sql="select * from dishcomment where dishname=\""+dishname+"\"";
    PreparedStatement ps=null;
    try
    {
      //DbPool.createConn();
      ps=DbPool.getConn().prepareStatement(sql);
      ResultSet rs=ps.executeQuery();
      while (rs.next())
      {
        String comment=rs.getString("comment");
        String comment2="{\"comment\":\""+comment+"\"}";
        /*Dish dish=new Dish();
        dish.setCanteenid(Integer.valueOf(canteenid));
        dish.setDishintrodiction(rs.getString("dishintroduction"));
        dish.setDishprice(rs.getInt("dishprice"));
        dish.setDishscore(rs.getFloat("dishscore"));
        dish.setPicturename(rs.getString("picturename"));
        dish.setCommentpeople(rs.getInt("commentpeople"));
        dish.setDishname(rs.getString("dishname"));*/
        commentlist.add(comment2);
      }
      ps.close();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return commentlist;
  }
  
  public int addbirthdaybook(String canteenid,String year,String month,
      String day,String lunchordinner,String name,String phonenumber){
    int rflag=0;
    String sql="insert into birthdaybook values("+year+","+month+
        ","+day+","+lunchordinner+",\""+name+"\",\""+phonenumber+"\","+canteenid+")";
    System.out.println(sql);
    PreparedStatement ps=null;
    try
    {
      //DbPool.createConn();
      ps=DbPool.getConn().prepareStatement(sql);
      rflag=ps.executeUpdate();
      ps.close();
      System.out.println("rflag="+rflag);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return rflag;
  }
  
  public int addcanteenbook(String canteenid,String year,String month,
      String day,String lunchordinner,String peoplenumber,String name,
      String phonenumber){
    int rflag=0;
    String sql="insert into canteenbook values("+year+","+month+
        ","+day+","+lunchordinner+","+peoplenumber+",\""+name+"\",\""
        +phonenumber+"\","+canteenid+")";
    System.out.println(sql);
    PreparedStatement ps=null;
    try
    {
      ps=DbPool.getConn().prepareStatement(sql);
      rflag=ps.executeUpdate();
      ps.close();
      System.out.println("rflag="+rflag);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return rflag;
  }
  
  public List<String> getcanteencomment(String canteenid){
    List<String> messagelist=new ArrayList<String>();
    String sql="select * from canteenmessage where canteenid="+canteenid;
    PreparedStatement ps=null;
    try
    {
      //DbPool.createConn();
      ps=DbPool.getConn().prepareStatement(sql);
      ResultSet rs=ps.executeQuery();
      while (rs.next())
      {
        String message=rs.getString("message");
        String message2="{\"message\":\""+message+"\"}";
        /*Dish dish=new Dish();
        dish.setCanteenid(Integer.valueOf(canteenid));
        dish.setDishintrodiction(rs.getString("dishintroduction"));
        dish.setDishprice(rs.getInt("dishprice"));
        dish.setDishscore(rs.getFloat("dishscore"));
        dish.setPicturename(rs.getString("picturename"));
        dish.setCommentpeople(rs.getInt("commentpeople"));
        dish.setDishname(rs.getString("dishname"));*/
        messagelist.add(message2);
      }
      ps.close();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return messagelist;
  }
  
  public List<String> getbirthday(String canteenid){
    List<String> birthdaylist=new ArrayList<String>();
    String sql="select * from birthdaybook where canteenid="+canteenid;
    PreparedStatement ps=null;
    try
    {
      //DbPool.createConn();
      ps=DbPool.getConn().prepareStatement(sql);
      ResultSet rs=ps.executeQuery();
      while (rs.next())
      {
        String name=rs.getString("name");
        String name2="{\"message\":\""+name+"\"}";
        birthdaylist.add(name2);
      }
      ps.close();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return birthdaylist;
}

public List<Birthdaybookmsg> getbirthdaymessage(String canteenid){
    List<Birthdaybookmsg> birthdaylist=new ArrayList<Birthdaybookmsg>();
    String sql="select * from birthdaybook where canteenid="+canteenid;
    PreparedStatement ps=null;
    try
    {
      //DbPool.createConn();
      ps=DbPool.getConn().prepareStatement(sql);
      ResultSet rs=ps.executeQuery();
      while (rs.next())
      {
        Birthdaybookmsg birth=new Birthdaybookmsg();
        birth.setCanteenid(Integer.valueOf(canteenid));
        birth.setYear(rs.getInt("year"));
        birth.setMonth(rs.getInt("month"));
        birth.setDay(rs.getInt("day"));
        birth.setLunchordinner(rs.getInt("lunchordinner"));
        birth.setPeoplename(rs.getString("name"));
        birth.setPhonenumber(rs.getString("phonenumber"));
        birthdaylist.add(birth);
      }
      ps.close();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return birthdaylist;
  }

public List<String> getcanteenbook(String canteenid){
    List<String> canteenbooklist=new ArrayList<String>();
    String sql="select * from canteenbook where canteenid="+canteenid;
    PreparedStatement ps=null;
    try
    {
      //DbPool.createConn();
      ps=DbPool.getConn().prepareStatement(sql);
      ResultSet rs=ps.executeQuery();
      while (rs.next())
      {
        String name=rs.getString("name");
        String name2="{\"message\":\""+name+"\"}";
        canteenbooklist.add(name2);
      }
      ps.close();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return canteenbooklist;
}

public List<Canteenbookmsg> getcanteenbookmessage(String canteenid){
    List<Canteenbookmsg> canteenbooklist=new ArrayList<Canteenbookmsg>();
    String sql="select * from canteenbook where canteenid="+canteenid;
    PreparedStatement ps=null;
    try
    {
      //DbPool.createConn();
      ps=DbPool.getConn().prepareStatement(sql);
      ResultSet rs=ps.executeQuery();
      while (rs.next())
      {
        Canteenbookmsg canteenbook=new Canteenbookmsg();
        canteenbook.setCanteenid(Integer.valueOf(canteenid));
        canteenbook.setYear(rs.getInt("year"));
        canteenbook.setMonth(rs.getInt("month"));
        canteenbook.setDay(rs.getInt("day"));
        canteenbook.setLunchordinner(rs.getInt("lunchordinner"));
        canteenbook.setPeoplename(rs.getString("name"));
        canteenbook.setPhonenumber(rs.getString("phonenumber"));
        canteenbook.setPeoplenumber(rs.getString("peoplenumber"));
        canteenbooklist.add(canteenbook);
      }
      ps.close();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return canteenbooklist;
  }

public void updatecanteenbookmessage(String name){
    String sql="delete from canteenbook where name=\"" + name + "\"";
    PreparedStatement ps=null;
    try {
      ps=DbPool.getConn().prepareStatement(sql);
      ps.executeUpdate(sql);//÷¥––sql”Ôæ‰
      ps.close();
  } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
  }
  }

public void updatebirthdaybookmessage(String name){
    String sql="delete from birthdaybook where name=\"" + name + "\"";
    PreparedStatement ps=null;
    try {
      ps=DbPool.getConn().prepareStatement(sql);
      ps.executeUpdate(sql);//÷¥––sql”Ôæ‰
      ps.close();
  } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
  }
  }
  
public int changedesc(String canteenid,String desc) {
  int rflag=0;
  String sql="update canteendesc set canteendesc=\""+desc+"\" where canteenid="+canteenid;
  System.out.println(sql);
  PreparedStatement ps=null;
  try
  {
    //DbPool.createConn();
    ps=DbPool.getConn().prepareStatement(sql);
    rflag=ps.executeUpdate();
    ps.close();
    System.out.println("rflag="+rflag);
  }
  catch(Exception e)
  {
    e.printStackTrace();
  }
  return rflag;
} 

public int updatedish(String dishprice,String dishintro,String dishname,String picturename) {
  int rflag=0;
  String sql="update menu set dishprice="+dishprice+",dishname=\""+dishname+"\",dishintroduction=\""
  +dishintro+"\" where picturename=\""+picturename+"\"";
  System.out.println(sql);
  PreparedStatement ps=null;
  try
  {
    //DbPool.createConn();
    ps=DbPool.getConn().prepareStatement(sql);
    rflag=ps.executeUpdate();
    ps.close();
    System.out.println("rflag="+rflag);
  }
  catch(Exception e)
  {
    e.printStackTrace();
  }
  return rflag;
} 


}

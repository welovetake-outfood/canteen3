package com.example1.mycanteen;

import java.io.Serializable;

public class Dish implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String picturename;
  private String dishintrodiction;
  private int dishprice;
  private float dishscore;
  private int canteenid;
  private int commentpeople;
  private String dishname;
  public String getDishname() {
    return dishname;
  }
  public void setDishname(String dishname) {
    this.dishname = dishname;
  }
  public int getCommentpeople() {
    return commentpeople;
  }
  public void setCommentpeople(int commentpeople) {
    this.commentpeople = commentpeople;
  }
  public String getPicturename() {
    return picturename;
  }
  public void setPicturename(String picturename) {
    this.picturename = picturename;
  }
  public String getDishintrodiction() {
    return dishintrodiction;
  }
  public void setDishintrodiction(String dishintrodiction) {
    this.dishintrodiction = dishintrodiction;
  }
  public int getDishprice() {
    return dishprice;
  }
  public void setDishprice(int dishprice) {
    this.dishprice = dishprice;
  }
  public float getDishscore() {
    return dishscore;
  }
  public void setDishscore(float dishscore) {
    this.dishscore = dishscore;
  }
  public int getCanteenid() {
    return canteenid;
  }
  public void setCanteenid(int canteenid) {
    this.canteenid = canteenid;
  }
  
}


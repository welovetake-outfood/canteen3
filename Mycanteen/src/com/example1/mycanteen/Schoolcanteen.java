package com.example1.mycanteen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Schoolcanteen {
  public static class Canteen{
    public Integer id;
    public String name;
    public String desc;
    public Canteen(Integer id,String name,String desc) {
      this.id=id;
      this.name=name;
      this.desc=desc;
    }
    public String toString() {
      return name;
    }
  }
  public static List<Canteen> ITEMS=new ArrayList<Canteen>();
  public static Map<Integer,Canteen> ITEM_MAP=new HashMap<Integer,Canteen>();
  static {
    addItem(new Canteen(1,"xueshi","canteenxueshi"));
    addItem(new Canteen(2,"xueyuan","canteenxueyuan"));
    addItem(new Canteen(3,"heidian","heidian"));
  }
  private static void addItem(Canteen canteen) {
    ITEMS.add(canteen);
    ITEM_MAP.put(canteen.id, canteen);
  }
}

package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.*;
import org.apache.commons.lang.exception.*;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    System.out.println("get");
    //response.getWriter().append("Served at: ").append(request.getContextPath());
    Dao pd=new Dao();
    String id=request.getParameter("userid");
    String password=request.getParameter("password");
    int canteenid=pd.login(id, password);
    response.setContentType("text/html;charset=GBK");
    if (canteenid>0) {
      request.getSession(true).setAttribute("canteenid", canteenid);
    }
    try {
      JSONObject jsonObj=new JSONObject();
      jsonObj.put("canteenid", canteenid);
      System.out.println(jsonObj.toString());
      response.getWriter().println(jsonObj.toString());
    }
    catch(JSONException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    System.out.println("post");
    doGet(request, response);
  }

}

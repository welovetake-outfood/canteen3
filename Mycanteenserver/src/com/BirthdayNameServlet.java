package com;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.sf.json.JSONException;

/**
 * Servlet implementation class CheckBirthdayServlet
 */
@WebServlet("/BirthdayNameServlet")
public class BirthdayNameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BirthdayNameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //response.getWriter().append("Served at: ").append(request.getContextPath());
      Dao pd=new Dao();
    String canteenid=request.getParameter("canteenid");
    List<String> name=pd.getbirthday(canteenid);
    response.setContentType("text/html;charset=GBK");
    request.getSession(true).setAttribute("name", name);
    try {
        net.sf.json.JSONArray jsonarr = net.sf.json.JSONArray.fromObject(name);  //list对象转换成net.sf.JSONArray类型对象
        System.out.println(jsonarr.toString());
        response.getWriter().println(jsonarr.toString());
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
        doGet(request, response);
    }

}

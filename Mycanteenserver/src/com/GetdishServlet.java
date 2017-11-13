package com;

import java.io.IOException;
//import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.json.JSONArray;
import org.json.JSONObject;

import net.sf.json.JSONException;

/**
 * Servlet implementation class GetdishServlet
 */
@WebServlet("/GetdishServlet")
public class GetdishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetdishServlet() {
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
    String picturename=request.getParameter("picturename");
    Dish dish=pd.getadish(picturename);
    response.setContentType("text/html;charset=GBK");
    /*if (canteenid>0) {
      request.getSession(true).setAttribute("canteenid", canteenid);
    }*/
    try {
      JSONObject json=new JSONObject(dish);
      System.out.println(json.toString());
      response.getWriter().println(json.toString());
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

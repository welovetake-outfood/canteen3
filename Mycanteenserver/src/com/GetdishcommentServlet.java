package com;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetdishcommentServlet
 */
@WebServlet("/GetdishcommentServlet")
public class GetdishcommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetdishcommentServlet() {
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
    String dishname=request.getParameter("dishname");
    //String password=request.getParameter("password");
    //String canteenid=request.getParameter("canteenid");
    List<String> dishcomment=pd.getdishcomment(dishname);
    response.setContentType("text/html;charset=GBK");
    request.getSession(true).setAttribute("comment", dishcomment);
    try {
      //JSONArray jsonarr=new JSONArray(dishcomment);
      net.sf.json.JSONArray jsonarr = net.sf.json.JSONArray.fromObject(dishcomment);
      //JSONArray jsonarr=JSONArray.fromObject(dishcomment);
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

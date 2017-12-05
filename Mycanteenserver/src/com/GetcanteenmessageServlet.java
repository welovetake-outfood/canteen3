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
 * Servlet implementation class GetcanteenmessageServlet
 */
@WebServlet("/GetcanteenmessageServlet")
public class GetcanteenmessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetcanteenmessageServlet() {
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
    List<String> canteenmessage=pd.getcanteencomment(canteenid);
    response.setContentType("text/html;charset=GBK");
    request.getSession(true).setAttribute("comment", canteenmessage);
    try {
      net.sf.json.JSONArray jsonarr = net.sf.json.JSONArray.fromObject(canteenmessage);
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

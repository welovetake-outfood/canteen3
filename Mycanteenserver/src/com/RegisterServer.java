package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class RegisterServer
 */
@WebServlet("/RegisterServer")
public class RegisterServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServer() {
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
    String id=request.getParameter("userid");
    String password=request.getParameter("password");
    String canteenid=request.getParameter("canteenid");
    int rflag=pd.register(id, password,canteenid);
    response.setContentType("text/html;charset=GBK");
    if (rflag>0) {
      request.getSession(true).setAttribute("rflag", rflag);
    }
    try {
      JSONObject jsonObj=new JSONObject();
      jsonObj.put("rflag", rflag);
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
		doGet(request, response);
	}

}

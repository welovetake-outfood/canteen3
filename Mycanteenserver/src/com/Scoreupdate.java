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
 * Servlet implementation class Scoreupdate
 */
@WebServlet("/Scoreupdate")
public class Scoreupdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Scoreupdate() {
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
    String dihscore=request.getParameter("dishscore");
    String commentpeople=request.getParameter("commentpeople");
    //int rflag=pd.changescore(Float.parseFloat(dihscore),Integer.valueOf(commentpeople).intValue() , picturename);
    int rflag=pd.changescore(dihscore,commentpeople, picturename);
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

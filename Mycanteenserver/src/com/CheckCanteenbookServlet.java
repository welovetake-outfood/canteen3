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

/**
 * Servlet implementation class CheckBirthdayServlet
 */
@WebServlet("/CheckCanteenbookServlet")
public class CheckCanteenbookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckCanteenbookServlet() {
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
        List<Canteenbookmsg> canteenbookmsg=pd.getcanteenbookmessage(canteenid);
        response.setContentType("text/html;charset=GBK");
        /*if (canteenid>0) {
          request.getSession(true).setAttribute("canteenid", canteenid);
        }*/
        try {
          JSONArray jsonarr=new JSONArray(canteenbookmsg);
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

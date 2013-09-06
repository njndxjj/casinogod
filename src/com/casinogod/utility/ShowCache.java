package com.casinogod.utility;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowCache
 */
public class ShowCache extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowCache() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("ShowCache");
		try {
			
			String result = "<html><body>";
			for(Object key : DataStore.cacheDataMap.keySet().toArray())
			{
				result +=(String)key + ": " + (String)DataStore.cacheDataMap.get(key) + "\r\n<br><br>";
			}
			
			
			for(Object key : DataStore.waitingBattle.keySet().toArray())
			{
				result +=(String)key + ": " + (String)DataStore.cacheDataMap.get(key) + "\r\n<br><br>";
			}
			
			for(Object key : DataStore.fightingBattle.keySet().toArray())
			{
				result +=(String)key + ": " + (String)DataStore.cacheDataMap.get(key) + "\r\n<br><br>";
			}
			
			result += "</body></html>";
			
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

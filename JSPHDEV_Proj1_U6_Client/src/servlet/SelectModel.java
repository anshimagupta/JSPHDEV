package servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Properties;

import clinet.AutoClientSocket;
import clinet.CarModelOptionsIO;

/**
 * Servlet implementation class SelectModel
 * This servlet is used for display all option set and options for user to configure
 */
public class SelectModel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String host = "localhost";
	private int port = 5555;
	private String allModels;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectModel() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Set the response message's MIME type
        response.setContentType("text/html;charset=UTF-8");
        // Allocate a output writer to write the response message into the network socket
        PrintWriter out = response.getWriter();
		
        
		AutoClientSocket servletSocket = new AutoClientSocket(host, port);
		servletSocket.openConnection();
		
		servletSocket.sendAddMore("2");
		servletSocket.sendConfigOption("1");
		allModels = servletSocket.receiveAllModelNames();
		String[] modelNames = allModels.split(";");
		
		out.println("<!DOCTYPE HTML>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Choose a Model</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h3>Welcome! Please pick a car model for configuration.</h3>");
		out.println("<form action='ConfigModel'>");
		out.println("<table border='1px'>");
		out.println("<tr><td>Available Models: </td>");
		out.println("<td><select name='model'>");	
		for(int i = 0; i < modelNames.length; i++)
			out.println("<option value='" + modelNames[i] + "'>" + modelNames[i] + "</option>");
		out.println("</select></td></tr>");
		out.println("<tr><td colspan=2><input type='submit' value='Continue' style='float: right;' />&nbsp;&nbsp;");
        out.println("<input type='button' value='Back' onClick='javascript:history.go(-1)' style='float: right;'/></tr>");
		out.println("</form>");
		out.println("</div></body>");;
		out.println("</html>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

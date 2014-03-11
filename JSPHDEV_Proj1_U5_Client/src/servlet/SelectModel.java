package servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import clinet.AutoClientSocket;

public class SelectModel extends HttpServlet {
	private String host = "localhost";
	private int port = 5555;
	private String allModels;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
        // Set the response message's MIME type
        response.setContentType("text/html;charset=UTF-8");
        // Allocate a output writer to write the response message into the network socket
        PrintWriter out = response.getWriter();
		
		AutoClientSocket servletSocket = new AutoClientSocket(host, port);
		Boolean openConn = servletSocket.openConnection();
		if (openConn == true)
			out.println("Connection opened!");
		servletSocket.sendAddMore("2");
		servletSocket.sendConfigOption("1");
		allModels = servletSocket.receiveAllModelNames();
		String[] modelNames = allModels.split(";");
	
		
		out.println("<!DOCTYPE HTML>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Choose a Model</title>");
		out.println("</head>");
		out.println("<body><div style='padding-left:200px;'>");
		out.println("<h3>Welcome! Please pick a car model for configuration.</h3>");
		out.println("<br/>");
		out.println("<form action='ConfigModel' onSubmit='return validation()'>");
			
        //out.println("<p>" + modelNames.get(0) + "</p><br/>");
		for(int i = 0; i < modelNames.length; i ++)
			out.println("<input type='radio' name='model' value='" + modelNames[i] + "'/>" + modelNames[i] + "<br/><br/>");
        out.println("<br/><input type='submit' value='Continue'/>&nbsp;&nbsp;");
        out.println("<input type='button' value='Back' onClick='javascript:history.go(-1)' />");
		out.println("</form>");
		out.println("</div></body>");

		out.println("<script type='text/Javascript'>");
		//out.println("//<![CDATA[");
		out.println("function validation() {");
		out.println("for(var i = 0; i < document.getElementsByName('model').length; i++)");
		out.println("if(document.getElementsByName('model')[i].checked)");
		out.println("return true;");
		out.println("alert('Please select a car');");
		out.println("return false;");
		out.println("}");
		out.println("//]]>");
		out.println("</script>");
		out.println("</html>");
		out.close();
	}
	
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
        doGet(request, response);
    }
}

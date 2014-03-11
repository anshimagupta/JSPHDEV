package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Automobile;

import clinet.AutoClientSocket;

public class ConfigModel extends HttpServlet {
	private String host = "localhost";
	private int port = 5555;
	private HttpSession session;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		session = request.getSession(true);
		if (session.getAttribute("auto") != null){
			Automobile auto = (Automobile) session.getAttribute("auto");
			Iterator<String> iter = auto.getOpsetList().keySet().iterator();
			while(iter.hasNext()) {
				String opsetName = iter.next();
				String optSelected = request.getParameter(opsetName);
				auto.setOptionChoice(opsetName, optSelected);
			}
		} else {
			String modelName = request.getParameter("model");
	        // Set the response message's MIME type
	        response.setContentType("text/html;charset=UTF-8");
	        // Allocate a output writer to write the response message into the network socket
	        PrintWriter out = response.getWriter();
	        
			AutoClientSocket servletSocket = new AutoClientSocket(host, port);
			servletSocket.sendAddMore("2");
			servletSocket.sendConfigOption("1");
			servletSocket.receiveAllModelNames();
			servletSocket.sendSelectedModel(modelName);
			Automobile auto = servletSocket.receiveSelectedModel();
			request.getSession().setAttribute("auto", auto);
			
			out.println("<!DOCTYPE HTML>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Configure a Model</title>");
			out.println("</head>");
			out.println("<body><div style='padding-left:200px;'>");
			out.println("<h3>Please make configuration for " + modelName + ".</h3>");
			out.println("<br/>");
			out.println("<form action='ConfigModel'>");
			out.println("<table action='ConfigModel' border='1px'>");
			out.println("<tr><td>Car Make</td><td>" + auto.getMake() + "</td></tr>");
			out.println("<tr><td>Car Model</td><td>" + auto.getModel() + "</td></tr>");
			out.println("<tr><td>Car Base Price</td><td>" + auto.getBaseprice() + "</td></tr>");
			
			Iterator<String> iter = auto.getOpsetList().keySet().iterator();
			while(iter.hasNext()) {
				String opsetName = iter.next();
				out.println("<tr><td>" + opsetName + "</td>");
				out.println("<td><select name='" + opsetName + "'>");
				Iterator<String> optIter = auto.getOptions(opsetName).keySet().iterator();
				while(optIter.hasNext()) {
					String optName = optIter.next();
					Float optPrice = auto.getOptions(opsetName).get(optName);
					out.println("<option value='" + optName + "'>" + optName + " - "  + optPrice + "</option>");
				}
				out.println("</select></td>");
				out.println("</tr>");
			}
	        out.println("<tr><td></td><td><input type='submit' value='Continue'/>&nbsp;&nbsp;");
	        out.println("<input type='button' value='Back' onClick='javascript:history.go(-1)' /></td></tr>");
			out.println("</table>");
			out.println("</form>");
			out.println("</div></body>");
			out.println("</html>");
			out.close();
		}
	}
}
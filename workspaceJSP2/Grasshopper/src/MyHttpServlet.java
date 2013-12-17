import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MyHttpServlet")
public class MyHttpServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2244732469487419265L;

	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		// Handle GET-request
		Date d = new Date();
		
		//response.getWriter().write("<html><body>\n");
		//response.getWriter().write("Time: " + d + "\n");
		//response.getWriter().write("</body></html>\n");
		
		request.getRequestDispatcher("header_template.jsp").include(request, response);
		request.getRequestDispatcher("index.jsp").include(request, response);
		request.getRequestDispatcher("footer_template.jsp").include(request, response);
	}

//	public void doPost(HttpServletRequest request, HttpServletResponse response)
	//{
		// Handle POST-request
	//}
	
	protected void service (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String username = "name";
		String password = "password";
		
		String un=request.getParameter("name");
		String pw=request.getParameter("password");
		String msg=" ";
		if(un.equals(username) && pw.equals(password)){
			msg="Hello " + un + "! Your login is sucessful";
		}else{
			msg="Login unsucessful!";
		}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<font size='6' color=red>" + msg + "</font>");
	}
	
}

package servlets;

import helpers.UserSession;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Photos")
public class Photos extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		UserSession.isLogged(session, response);
		
		request.setAttribute("title", "Small World - Photos");
		request.getRequestDispatcher("/photos.jsp").include(request, response);
	}
}

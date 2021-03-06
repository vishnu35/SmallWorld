package servlets;

import helpers.UserSession;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Post;
import datamodels.CommentModel;
import datamodels.LikeModel;
import datamodels.PostModel;
import datamodels.ReportModel;

@WebServlet("/DeletePost")
public class DeletePost extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String PostId = request.getParameter("postid");
		String redirect = request.getParameter("redirect");

		HttpSession session = request.getSession(true);
		String UserId = UserSession.getUserid(session);
		
		PostModel pm = new PostModel();
		Post post = pm.GetPost(PostId);
		if(post.getFromuserid().equals(UserId) || post.getTouserid().equals(UserId)){			
			pm.deletePost(PostId);
			
			LikeModel lm = new LikeModel();
			lm.deleteLikeDislikes(PostId);
			
			CommentModel cm = new CommentModel();
			cm.deleteComments(PostId);
			
			ReportModel rm =new ReportModel();
			rm.deleteReports(PostId);
		}
		
		response.sendRedirect(redirect);
		return;
	}

}

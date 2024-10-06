package controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import java.util.List;

import models.User;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

public class UploadProfilePicServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");

		String nextPage = "signin.jsp";
		
		if(user!=null){
			if(ServletFileUpload.isMultipartContent(request)){
				DiskFileItemFactory dfif = new DiskFileItemFactory();
				ServletFileUpload sfu = new ServletFileUpload(dfif);

				List<FileItem> fileItems = null;
				try{
					fileItems = sfu.parseRequest(request);
				}catch(FileUploadException e){
					e.printStackTrace();
				}
				
				FileItem fileItem = fileItems.get(0);
				String fileName = fileItem.getName();
				
				String uploadPath = getServletContext().getRealPath("/WEB-INF/uploads/"+user.getEmail());
				File file = new File(uploadPath, fileName);
				
				try{
					fileItem.write(file);

					user.setProfpic(fileName);
					user.saveProfPic();
				}catch(Exception e){
					e.printStackTrace();
				}
			}

			nextPage = "profile.jsp";
		}
		
		request.getRequestDispatcher(nextPage).forward(request,response);				
	}
}
package controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import utils.TextLocalSMS;
import utils.OTPGenerator;

public class SendOTPServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();

		String mobile = request.getParameter("mobile");

		System.out.println("################# TextLocal OTP SMS Code ##################");
		String newOtp = OTPGenerator.newOTP();
		session.setAttribute("otp",newOtp);

		System.out.println(TextLocalSMS.sendSms(newOtp,mobile));
		System.out.println("################# TextLocal OTP SMS Code ##################");

		response.getWriter().write("done");
	}
}
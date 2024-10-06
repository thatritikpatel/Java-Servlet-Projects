package listeners;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContext;

import models.Country;
import java.util.ArrayList;

public class AppListener implements ServletContextListener{
	public void contextInitialized(ServletContextEvent e){
		ServletContext context = e.getServletContext();
		
		ArrayList<Country> countries = Country.collectCountries();
		
		context.setAttribute("countries",countries);
	}

	public void contextDestroyed(ServletContextEvent e){
	
	}
}
package example.gjss;

import java.io.IOException;

import org.springframework.web.filter.DelegatingFilterProxy;


import com.sun.grizzly.http.embed.GrizzlyWebServer;
import com.sun.grizzly.http.servlet.ServletAdapter;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;


public class Gjss 
{
    public static void main( String[] args ) throws IOException
    {
        String host = "localhost";
        int port = 8081;
        
    	ServletAdapter jAdapter = new ServletAdapter();
    	jAdapter.setContextPath("/");  
    	
    	// For jersey + Spring
    	jAdapter.setServletInstance(new SpringServlet());
    	jAdapter.addContextParameter("contextConfigLocation", "classpath:spring-context.xml");
    	jAdapter.addServletListener("org.springframework.web.context.ContextLoaderListener");
    	jAdapter.addServletListener("org.springframework.web.context.request.RequestContextListener");
    	
    	// For Spring Security
    	jAdapter.addFilter(new DelegatingFilterProxy(),"springSecurityFilterChain", null);
    	    	        
        
    	GrizzlyWebServer grizzlyServer = new GrizzlyWebServer(host,port,"",false);
        grizzlyServer.addGrizzlyAdapter(jAdapter, new String[] {"/"});
        grizzlyServer.start();
        
        System.out.println("Start running server(host: " + host + ",port: " + Integer.toString(port));
        System.out.println("Press any key to stop the server.");
        System.in.read();
    	grizzlyServer.stop();
    }
}

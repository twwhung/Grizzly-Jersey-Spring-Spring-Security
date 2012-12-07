package example.gjss;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;

@Component
@Path("/")
public class TestResource {
	@GET 
    @Produces("text/html")
	public String getIt()   {												
		return "hello"; 
    }	
}

package sample.jersey;

import org.glassfish.jersey.server.ResourceConfig;

public class Application extends ResourceConfig {

	public Application(){
		packages("sample.jersey.resources");
	}
	
}

package yl288.example.rest.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import yl288.example.rest.jetty.resource.GreetingServer;

public class RestJettyExampleApp {
	private static final Logger logger = LoggerFactory.getLogger(RestJettyExampleApp.class);
	
	public static void main(String[] args) {
		Server server = new Server(8080);

		ResourceHandler resourceHandler = new ResourceHandler();
		resourceHandler.setDirectoriesListed(true);
		resourceHandler.setWelcomeFiles(new String[] { "index.html" });
		resourceHandler.setResourceBase("src/main/webapp");

		ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		servletContextHandler.setContextPath("/api");
		
		ServletHolder holder = servletContextHandler.addServlet(ServletContainer.class, "/*");
		holder.setInitOrder(0);
		
		holder.setInitParameter("jersey.config.server.provider.classnames", GreetingServer.class.getCanonicalName());
		
		HandlerList handlers = new HandlerList(
				servletContextHandler,
				resourceHandler,
				new DefaultHandler());
		
		server.setHandler(handlers);
		
		try {
			server.start();
			server.join();
		} catch (Exception e) {
			logger.error("Failed to start server", e);
		}
	}
}

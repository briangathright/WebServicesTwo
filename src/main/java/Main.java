
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.apache.log4j.BasicConfigurator;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.*;
import org.eclipse.jetty.webapp.WebAppContext;

import com.zewdiemarket.ws.client.ZewdieMarketClient;
import com.zewdiemarket.ws.dal.HibernateSessionHelper;
/**
 * Necessary for Heroku deployment - servers and servlets created
 */
public class Main {
	/**
	 * This function passes the servlet's writer to our client, which then runs our client/test code.]
	 * It also configures the Postgres database with Hibernate (ORM) by using our Heroku database settings.
	 * It then closes thes session factory once the code has finished.
	 */
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//		HibernateSessionHelper.configHibernate();
//		PrintWriter writer = resp.getWriter();
//		ZewdieMarketClient c = new ZewdieMarketClient(writer);
//		c.run();
//		HibernateSessionHelper.getSessionFactory().close();
//	}
	/**
	 * Starts a server on the Heroku instance using the given port. It creates the sever, adds it to the
	 * server, then starts the server.
	 * 
	 */
	public static void main(String[] args) throws Exception {	
		String webappDirLocation = "WebContent/";

		//The port that we should run on can be set into an environment variable
		//Look for that variable and default to 8080 if it isn't there.
		String webPort = System.getenv("PORT");
		if(webPort == null || webPort.isEmpty()) {
			webPort = "8080";
		}

		Server server = new Server(Integer.valueOf(webPort));
		WebAppContext root = new WebAppContext();

		root.setContextPath("/");
		root.setDescriptor(webappDirLocation+"/WEB-INF/web.xml");
		root.setResourceBase(webappDirLocation);

		root.setParentLoaderPriority(true);

		server.setHandler(root);

		server.start();
		server.join();   

		//		Server server = new Server(Integer.valueOf(System.getenv("PORT")));
		//		BasicConfigurator.configure();
		//		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		//		context.setContextPath("/");
		//		server.setHandler(context);
		//		context.addServlet(new ServletHolder(new Main()),"/*");
		//		server.start();
		//		server.join();
	}


}
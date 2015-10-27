
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.BasicConfigurator;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.*;

import com.zewdiemarket.ws.client.ProductServiceClient;
import com.zewdiemarket.ws.dal.HibernateSessionHelper;
/**
 * Necessary for Heroku deployment - servers and servlets created
 */
public class Main extends HttpServlet {
	/**
	 * This function passes the servlet's writer to our client, which then runs our client/test code.]
	 * It also configures the Postgres database with Hibernate (ORM) by using our Heroku database settings.
	 * It then closes thes session factory once the code has finished.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HibernateSessionHelper.configHibernate();
		PrintWriter writer = resp.getWriter();
		ProductServiceClient c = new ProductServiceClient(writer);
		c.run();
		HibernateSessionHelper.getSessionFactory().close();
	}
	/**
	 * Starts a server on the Heroku instance using the given port. It creates the sever, adds it to the
	 * server, then starts the server.
	 * 
	 */
	public static void main(String[] args) throws Exception {
		Server server = new Server(Integer.valueOf(System.getenv("PORT")));
		BasicConfigurator.configure();
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);
		context.addServlet(new ServletHolder(new Main()),"/*");
		server.start();
		server.join();
	}
}
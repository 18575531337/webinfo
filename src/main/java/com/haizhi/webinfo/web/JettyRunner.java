package com.haizhi.webinfo.web;

import com.haizhi.webinfo.config.SpringConfig;
import com.haizhi.webinfo.web.servlet.TestServlet;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Created by haizhi on 2017/9/1.
 */
public class JettyRunner {

    private static int PORT = 8080;

    public static void run(){
        Server server = new Server(8080);
        QueuedThreadPool threadPool = new QueuedThreadPool(
                20,1,30000);

        SslContextFactory sslContextFactory = new SslContextFactory();
        sslContextFactory.setKeyStorePath("src/main/webapp/WEB-INF/keystore");
        sslContextFactory.setKeyStorePassword(  "OBF:1xtb1uo71wg41y0q1y7z1y101wfu1unr1xu7");
        sslContextFactory.setKeyManagerPassword("OBF:1xtb1uo71wg41y0q1y7z1y101wfu1unr1xu7");

        HttpConfiguration httpsConfig = new HttpConfiguration();
        httpsConfig.setSecureScheme("https");
        httpsConfig.setSecurePort(8180);
        httpsConfig.setOutputBufferSize(32768);
        httpsConfig.addCustomizer(new SecureRequestCustomizer());

        ServerConnector conn = new ServerConnector(server,
                new SslConnectionFactory(sslContextFactory,"http/1.1"),
        new HttpConnectionFactory(httpsConfig));
        conn.setPort(8180);
        conn.setIdleTimeout(60000);

        /*
        Configuration.ClassList classlist = Configuration.ClassList
                .setServerDefault(server);
        classlist.addAfter("org.eclipse.jetty.webapp.FragmentConfiguration",
                "org.eclipse.jetty.plus.webapp.EnvConfiguration",
                "org.eclipse.jetty.plus.webapp.PlusConfiguration");
        classlist.addBefore(
                "org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
                "org.eclipse.jetty.annotations.AnnotationConfiguration");
*/
        //Connector c = new LocalConnector();
        //Connector c = new NetworkTrafficServerConnector();

        WebAppContext webAppContext = new WebAppContext();
        initWebappContext(webAppContext);

        ServletContextHandler servletContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
        initServletContext(servletContext);

        ContextHandlerCollection allContext = new ContextHandlerCollection();
        allContext.setHandlers(new Handler[]{
                webAppContext
                ,servletContext
        });


        server.setHandler(allContext);//webAppContext
        server.addBean(threadPool);

        Connector[] connectorList = {conn};
        //server.setConnectors(connectorList);

        try {
            server.setStopAtShutdown(true);
            server.start();
            server.join();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private static void initWebappContext(WebAppContext webAppContext){
        webAppContext.setContextPath("/");
        webAppContext.setResourceBase("src/main/webapp");
        webAppContext.setDescriptor("src/main/webapp/WEB-INF/web.xml");

        //webAppContext.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "false");
        //webAppContext.setInitParameter("org.eclipse.jetty.servlet.Default.useFileMappedBuffer", "false");
        //webAppContext.setAttribute("metadata-complete",false);
        //webAppContext.setAttribute("org.eclipse.jetty.containerInitializerOrder","org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer, com.haizhi.webinfo, *");

    }

    private static void initServletContext(ServletContextHandler servletContext){
        servletContext.setClassLoader(Thread.currentThread().getContextClassLoader());
        servletContext.setContextPath("/api");

        //String path = Thread.currentThread().getContextClassLoader().getResource("com/haizhi/webinfo/web/servlet").getPath();
        //servletContext.setResourceBase(path);
        servletContext.addServlet(new ServletHolder(new TestServlet()),"/test");
        servletContext.addServlet(new ServletHolder(new DispatcherServlet()),"/");
        servletContext.setInitParameter("contextClass", AnnotationConfigWebApplicationContext.class.getName());
        servletContext.setInitParameter("contextConfigLocation", SpringConfig.class.getName());
        //servletContext.addEventListener();
    }
}

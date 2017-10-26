package indi.sword.springmvc._00_listenerbase;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
    监听ServletContext域对象的创建和销毁:
    ServletContextListener接口用于监听ServletContext对象的创建和销毁事件。实现了ServletContextListener接口的类都可以对ServletContext对象的创建和销毁进行监听。
　　当ServletContext对象被创建时，激发contextInitialized (ServletContextEvent sce)方法。
　　当ServletContext对象被销毁时，激发contextDestroyed(ServletContextEvent sce)方法。

　　ServletContext域对象创建和销毁时机：
　　　　创建：服务器启动针对每一个Web应用创建ServletContext
　　　　销毁：服务器关闭前先关闭代表每一个web应用的ServletContext
 */
/*
    MyServletContextListener类实现了ServletContextListener接口，因此可以对ServletContext对象的创建和销毁这两个动作进行监听。
 */
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContext对象创建..." );
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContext对象销毁...");
    }
}

## 代码介绍
### web.xml配置：
```
<context-param>
   <param-name>contextConfigLocation</param-name>
   <param-value>/WEB-INF/spring-context.xml</param-value>
</context-param>
<listener>
   <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>

```
ContextLoaderListener 的作用：启动Web容器时，自动装配ApplicationContext.xml的配置信息。因为它实现了ServletContextListener这个接口，在web.xml配置这个监听器，启动容器时，就会默认执行它实现的方法。

先瞧下代码：
ContextLoaderListener.java
```
public class ContextLoaderListener extends ContextLoader implements ServletContextListener {
    public ContextLoaderListener() {
    }

    public ContextLoaderListener(WebApplicationContext context) {
        super(context);
    }

    public void contextInitialized(ServletContextEvent event) {
        this.initWebApplicationContext(event.getServletContext());
    }

    public void contextDestroyed(ServletContextEvent event) {
        this.closeWebApplicationContext(event.getServletContext());
        ContextCleanupListener.cleanupAttributes(event.getServletContext());
    }
}
```
实现的接口：ServletContextListener.java
```
public interface ServletContextListener extends EventListener {
    void contextInitialized(ServletContextEvent var1);

    void contextDestroyed(ServletContextEvent var1);
}

```

观察 继承的父类ContextLoader的 initWebApplicationContext方法：

```
public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
    if (servletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE) != null) {
        throw new IllegalStateException("Cannot initialize context because there is already a root application context present - check whether you have multiple ContextLoader* definitions in your web.xml!");
    } else {
        Log logger = LogFactory.getLog(ContextLoader.class);
        servletContext.log("Initializing Spring root WebApplicationContext");
        if (logger.isInfoEnabled()) {
            logger.info("Root WebApplicationContext: initialization started");
        }

        long startTime = System.currentTimeMillis();

        try {
            if (this.context == null) {
                this.context = this.createWebApplicationContext(servletContext);
            }

            if (this.context instanceof ConfigurableWebApplicationContext) {
                ConfigurableWebApplicationContext cwac = (ConfigurableWebApplicationContext)this.context;
                if (!cwac.isActive()) {
                    if (cwac.getParent() == null) {
                        ApplicationContext parent = this.loadParentContext(servletContext);
                        cwac.setParent(parent);
                    }

                    this.configureAndRefreshWebApplicationContext(cwac, servletContext);
                }
            }

            servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, this.context);
            ClassLoader ccl = Thread.currentThread().getContextClassLoader();
            if (ccl == ContextLoader.class.getClassLoader()) {
                currentContext = this.context;
            } else if (ccl != null) {
                currentContextPerThread.put(ccl, this.context);
            }

            if (logger.isDebugEnabled()) {
                logger.debug("Published root WebApplicationContext as ServletContext attribute with name [" + WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE + "]");
            }

            if (logger.isInfoEnabled()) {
                long elapsedTime = System.currentTimeMillis() - startTime;
                logger.info("Root WebApplicationContext: initialization completed in " + elapsedTime + " ms");
            }

            return this.context;
        } catch (RuntimeException var8) {
            logger.error("Context initialization failed", var8);
            servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, var8);
            throw var8;
        } catch (Error var9) {
            logger.error("Context initialization failed", var9);
            servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, var9);
            throw var9;
        }
    }
}
```
```
this.configureAndRefreshWebApplicationContext(cwac, servletContext);
```

```
protected void configureAndRefreshWebApplicationContext(ConfigurableWebApplicationContext wac, ServletContext sc) {
    String initParameter;
    if (ObjectUtils.identityToString(wac).equals(wac.getId())) {
        initParameter = sc.getInitParameter("contextId");
        if (initParameter != null) {
            wac.setId(initParameter);
        } else {
            wac.setId(ConfigurableWebApplicationContext.APPLICATION_CONTEXT_ID_PREFIX + ObjectUtils.getDisplayString(sc.getContextPath()));
        }
    }

    wac.setServletContext(sc);
    initParameter = sc.getInitParameter("contextConfigLocation");
    if (initParameter != null) {
        wac.setConfigLocation(initParameter);
    }

    this.customizeContext(sc, wac);
    wac.refresh();
}
```
找到下面语句，这个就是配置到 web.xml里面的 ApplicationContext.xml 的存放位置
```
 initParameter = sc.getInitParameter("contextConfigLocation");

```
### 以下详解:
ApplicationContext.xml这个配置文件部一般默认放置在。applicationContext的默认的路径是”/WEB-INF/applicationContext.xml。也可以在web.xml中配置该文件的其他位置，配置如下：
```
<context-param>
     <param-name>contextConfigLocation</param-name>
    <param-value>
        classpath:applicationContext.xml
        classpath:applicationContext-security.xml;
    </param-value>
</context-param>
```    
org.springframework.web.context.ContextLoaderListener类实现了javax.servlet.ServletContextListener接口。ServletContextListener接口能够监听ServletContext对象的生命周期，因为每个web应用仅有一个ServletContext对象，故实际上该接口监听的是整个web应用。

实现该接口的类在web.xml中作为监听器配置后，当web应用启动后，会触发ServletContextEvent事件，调用ContextLoaderListener的contextInitialized(ServletContextEvent sce)方法。





ContextLoaderListener通过一个ContextLoader对象来初始化Spring容器。在contextInitialized方法中调用contextLoader.initWebApplicationContext(event.getServletContext())。

ContextLoader类的initWebApplicationContext方法即可返回一个WebApplicationContext对象context。并通过 servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, context)将WebApplicationContext对象放置在ServletContext对象中。initWebApplicationContext方法通过调用以下方法实例化并设置WebApplicationContext对象。



下面的代码有可能是另外版本的：

```
protected WebApplicationContext createWebApplicationContext(ServletContext servletContext, ApplicationContext parent)
        throws BeansException
    {
        Class contextClass = determineContextClass(servletContext);//通过servletContext确定WebApplicationContext的具体类型
        if(!(org.springframework.web.context.ConfigurableWebApplicationContext.class).isAssignableFrom(contextClass))
        {
            throw new ApplicationContextException("Custom context class [" + contextClass.getName() + "] is not of type [" + (org.springframework.web.context.ConfigurableWebApplicationContext.class).getName() + "]");
        } else
        {
            ConfigurableWebApplicationContext wac = (ConfigurableWebApplicationContext)BeanUtils.instantiateClass(contextClass);
            wac.setParent(parent);
            wac.setServletContext(servletContext);
            wac.setConfigLocation(servletContext.getInitParameter("contextConfigLocation"));//设置配置文件的路径名
            customizeContext(servletContext, wac);
            wac.refresh();
            return wac;
        }
    }
```
因此可以通过WebApplicationContextUtils.getWebApplicationContext(ServletContext sc)获取WebApplicationContext。内部实现是通过servletContext对象查找该对象，属性名为WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE。






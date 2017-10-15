
在使用springmvc时，都会在web.xml中配置一个dispatchservlet，如下：


```
<listener>    
    <listener-class>    
        org.springframework.web.context.ContextLoaderListener    
    </listener-class>    
</listener>    
    
<!--  Spring MVC Servlet -->    
    
<servlet>    
    <servlet-name>servletName</servlet-name>    
    <servlet-class>    
        org.springframework.web.servlet.DispatcherServlet    
    </servlet-class>    
    <load-on-startup>1</load-on-startup>    
</servlet>    
    
<servlet-mapping>    
    <servlet-name>servletName</servlet-name>    
    <strong><url-pattern>/</url-pattern></strong>    
</servlet-mapping>    
```

其中/和/*的区别：
< url-pattern > / </ url-pattern > 不会匹配到*.jsp，即：*.jsp不会进入spring的 DispatcherServlet类 。
< url-pattern > /* </ url-pattern > 会匹配*.jsp，会出现返回jsp视图时再次进入spring的DispatcherServlet 类，导致找不到对应的controller所以报404错。 


总之，关于web.xml的url映射的小知识:
<url-pattern>/</url-pattern> 会匹配到/login这样的路径型url，不会匹配到模式为*.jsp这样的后缀型url
<url-pattern>/*</url-pattern> 会匹配所有url：路径型的和后缀型的url(包括/login,*.jsp,*.js和*.html等)


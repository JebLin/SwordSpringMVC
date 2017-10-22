xml代码
```
<?xml  version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:context="http://www.springframework.org/schema/context"
    xmlns:mvc="http://www.springframework.org/schema/mvc"
    xsi:schemaLocation="http://www.springframework.org/schema/beans  
        http://www.springframework.org/schema/beans/spring-beans.xsd  
        http://www.springframework.org/schema/context  
        http://www.springframework.org/schema/context/spring-context.xsd  
        http://www.springframework.org/schema/mvc  
        http://www.springframework.org/schema/mvc/spring-mvc.xsd"
    default-autowire="byName">
    <!-- auto register Processor -->
    <context:annotation-config />
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.anxin.msapweb.db.mybatis.mapper" />
    </bean>

    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="db2dataSource" />
    </bean>

    <mvc:interceptors>
        <mvc:interceptor>
            <!-- 需拦截的地址 -->
                        <!-- 一级目录 -->
            <mvc:mapping path="/*.do" />
            <mvc:mapping path="/*.ajax" />
            <mvc:mapping path="/*.htm" />

                        <!-- 二级目录 -->
            <mvc:mapping path="/*/*.do" />
            <mvc:mapping path="/*/*.ajax" />
            <mvc:mapping path="/*/*.htm" />
            <!-- 需排除拦截的地址 -->
            <mvc:exclude-mapping path="/login.htm"/>
            <bean class="com.anxin.msapweb.web.interceptor.SecurityInterceptor" />
        </mvc:interceptor>
    </mvc:interceptors>
</beans>


```
注：不支持  mvc:mapping path="*.do" 

```
package com.anxin.msapweb.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.anxin.msapweb.common.Config;

public class SecurityInterceptor implements HandlerInterceptor {

    private static final String LOGIN_URL = "/login.htm";

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        HttpSession session = req.getSession(true);
        // 从session 里面获取用户名的信息
        Object obj = session.getAttribute(Config.Passport.SESSION_NAME_LOGIN_RESULT);
        // 判断如果没有取到用户信息，就跳转到登陆页面，提示用户进行登陆
        if (obj == null || "".equals(obj.toString())) {
            res.sendRedirect(LOGIN_URL);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse res, Object arg2, ModelAndView arg3) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object arg2, Exception arg3) throws Exception {
    }

}

```
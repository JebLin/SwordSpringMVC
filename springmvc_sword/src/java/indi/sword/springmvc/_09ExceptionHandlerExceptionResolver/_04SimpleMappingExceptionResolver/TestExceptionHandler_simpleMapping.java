package indi.sword.springmvc._09ExceptionHandlerExceptionResolver._04SimpleMappingExceptionResolver;

import indi.sword.springmvc._09ExceptionHandlerExceptionResolver._02ResponseStatusExceptionResolver.UserNameNotMatchPasswordException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/*
    <!-- 配置 SimpleMappingExceptionResolver -->
	<bean class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
		<!-- 重新配置SimpleMappingExceptionResolver的exceptionAttribute值，用于在error.jsp中显示 -->
		<property name="exceptionAttribute" value="ex"></property>
		<property name="exceptionMappings">
			<props>
				<!-- 表示哪个异常，跳转到哪个异常页面 -->
				<prop key="java.lang.ArrayIndexOutOfBoundsException">jsp_exceptionHandler/error</prop>
			</props>
		</property>
	</bean>
 */
@RequestMapping("/exceptionResolver")
@Controller
public class TestExceptionHandler_simpleMapping {

    /*
        提示：
        SimpleMappingExceptionResolver.java 中的 getModelAndView(String viewName, Exception ex, HttpServletRequest request)会返回ModelAndView对象，
        其中有个 this.exceptionAttribute 为异常属性名称，也就是在error.jsp中显示的属性key，
        private String exceptionAttribute = "exception";
        可以在springmvc-servlet.xml中重新配置该属性的key <property name="exceptionAttribute" value="ex"></property>
    }

     */

    @RequestMapping("/testSimpleMappingExceptionResolver")
    public String test(@RequestParam("i") int i){
        String[] vals = new String[10];
        System.out.println("testSimpleMappingExceptionResolver ...");
        System.out.println(vals[i]);
        return "/jsp_exceptionHandler/success";
    }



}

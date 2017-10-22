package indi.sword.springmvc._09ExceptionHandlerExceptionResolver._03DefaultHandlerExceptionResolver;

import indi.sword.springmvc._09ExceptionHandlerExceptionResolver._02ResponseStatusExceptionResolver.UserNameNotMatchPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/exceptionResolver")
@Controller
public class TestExceptionHandler_defaultHandler {

    /*
        验证步骤：
            1、在DefaultHandlerExceptionResolver的 HttpRequestMethodNotSupportedException 异常内加断点
            2、点击index.jsp里面的 按钮。可以查看默认ExceptionHandler内置的处理方法.(因为 method = RequestMethod.POST 不一样）
     */
    @RequestMapping(value = "/testDefaultHandlerExceptionResolver",method = RequestMethod.POST)
    public String test(){
        return "/jsp_exceptionHandler/success";
    }



}

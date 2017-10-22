package indi.sword.springmvc._09ExceptionHandlerExceptionResolver._02ResponseStatusExceptionResolver;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/exceptionResolver")
@Controller
public class TestExceptionHandler_responseStatus {


    /*
        @ResponseStatus 注解标记在类上
     */
    @RequestMapping("/testResponseStatusExceptionResolver_class")
    public String test(@RequestParam("i") int i){

        if(i == 13){
            throw new UserNameNotMatchPasswordException();
        }
        System.out.println("testResponseStatusExceptionResolver_class ...");
        return "/jsp_exceptionHandler/success";
    }

    /*
        @ResponseStatus 注解标记在方法上
     */
    @ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "测试 @ResponseStatus 写在方法上")
    @RequestMapping("/testResponseStatusExceptionResolver_method")
    public String test_responseStatus_method(@RequestParam("i") int i){

        System.out.println("test_responseStatus_method ...");
        return "/jsp_exceptionHandler/success";
    }

}

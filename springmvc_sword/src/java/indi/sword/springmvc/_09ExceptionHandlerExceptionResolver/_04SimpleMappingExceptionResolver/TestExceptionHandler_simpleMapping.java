package indi.sword.springmvc._09ExceptionHandlerExceptionResolver._04SimpleMappingExceptionResolver;

import indi.sword.springmvc._09ExceptionHandlerExceptionResolver._02ResponseStatusExceptionResolver.UserNameNotMatchPasswordException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/exceptionResolver")
@Controller
public class TestExceptionHandler_simpleMapping {

    @RequestMapping("/testSimpleMappingExceptionResolver")
    public String test(@RequestParam("i") int i){
        String[] vals = new String[10];
        System.out.println("testSimpleMappingExceptionResolver ...");
        System.out.println(vals[i]);
        return "/jsp_exceptionHandler/success";
    }



}

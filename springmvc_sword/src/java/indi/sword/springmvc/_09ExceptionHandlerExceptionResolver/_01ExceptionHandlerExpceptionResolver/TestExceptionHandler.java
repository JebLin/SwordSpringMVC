package indi.sword.springmvc._09ExceptionHandlerExceptionResolver._01ExceptionHandlerExpceptionResolver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/exceptionResolver")
@Controller
public class TestExceptionHandler {


    @RequestMapping("/")
    public String test(){
        return "/jsp_exceptionHandler/index";
    }

    @RequestMapping("/testExceptionHandlerExceptionResolver")
    public String test(@RequestParam("i") int i){


        System.out.println("result : " + (10 / i));
        return "/jsp_exceptionHandler/success";
    }

 /*
    @ExceptionHandler({ArithmeticException.class})
    public String handlerArithmeticException(Exception ex){
        System.out.println("出异常了：" + ex);
        return "/jsp_exceptionHandler/error";
    }
*/
    /*
        1.在@ExceptinolHandler 方法的入参中可以加入Exception类型的参数，该参数即对应发生的异常对象。
        2.@ExceptinolHandler 方法的入参中不能传入Map，若希望把异常信息传导到页面上，需要使用ModelAndView作为返回值
        3.@ExceptionHandler 方法标记的异常有优先级的问题。
        4.@ControllerAdvice 如果在当前Handler中找不到@ExceptionHandler 方法来处理当前方法出现的异常，
            则将去@ControllerAdvice 标记的类中查找@ExceptionHandler标记的方法来处理异常。
     */
//    @ExceptionHandler({ArithmeticException.class})
//    public ModelAndView handlerArithmeticException(Exception ex){
//        System.out.println("handlerArithmeticException 出异常了：" + ex);
//        ModelAndView modelAndView = new ModelAndView("/jsp_exceptionHandler/error"); // 视图名称
//        modelAndView.addObject("exceptionDetail",ex);
//        return modelAndView;
//    }

//
//    @ExceptionHandler({RuntimeException.class})
//    public ModelAndView handlerArithmeticException2(Exception ex){
//        System.out.println("handlerArithmeticException2 - 出异常了：" + ex);
//        ModelAndView modelAndView = new ModelAndView("/jsp_exceptionHandler/error"); // 视图名称
//        modelAndView.addObject("exceptionDetail",ex);
//        return modelAndView;
//    }
}

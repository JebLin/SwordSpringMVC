package indi.sword.springmvc._09ExceptionHandlerExceptionResolver._01ExceptionHandlerExpceptionResolver;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/*
       1.在@ExceptinolHandler 方法的入参中可以加入Exception类型的参数，该参数即对应发生的异常对象。
       2.@ExceptinolHandler 方法的入参中不能传入Map，若希望把异常信息传导到页面上，需要使用ModelAndView作为返回值
       3.@ExceptionHandler 方法标记的异常有优先级的问题。
       4.@ControllerAdvice 如果在当前Handler中找不到@ExceptionHandler 方法来处理当前方法出现的异常，
           则将去@ControllerAdvice 标记的类中查找@ExceptionHandler标记的方法来处理异常。
*/
@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler({ArithmeticException.class})
    public ModelAndView handler_ArithmeticException(Exception ex){
        System.out.println("MyControllerAdvice handler_ArithmeticException 出异常了：" + ex);
        ModelAndView modelAndView = new ModelAndView("/jsp_exceptionHandler/error"); // 视图名称
        modelAndView.addObject("exceptionDetail",ex);
        return modelAndView;
    }

//    @ExceptionHandler({ArrayIndexOutOfBoundsException.class})
//    public ModelAndView handler_ArrayIndexOutOfBounds(Exception ex){
//        System.out.println("MyControllerAdvice handler_ArrayIndexOutOfBounds 出异常了：" + ex);
//        ModelAndView modelAndView = new ModelAndView("/jsp_exceptionHandler/error"); // 视图名称
//        modelAndView.addObject("exceptionDetail",ex);
//        return modelAndView;
//    }
}

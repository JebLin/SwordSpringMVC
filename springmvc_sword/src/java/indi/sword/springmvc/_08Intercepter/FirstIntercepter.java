package indi.sword.springmvc._08Intercepter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
    自定义拦截器：
        Spring MVC也可以使用拦截器对请求进行拦截处理，用户可以自定义拦截器来实现特定的功能，自定义的拦截器必须实现HandlerInterceptor接口。
        - preHandle()：这个方法在业务处理器处理请求之前被调用，在该方法中对用户请求 request 进行处理。
            如果程序员决定该拦截器对请求进行拦截处理后还要调用其他的拦截器，或者是业务处理器去进行处理，则返回true；
            如果程序员决定不需要再调用其他的组件去处理请求，则返回false。
        - postHandle()：这个方法在业务处理器处理完请求后，但是DispatcherServlet 向客户端返回响应前被调用，在该方法中对用户请求request进行处理。
        - afterCompletion()：这个方法在 DispatcherServlet 完全处理完请求后被调用，可以在该方法中进行一些资源清理的操作。

 */
public class FirstIntercepter implements HandlerInterceptor {

    /*
     * 该方法在目标方法之前被调用。
     * 若返回值为true，则继续调用后续的拦截器和目标方法。
     * 若返回值为false，则不会再调用后续的拦截器和目标方法。
     *
     * 可以考虑做权限，日志，事务等等等等。
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("[FirstIntercepter]preHandle");
        return true;
    }

    /*
     *  调用目标方法之后，但渲染视图之前。
     *  可以对请求域中的属性或视图做出修改。
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("[FirstIntercepter]postHandle");
    }

    /*
     *  渲染视图之后调用。
     *  可以对请求域中的属性或视图做出修改。
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("[FirstIntercepter]afterCompletion");
    }
}

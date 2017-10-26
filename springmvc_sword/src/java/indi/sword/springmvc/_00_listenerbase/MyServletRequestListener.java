package indi.sword.springmvc._00_listenerbase;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * @Decription MyServletRequestListener类实现了ServletRequestListener接口，
 *                 因此可以对ServletRequest对象的创建和销毁这两个动作进行监听。
 * @Author: rd_jianbin_lin
 * @Date : 2017/10/26 16:40
 */
public class MyServletRequestListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println(sre.getServletRequest() + "销毁了！！");

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println(sre.getServletRequest() + "创建了！！");
    }
}

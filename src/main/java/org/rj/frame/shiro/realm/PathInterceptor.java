package org.rj.frame.shiro.realm;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 设置路径
 * @author : zhuxueke
 * @since : 2017-12-22 9:39
 **/
public class PathInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        // 设置绝对路径
        request.setAttribute("basePath", basePath);
        super.postHandle(request, response, handler, modelAndView);
    }

}

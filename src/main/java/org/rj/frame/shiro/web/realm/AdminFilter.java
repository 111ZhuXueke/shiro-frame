package org.rj.frame.shiro.web.realm;

import com.rui.web.common.enums.Constant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * filter 实现用户登录过滤器
 * @author : zhuxueke
 * @since : 2018-03-20 17:14
 **/
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try{
            String servletPath = request.getServletPath();
            // 过滤静态文件
            if(!servletPath.startsWith("/static/")){
                if(!servletPath.endsWith("signIn")) {
                    if (request.getSession().getAttribute("status") == null) {
                        response.sendRedirect("/signIn");
                        return;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        chain.doFilter(request,response);
        return;
    }

    @Override
    public void destroy() {
        System.out.println("-------------------------过滤器已销毁-------------------------");
    }
}

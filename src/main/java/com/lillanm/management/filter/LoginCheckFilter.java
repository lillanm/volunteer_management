package com.lillanm.management.filter;

import com.alibaba.fastjson.JSON;
import com.lillanm.management.common.BaseContext;
import com.lillanm.management.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "loginCheckFiler",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    //路径匹配符
    public  static  final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //获取本次请求的URI
        String requestURI = request.getRequestURI();

        log.info("拦截到请求：{}",requestURI);

        //定义不需要拦截的路径
        String [] urls = new String[]{
                "/student/login",
                "/student/register",
                "/active",
                "/active/*",
                "/admin/login",
                "/admin/logout",
                "/admin/test"
        };

        //判断本次请求是否需要处理
        boolean check = check(urls, requestURI);
        if (check){
            log.info("本次请求{}不需要拦截",requestURI);
            filterChain.doFilter(request,response);
            return;
        }

        //判断登录状态，如果已经登录，则直接放行
        if (request.getSession().getAttribute("stuId")!=null){
            log.info("用户已登录");
            Long stuId = (Long) request.getSession().getAttribute("stuId");
            BaseContext.setCurrentId(stuId);
            filterChain.doFilter(request,response);
            return;
        }

        if (request.getSession().getAttribute("admId")!=null){
            log.info("用户已登录");
            Long stuId = (Long) request.getSession().getAttribute("admId");
            BaseContext.setCurrentId(stuId);
            filterChain.doFilter(request,response);
            return;
        }

        //用户未登录，给前端一些提示
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
    }



    public boolean check(String[]urls,String requestURI){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url,requestURI);
            if (match){
                return true;
            }
        }
        return false;
    }
}

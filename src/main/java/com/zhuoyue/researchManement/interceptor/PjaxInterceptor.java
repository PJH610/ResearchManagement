package com.zhuoyue.researchManement.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class PjaxInterceptor implements HandlerInterceptor {

    public static final String LAYOUT_PATH = "/manage/layout";

    public static final String PJAX = "_pjax";
    private static final String DESC = "desc";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accept = request.getHeader("Accept");
        if (accept.contains("json")) return true;

        String path = request.getServletPath();
        if (path.indexOf("/api/") == 0) return true;

        String header = request.getHeader("X-PJAX");
        String desc = request.getParameter(DESC);
        if (header == null && !path.contains(LAYOUT_PATH) && desc == null) {
            Enumeration<String> names = request.getParameterNames();
            StringBuilder sb = new StringBuilder();
            while (names.hasMoreElements()) {
                String s = names.nextElement();
                String param = request.getParameter(s);
                if (param == null || param.length() < 1) continue;

                sb.append(s).append("=").append("&");
            }
            String params = sb.length() > 1 ? sb.substring(0, sb.length() - 1) : "";
            request.setAttribute("params", params);
            request.setAttribute("path", path);
            String url = request.getContextPath() + LAYOUT_PATH;
            request.getRequestDispatcher(url).forward(request, response);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

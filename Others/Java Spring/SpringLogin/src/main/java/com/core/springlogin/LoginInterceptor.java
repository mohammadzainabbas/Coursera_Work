/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.core.springlogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author Gonzalo H. Mendoza
 * email: yogonza524@gmail.com
 * StackOverflow: http://stackoverflow.com/users/5079517/gonza
 */
public class LoginInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o) throws Exception {
        HttpSession session = hsr.getSession();
        if (session != null && session.getAttribute("user") != null
                && session.getAttribute("pass") != null) {
            String user = session.getAttribute("user").toString();
            String pass = session.getAttribute("pass").toString();
            if (!user.equalsIgnoreCase("gonza") && !pass.equalsIgnoreCase("hello123")) {
                hsr1.sendRedirect(hsr.getContextPath() + "/login");
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, ModelAndView mav) throws Exception {
        System.out.println("Post handle (Login)");
    }

    @Override
    public void afterCompletion(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, Exception excptn) throws Exception {
        System.out.println("After completion (Login)");
    }
    
    
}

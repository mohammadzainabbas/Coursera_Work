/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.core.springlogin;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author Gonzalo H. Mendoza
 * email: yogonza524@gmail.com
 * StackOverflow: http://stackoverflow.com/users/5079517/gonza
 */
@Controller
public class HomeController {

    @Autowired
    private LoginBean loginBean;
    
    @RequestMapping("/")
    public String home(){
        return "index";
    }
    
    @RequestMapping("/login")
    public String login(@ModelAttribute("loginBean")LoginBean loginbean) {
        return "login";
    }
    
    @RequestMapping("/home")
    public String home(@ModelAttribute("loginBean")LoginBean loginbean,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if (session != null && session.getAttribute("user") != null) {
            return "home";
        }
        response.sendRedirect(request.getContextPath() + "/login");
        return "login";
    }
    
    @RequestMapping(value="/access", method=RequestMethod.POST)
    public String access(HttpServletRequest request, 
            HttpServletResponse response,
            @ModelAttribute("loginBean")LoginBean loginbean,
            Model model){
        if (loginbean.getUsername().equalsIgnoreCase("gonza") &&
                loginbean.getPassword().equalsIgnoreCase("hello123")
                ) {
            request.getSession().setAttribute("user", "gonza");
            request.getSession().setAttribute("pass", "hello123");
            //Success
            return "home";
        }
        return "login";
    }
    
    @RequestMapping("/logout")
    public String logout(@ModelAttribute("loginBean")LoginBean loginBean,
            HttpServletRequest request,
            HttpServletResponse response){
        request.getSession().setAttribute("user", null);
        request.getSession().setAttribute("pass", null);
        request.getSession().invalidate();
        return "login";
    }
}

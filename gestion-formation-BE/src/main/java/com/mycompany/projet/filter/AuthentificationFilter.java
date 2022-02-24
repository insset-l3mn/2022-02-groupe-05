/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author valen
 */
@WebFilter("/*")
public class AuthentificationFilter implements Filter{

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        
        
        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");

        
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        
        
        if(req.getMethod().equals("OPTIONS")){
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            return;
        }
        
        String action = req.getServletPath();
        //System.out.println(action);
        
        /*if("/login.xhtml".equals(action) || "/javax.faces.resource/img/hexagone.png.xhtml".equals(action)){
            filterChain.doFilter(servletRequest, servletResponse);
        }*/
        
        /*if("/login".equals(action) || "/login.jsp".equals(action) || "/login.xhtml".equals(action) || req.getServletPath().contains("/javax.faces.resource") || req.getServletPath().contains("/css") || req.getServletPath().contains("/img") || req.getServletPath().contains("/js")){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }else if("/".equals(action) || "/index.xhtml".equals(action) || "/register.xhtml".equals(action)){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }else{
            Object isLoggedObj = req.getSession().getAttribute("isLoggedIn");
            if(isLoggedObj != null){
                boolean isLoggedIn = (Boolean) isLoggedObj;
                if(isLoggedIn){
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }
            }
            resp.sendRedirect(req.getContextPath()+"/login");
        }*/
        
        filterChain.doFilter(servletRequest, servletResponse);//A retirer si décommenter ce qu'il y a au dessus.
    }
    
}

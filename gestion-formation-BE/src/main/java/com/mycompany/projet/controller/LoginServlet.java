/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet.controller;

import com.mycompany.projet.ejb.UserGestionnary;
import com.mycompany.projet.entities.User;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author valen
 */
@WebServlet({"/login", "/login"})
public class LoginServlet extends HttpServlet{

    @EJB
    private UserGestionnary userGestionnary;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        
        User user = userGestionnary.requestUser(name, password);

        if(user != null){// => Identifiants bons
            HttpSession session = req.getSession();
            session.setAttribute("isLoggedIn", true);
            session.setAttribute("name", user.getUserName());
            session.setAttribute("email", user.getUserEmail());
            session.setAttribute("group", user.getUserGroup());
            
            resp.sendRedirect("welcome.jsp"); //only accessed after login
        }else{
            resp.sendRedirect("login?error=true");
        }
    }
}

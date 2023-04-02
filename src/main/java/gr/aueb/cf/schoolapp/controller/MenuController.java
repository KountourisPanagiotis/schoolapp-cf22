package gr.aueb.cf.schoolapp.controller;

import gr.aueb.cf.schoolapp.authentication.AuthenticationProvider;
import gr.aueb.cf.schoolapp.dto.UserDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/schoolapp/menu")
public class MenuController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}

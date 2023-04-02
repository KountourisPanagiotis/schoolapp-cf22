package gr.aueb.cf.schoolapp.controller;

import gr.aueb.cf.schoolapp.authentication.AuthenticationProvider;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(email);
        userDTO.setPassword(password);

        User user = AuthenticationProvider.authenticate(userDTO);
        if (user == null) response.sendRedirect(request.getContextPath() + "/login");

        HttpSession session = request.getSession(true);
        assert user != null;
        session.setAttribute("username" , user.getUsername());

        //seconds * minutes
        session.setMaxInactiveInterval(60*15);

        Cookie cookie = new Cookie("JSESSIONID", session.getId());
        //we set same cookie maxAge as session Max Age
        cookie.setMaxAge(session.getMaxInactiveInterval());
        response.addCookie(cookie);

        if (AuthenticationProvider.isAdmin(userDTO)) {
            request.getRequestDispatcher("/schoolapp/static/templates/menu.jsp").forward(request, response);
        } else if (AuthenticationProvider.isUser(userDTO)) {
            request.getRequestDispatcher("/schoolapp/static/templates/hellouser.jsp").forward(request, response);
        }
    }
}

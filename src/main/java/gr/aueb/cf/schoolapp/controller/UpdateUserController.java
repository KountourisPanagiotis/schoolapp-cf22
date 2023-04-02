package gr.aueb.cf.schoolapp.controller;

import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.service.IUserService;
import gr.aueb.cf.schoolapp.service.UserServiceImpl;
import gr.aueb.cf.schoolapp.service.exceptions.UserNotFoundException;
import gr.aueb.cf.schoolapp.validation.Validator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/schoolapp/updateuser")
public class UpdateUserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final IUserDAO userDAO = new UserDAOImpl();
    private final IUserService userServ = new UserServiceImpl(userDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/schoolapp/static/templates/userupdate.jsp")
                .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDTO newUserDTO = new UserDTO();
        newUserDTO.setId(id);
        newUserDTO.setUsername(username);
        newUserDTO.setPassword(password);

        request.setAttribute("insertedUser", newUserDTO);

        try {
            String error = Validator.validate(newUserDTO);
            if (!error.equals("")) {
                request.setAttribute("error", error);
                request.getRequestDispatcher("/schoolapp/static/templates/usersmenu.jsp")
                        .forward(request, response);
            }

            userServ.updateUser(newUserDTO);

            request.setAttribute("user", newUserDTO);
            request.getRequestDispatcher("/schoolapp/static/templates/userupdated.jsp")
                    .forward(request, response);
        } catch (UserNotFoundException | UserDAOException e) {
            String message = e.getMessage();
            request.setAttribute("isError", true);
            request.setAttribute("user", newUserDTO);
            request.getRequestDispatcher("/schoolapp/static/templates/userupdated.jsp")
                    .forward(request, response);

        }
    }
}

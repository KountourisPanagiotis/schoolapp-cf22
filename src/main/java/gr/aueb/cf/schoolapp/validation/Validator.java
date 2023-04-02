package gr.aueb.cf.schoolapp.validation;

import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherDTO;
import gr.aueb.cf.schoolapp.dto.UserDTO;

public class Validator {
    static UserDAOImpl dao = new UserDAOImpl();
    private Validator(){}

    public static String validate(TeacherDTO dto) {
        if (dto.getFirstname().equals("")) {
            return "Firstname: Empty";
        }

        if (dto.getFirstname().length() < 3 || (dto.getFirstname().length() > 32)) {
            return "Firstname: Length";
        }

        if (dto.getLastname().equals("")) {
            return "Lastname: Empty";
        }

        if (dto.getLastname().length() < 3 || (dto.getLastname().length() > 32)) {
            return "Lastname: Length";
        }

        return "";
    }

    public static String validate(UserDTO dto) throws UserDAOException {
        if (dto.getUsername().equals("")) {
            return "Username: Empty";
        }

        if (dto.getUsername().length() < 3 || (dto.getUsername().length() > 32)) {
            return "Username: Length";
        }

        if (dto.getPassword().equals("")) {
            return "Password: Empty";
        }

        if(!dto.getUsername().matches("^(.+)@(.+)$")) {
            return "Username: not a valid email address";
        }

        if(!dto.getPassword().matches("(.*[a-z].*)(.*[A-Z].*)(.*[3-32].*)(.*\\W.*)")) {
            return "Password must contain:<br>" +
                    "At least 8 characters.<br>" +
                    "At least 1 lowercase letter.<br>" +
                    "At least 1 uppercase letter.<br>" +
                    "At least 1 number.<br>" +
                    "At least 1 special character";
        }

        //Email Already Taken
        if (dao.getByUsername(dto.getUsername()).size() != 0) {
            return "Email already taken";
        }
        return "";
    }

}

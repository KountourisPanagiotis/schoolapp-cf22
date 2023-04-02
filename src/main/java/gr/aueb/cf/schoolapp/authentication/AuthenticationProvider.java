package gr.aueb.cf.schoolapp.authentication;

import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class AuthenticationProvider {
    private static final IUserDAO userDao = new UserDAOImpl();
    private AuthenticationProvider(){}

    public static User authenticate(UserDTO userDTO) {
        if (!userDao.isUserValid(userDTO.getUsername(), userDTO.getPassword())) {
            return null;
        }
        return new User(userDTO.getId(),userDTO.getUsername(),userDTO.getPassword());
    }

    public static Boolean isAdmin(UserDTO userDTO) {
        String envpassword = System.getenv("TS_ADMIN_PASSWORD");
        if(userDTO.getUsername().equals("admin@aueb.gr")) {
            userDTO.setUsername("admin");
        }
        return (userDTO.getUsername().equals("admin") && (userDTO.getPassword().equals(envpassword)));
    }

    public static Boolean isUser(UserDTO userDTO) {
        List<User> users;
        int listSize;

        try {
            users = userDao.getByUsername(userDTO.getUsername());
            listSize = users.size();

            if(listSize == 0) return false;

            for (int i = 0; i < listSize; i++) {
                if (users.get(i).getUsername().equals(userDTO.getUsername())
                        && BCrypt.checkpw(userDTO.getPassword(), users.get(i).getPassword())) {
                    return true;
                }
            }
        } catch (UserDAOException e) {
            e.printStackTrace();
        }

        return false;
    }
}

package gr.aueb.cf.schoolapp.service.exceptions;

import gr.aueb.cf.schoolapp.model.User;

public class UserNotFoundException extends Exception {
    private final static long serialVersionUID = 1L;

    public UserNotFoundException(User user) {
        super("User with the id " + user.getId() + " does not exist");
    }

    public UserNotFoundException(String s) {
        super(s);
    }
}

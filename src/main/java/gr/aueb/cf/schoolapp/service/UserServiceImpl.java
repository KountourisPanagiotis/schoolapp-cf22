package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.authentication.EncryptionProvider;
import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.exceptions.UserNotFoundException;
import gr.aueb.cf.schoolapp.validation.Validator;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements IUserService {

    private final IUserDAO userDAO;

    public UserServiceImpl(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User insertUser(UserDTO userToInsert) throws UserDAOException {
        if (userToInsert == null) return null;
        if (Validator.validate(userToInsert) != "") return null;

        try {
            User user = mapUser(EncryptionProvider.encrypt(userToInsert));
            return userDAO.insert(user);
        } catch (UserDAOException e) {
            throw e;
        }
    }

    @Override
    public User updateUser(UserDTO userToUpdate) throws UserDAOException, UserNotFoundException {
        if (userToUpdate == null) return null;
        if (Validator.validate(userToUpdate) != "") return null;

        try {
            if (userDAO.getById(userToUpdate.getId()) == null) {
                throw new UserNotFoundException("User with id " + userToUpdate.getId() + " was not found");
            }
            User user = mapUser(EncryptionProvider.encrypt(userToUpdate));

            return userDAO.update(user);
        } catch (UserDAOException | UserNotFoundException e) {
            throw e;
        }
    }

    @Override
    public void deleteUser(int id) throws UserDAOException, UserNotFoundException {
        try {
            if (userDAO.getById(id) == null) {
                throw new UserNotFoundException("User with id " + id + " was not found");
            }
            userDAO.delete(id);
        } catch (UserDAOException | UserNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<User> getUsersByUsername(String username) throws UserDAOException {
        List<User> users = new ArrayList<>();
        if (username == null) return users;

        try {
            users = userDAO.getByUsername(username);
            return users;
        } catch (UserDAOException e) {
            throw e;
        }
    }

    private User mapUser(UserDTO dto) {
        return new User(dto.getId(),dto.getUsername(),dto.getPassword());
    }
}


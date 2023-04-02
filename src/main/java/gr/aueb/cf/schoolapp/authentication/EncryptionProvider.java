package gr.aueb.cf.schoolapp.authentication;

import gr.aueb.cf.schoolapp.dto.UserDTO;
import org.mindrot.jbcrypt.BCrypt;

public class EncryptionProvider {

    public static UserDTO encrypt(UserDTO userDTO) {
        int workload = 12;
        String inputPassword = userDTO.getPassword().trim();
        String salt = BCrypt.gensalt(workload);

        String hashedPassword = BCrypt.hashpw(inputPassword, salt);
        UserDTO userDTOhashed = new UserDTO();

        userDTOhashed.setId(userDTO.getId());
        userDTOhashed.setUsername(userDTO.getUsername());
        userDTOhashed.setPassword(hashedPassword);

       return userDTOhashed;
    }
}

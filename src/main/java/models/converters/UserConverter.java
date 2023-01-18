package models.converters;

import models.DTO.RouteDTO;
import models.DTO.UserDTO;
import models.entity.Route;
import models.entity.User;
import models.view.RouteView;
import models.view.UserView;

public class UserConverter {
    public static User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setUserID(userDTO.getUserID());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setFullname(userDTO.getFullname());
        user.setPhone(userDTO.getPhone());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        return user;
    }

    public static UserDTO toDTO(UserView userView) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserID(userView.getUserID());
        userDTO.setUsername(userView.getUsername());
        userDTO.setFullname(userView.getFullname());
        userDTO.setPhone(userView.getPhone());
        userDTO.setEmail(userView.getEmail());
        userDTO.setRole(userView.getRole());
        return userDTO;
    }

    public static UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserID(user.getUserID());
        userDTO.setUsername(user.getUsername());
        userDTO.setFullname(user.getFullname());
        userDTO.setPhone(user.getPhone());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    public static UserView toView(UserDTO userDTO) {
        UserView userView = new UserView();
        userView.setUserID(userDTO.getUserID());
        userView.setUsername(userDTO.getUsername());
        userView.setFullname(userDTO.getFullname());
        userView.setPhone(userDTO.getPhone());
        userView.setEmail(userDTO.getEmail());
        userView.setRole(userDTO.getRole());
        return userView;
    }
}

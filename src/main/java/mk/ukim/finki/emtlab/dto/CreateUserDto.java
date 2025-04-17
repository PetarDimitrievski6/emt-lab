package mk.ukim.finki.emtlab.dto;

import mk.ukim.finki.emtlab.model.enumerations.Role;
import mk.ukim.finki.emtlab.model.domain.User;

public record CreateUserDto(
        String username,
        String password,
        String repeatPassword,
        String name,
        String surname,
        Role role
) {
    public User toUser() {
        return new User(username, password, name, surname, role);
    }
}

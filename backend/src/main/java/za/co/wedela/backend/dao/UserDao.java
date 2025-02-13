package za.co.wedela.backend.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import za.co.wedela.backend.model.role.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDao {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Role role;
}

package dao;

import model.Role;
import java.util.List;

public interface RoleDAO {
    void addRole(Role role) throws Exception;
    Role getRoleById(int id) throws Exception;
    List<Role> getAllRoles() throws Exception;
    void updateRole(Role role) throws Exception;
    void deleteRole(int id) throws Exception;
}

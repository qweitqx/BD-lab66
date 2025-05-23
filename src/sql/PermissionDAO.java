package dao;

import model.Permission;
import java.util.List;

public interface PermissionDAO {
    void addPermission(Permission permission) throws Exception;
    Permission getPermissionById(int id) throws Exception;
    List<Permission> getAllPermissions() throws Exception;
    void updatePermission(Permission permission) throws Exception;
    void deletePermission(int id) throws Exception;
}

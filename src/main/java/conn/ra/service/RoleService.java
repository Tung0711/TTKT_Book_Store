package conn.ra.service;

import conn.ra.model.entity.Role;
import conn.ra.model.enums.ERole;

import java.util.List;

public interface RoleService {
    Role findByRoleName(ERole name);
    List<Role> getAll();
}

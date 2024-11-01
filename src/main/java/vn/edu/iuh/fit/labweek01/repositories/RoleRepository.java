/*
 * @ (#) RoleRepository.java       1.0     10/31/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.labweek01.repositories;

import vn.edu.iuh.fit.labweek01.models.Role;

import java.util.List;
import java.util.Optional;

/*
 * @description:
 * @author: Luong Tan Dat
 * @date: 10/31/2024
 */
public interface RoleRepository {
    public boolean createRole(Role role);
    public boolean updateRole(Role role);
    public boolean deleteRole(Role role);
    public List<Role> findAll();
    public List<Role> getRoleByAccount(String accountId);
}

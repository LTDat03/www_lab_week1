/*
 * @ (#) RoleRepositoryImpl.java       1.0     10/31/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.labweek01.repositories.Impl;
/*
 * @description:
 * @author: Luong Tan Dat
 * @date: 10/31/2024
 */

import vn.edu.iuh.fit.labweek01.connectionDB.ConnectionDB;
import vn.edu.iuh.fit.labweek01.enums.Status;
import vn.edu.iuh.fit.labweek01.models.Role;
import vn.edu.iuh.fit.labweek01.repositories.RoleRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleRepositoryImpl implements RoleRepository {
    Connection connection = new ConnectionDB().getConnection();

    @Override
    public boolean createRole(Role role) {
        String sql = "INSERT INTO roles VALUES (?, ?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, role.getRoleId());
            preparedStatement.setString(2, role.getRoleName());
            preparedStatement.setString(3, role.getDescription());
            preparedStatement.setInt(4, role.getStatus().getCode());
            preparedStatement.executeQuery();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateRole(Role role) {
        String sql = "UPDATE roles SET roleName = ?, description = ?, status = ? WHERE roleId = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, role.getRoleName());
            preparedStatement.setString(2, role.getDescription());
            preparedStatement.setInt(3, role.getStatus().getCode());
            preparedStatement.setString(4, role.getRoleId());
            preparedStatement.executeQuery();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteRole(Role role) {
        String sql = "UPDATE roles SET status = -1 WHERE roleId = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, role.getRoleId());
            preparedStatement.executeQuery();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Role> findAll() {
        List<Role> roleList = new ArrayList<>();
        String sql = "SELECT * FROM roles WHERE status = 1";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Status status = Status.findByCode(resultSet.getInt("status"));
                roleList.add(new Role(resultSet.getString("roleId"), resultSet.getString("roleName"), resultSet.getString("description"), status));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roleList;
    }

    @Override
    public List<Role> getRoleByAccount(String accountId) {
        List<Role> roleList = new ArrayList<>();
        String sql = "select * from account join grant_access on account.accountId = grant_access.accountId join roles on grant_access.roleId = roles.roleId where account.accountId = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Status status = Status.findByCode(resultSet.getInt("status"));
                roleList.add(new Role(resultSet.getString("roleId"), resultSet.getString("roleName"), resultSet.getString("description"), status));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return roleList;
    }
}

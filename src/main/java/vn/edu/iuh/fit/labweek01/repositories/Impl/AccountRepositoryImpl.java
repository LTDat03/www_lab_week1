/*
 * @ (#) AccountRepositoryImpl.java       1.0     10/31/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.labweek01.repositories.Impl;
/*
 * @description:
 * @author: Luong Tan Dat
 * @date: 10/31/2024
 */

import jdk.jshell.spi.SPIResolutionException;
import vn.edu.iuh.fit.labweek01.connectionDB.ConnectionDB;
import vn.edu.iuh.fit.labweek01.enums.Status;
import vn.edu.iuh.fit.labweek01.models.Account;
import vn.edu.iuh.fit.labweek01.repositories.AccountRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountRepositoryImpl implements AccountRepository {
    Connection connection = new ConnectionDB().getConnection();

    @Override
    public boolean createAccount(Account account) {
        String sql = "INSERT INTO account VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getAccountId());
            preparedStatement.setString(2, account.getFullName());
            preparedStatement.setString(3, account.getPassword());
            preparedStatement.setString(4, account.getEmail());
            preparedStatement.setString(5, account.getPhone());
            preparedStatement.setInt(6, account.getStatus().getCode());
            preparedStatement.executeQuery();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateAccount(Account account) {
        String sql = "UPDATE account set full_name = ?, password = ?, email = ?, phone = ?, status = ? where account_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getFullName());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setString(3, account.getEmail());
            preparedStatement.setString(4, account.getPhone());
            preparedStatement.setInt(5, account.getStatus().getCode());
            preparedStatement.setString(6, account.getAccountId());
            preparedStatement.executeQuery();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteAccount(Account account) {
        String sql = "UPDATE account set status = -1 where account_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getAccountId());
            preparedStatement.executeQuery();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accountList = new ArrayList<>();
        String sql = "SELECT * FROM account where status = 1 or status = 0";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Status status = Status.findByCode(resultSet.getInt("status"));
                accountList.add(new Account(resultSet.getString("account_id"),
                        resultSet.getString("full_name"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        status));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountList;
    }

    @Override
    public Optional<Account> findById(String id) {
        String sql = "SELECT * FROM account where account_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Status status = Status.findByCode(resultSet.getInt("status"));
                return Optional.of(new Account(resultSet.getString("account_id"),
                        resultSet.getString("full_name"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        status));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}

/*
 * @ (#) LogRepositoryImpl.java       1.0     10/31/2024
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
import vn.edu.iuh.fit.labweek01.models.Account;
import vn.edu.iuh.fit.labweek01.models.Log;
import vn.edu.iuh.fit.labweek01.repositories.LogRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class LogRepositoryImpl implements LogRepository {
    Connection connection = new ConnectionDB().getConnection();

    @Override
    public boolean createLog(Log log) {
        String sql = "INSERT INTO log(account_id,login_time,logout_time,notes) VALUES(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, log.getAccount().getAccountId());
            preparedStatement.setTimestamp(2, new Timestamp(log.getLoginTime().getTime()));
            preparedStatement.setTimestamp(3, null);
            preparedStatement.setString(4, log.getNotes());
            preparedStatement.executeQuery();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateLog(Log log) {
        String sql = "UPDATE log SET account_id = ?,login_time = ?, logout_time = ?, notes = ? WHERE id = ?";
        try {
            Log lastLog = this.getLastLogByAccountId(log.getAccount().getAccountId());
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, log.getAccount().getAccountId());
            preparedStatement.setTimestamp(2, new Timestamp(log.getLoginTime().getTime()));
            preparedStatement.setTimestamp(3, new Timestamp(log.getLogoutTime().getTime()));
            preparedStatement.setString(4, log.getNotes());
            preparedStatement.setString(5, lastLog.getId());
            preparedStatement.executeQuery();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteLog(Log log) {
        String sql = "DELETE FROM log WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, log.getId());
            preparedStatement.executeQuery();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Log> findAll() {
        List<Log> logList = new ArrayList<>();
        String sql = "SELECT * FROM log";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Account account = new Account(resultSet.getString("account_id"), resultSet.getString("full_name"), resultSet.getString("password"), resultSet.getString("email"), resultSet.getString("phone"), Status.findByCode(resultSet.getInt("status")));
                if (resultSet.getDate("logout_time") == null) {
                    logList.add(new Log(resultSet.getString("id"), new Timestamp(resultSet.getTimestamp("login_time").getTime()), account, resultSet.getString("notes")));
                } else {
                    logList.add(new Log(resultSet.getString("id"), account, new Timestamp(resultSet.getTimestamp("login_time").getTime()), new Timestamp(resultSet.getTimestamp("logout_time").getTime()), resultSet.getString("notes")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(logList);
        return logList;
    }

    public Log getLastLogByAccountId(String accountId) {
        String sql = "SELECT * FROM log WHERE account_id = ? ORDER BY login_time DESC LIMIT 1";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            if (resultSet.next()) {
                Account account = new Account(resultSet.getString("account_id"));
                if (resultSet.getTimestamp("logout_time") == null) {
                    return new Log(resultSet.getString("id"), account, resultSet.getTimestamp("login_time"), null, resultSet.getString("notes"));
                } else {
                    return new Log(resultSet.getString("id"), account, resultSet.getTimestamp("login_time"), resultSet.getTimestamp("logout_time"), resultSet.getString("notes"));
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

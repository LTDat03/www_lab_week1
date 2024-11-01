/*
 * @ (#) Log.java       1.0     10/31/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.labweek01.models;
/*
 * @description:
 * @author: Luong Tan Dat
 * @date: 10/31/2024
 */

import java.sql.Timestamp;

public class Log {
    private String id;
    private Account account;
    private Timestamp loginTime;
    private Timestamp logoutTime;
    private String notes;

    public Log(String notes, Timestamp loginTime, Account account, String id) {
        this.notes = notes;
        this.loginTime = loginTime;
        this.account = account;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    public Timestamp getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Timestamp logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Log() {
    }

    public Log(String id, Account account, Timestamp loginTime, Timestamp logoutTime, String notes) {
        this.id = id;
        this.account = account;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id='" + id + '\'' +
                ", account=" + account +
                ", loginTime=" + loginTime +
                ", logoutTime=" + logoutTime +
                ", notes='" + notes + '\'' +
                '}';
    }
}

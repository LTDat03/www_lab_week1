/*
 * @ (#) GrantAccess.java       1.0     10/31/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.labweek01.models;
/*
 * @description:
 * @author: Luong Tan Dat
 * @date: 10/31/2024
 */

import vn.edu.iuh.fit.labweek01.enums.Is_grant;

public class GrantAccess {
    private String accountId;
    private String roleId;
    private Is_grant is_grant;
    private String note;

    @Override
    public String toString() {
        return "GrantAccess{" +
                "accountId='" + accountId + '\'' +
                ", roleId='" + roleId + '\'' +
                ", is_grant=" + is_grant +
                ", note='" + note + '\'' +
                '}';
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Is_grant getIs_grant() {
        return is_grant;
    }

    public void setIs_grant(Is_grant is_grant) {
        this.is_grant = is_grant;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public GrantAccess() {
    }

    public GrantAccess(String accountId, String roleId, Is_grant is_grant, String note) {
        this.accountId = accountId;
        this.roleId = roleId;
        this.is_grant = is_grant;
        this.note = note;
    }
}

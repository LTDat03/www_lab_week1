/*
 * @ (#) Account.java       1.0     10/31/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.labweek01.repositories;
/*
 * @description:
 * @author: Luong Tan Dat
 * @date: 10/31/2024
 */

import vn.edu.iuh.fit.labweek01.models.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    public boolean createAccount(Account account);
    public boolean updateAccount(Account account);
    public boolean deleteAccount(Account account);
    public List<Account> findAll();
    public Optional<Account> findById(String id);
}

/*
 * @ (#) LogRepository.java       1.0     10/31/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.labweek01.repositories;
/*
 * @description:
 * @author: Luong Tan Dat
 * @date: 10/31/2024
 */

import vn.edu.iuh.fit.labweek01.models.Log;

import java.util.List;
import java.util.Optional;

public interface LogRepository {
    public boolean createLog(Log log);
    public boolean updateLog(Log log);
    public boolean deleteLog(Log log);
    public List<Log> findAll();
}

/*
 * @ (#) Status.java       1.0     10/31/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.labweek01.enums;
/*
 * @description:
 * @author: Luong Tan Dat
 * @date: 10/31/2024
 */

public enum Status {
    ACTIVE(1, "Active"),

    DEACTIVATE(0, "Deactivate"),

    DELETE(-1, "Delete");

    private final int code;
    private final String description;

    public int getCode() {
        return code;
    }

    public String getDescription(){
        return description;
    }

    Status(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static Status findByCode(int code){
        for(Status status : Status.values()){
            if(status.getCode() == code){
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid code : " + code);
    }
}

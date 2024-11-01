package vn.edu.iuh.fit.labweek01.enums;

public enum Is_grant {
    disable(0, "Disable"),
    enable(1, "Enable");
    private final int code;
    private final String description;

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    Is_grant(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static Is_grant findByCode(int code) {
        for (Is_grant is_grant : Is_grant.values()) {
            if (is_grant.getCode() == code) {
                return is_grant;
            }
        }
        throw new IllegalArgumentException("Invalid code : " + code);
    }
}

package demo.enumer;

public enum UserRole {

    ROLE_ROOT_ADMIN("系统管理员", 100000),  // 系统管理员

    ROLE_ORDER_ADMIN("订单管理员", 200000), // 订单管理员

    ROLE_NORMAL("普通用户", 300000);       // 普通用户

    private String roleName;

    private Integer roleCode;

    UserRole(String roleName, Integer roleCode) {
        this.roleCode = roleCode;
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public Integer getRoleCode() {
        return roleCode;
    }

    public static Integer getRoleCodeByRoleName(String roleName) {
        for(UserRole role : UserRole.values()) {
            if(role.getRoleName().equals(roleName)) {
                return role.getRoleCode();
            }
        }
        return null;
    }
}
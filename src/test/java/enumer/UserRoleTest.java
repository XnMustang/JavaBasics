package enumer;

import demo.enumer.UserRole;
import org.junit.Test;

public class UserRoleTest {

    @Test
    public void testEnum() {
        UserRole role1 = UserRole.ROLE_ROOT_ADMIN;
        UserRole role2 = UserRole.ROLE_ORDER_ADMIN;
        UserRole role3 = UserRole.ROLE_NORMAL;

        // values()方法：返回所有枚举常量的数组集合
        for (UserRole role : UserRole.values()){
            System.out.println(role);
        }

        System.out.println("---------------------");
        // ordinal() 返回枚举常量的序数  从0开始
        System.out.println(role1.ordinal());
        System.out.println(role2.ordinal());
        System.out.println(role3.ordinal());

        System.out.println("---------------------");
        // compareTo()方法：枚举常量间的比较
        System.out.println(role1.compareTo(role1));
        System.out.println(role1.compareTo(role2));
        System.out.println(role2.compareTo(role3));
        System.out.println(role1.compareTo(role3));

        System.out.println("---------------------");
        // name() 获取枚举常量的名称
        System.out.println(role1.name());

        System.out.println("---------------------");
        // valueOf()方法：返回指定名称的枚举常量
        System.out.println(UserRole.valueOf( "ROLE_ROOT_ADMIN" ) );
        System.out.println(UserRole.valueOf( "ROLE_ORDER_ADMIN" ) );
        System.out.println(UserRole.valueOf( "ROLE_NORMAL" ) );

        System.out.println("---------------------");
        Integer roleCodeByRoleName = UserRole.getRoleCodeByRoleName("系统管理员");
        System.out.println(roleCodeByRoleName);
    }

    @Test
    public void testEnum2() {
        UserRole role = UserRole.ROLE_ROOT_ADMIN;
        switch (role) {
            case ROLE_NORMAL:
                System.out.println("普通角色");
                break;
            case ROLE_ORDER_ADMIN:
                System.out.println("订单管理员");
                break;
            case ROLE_ROOT_ADMIN:
                System.out.println("系统管理员");
                break;
        }
    }

}

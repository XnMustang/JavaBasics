package enumer;

import demo.enuminteface.RoleEnum;
import org.junit.Test;

public class RoleEnumTest {

    public String judge(String roleName) {
        return RoleEnum.valueOf(roleName).operation();
    }

    @Test
    public void testJudge() {
        String role_order_admin = judge("ROLE_ORDER_ADMIN");
        System.out.println(role_order_admin);
    }

}

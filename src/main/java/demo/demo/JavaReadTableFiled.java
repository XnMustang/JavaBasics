package demo.demo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class JavaReadTableFiled {

    public static void main(String[] args) {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/redis_ idempotent?serverTimezone=GMT";
        String user = "root";
        String password = "123456";

        try {
            printTableStructure(driver, url, user, password);
        } catch (Exception e) {
            System.out.println("error la la ");
            e.printStackTrace();
        }
    }

    private static void printTableStructure(String driver, String url, String user, String password) throws Exception {
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);

        DatabaseMetaData metaData = connection.getMetaData();
        // 获取所有表
        ResultSet tableResultSet = metaData.getTables(null, null, null, new String[]{"TABLE"});
        while (tableResultSet.next()) {
            String tableName = tableResultSet.getString("TABLE_NAME");
            System.out.println("table:" + tableName);

            // 获取表字段结构
            ResultSet columnResultSet = metaData.getColumns(null, "%", tableName, "%");
            while (columnResultSet.next()) {
                // 字段名称
                String columnName = columnResultSet.getString("COLUMN_NAME");
                // 数据类型
                String columnType = columnResultSet.getString("TYPE_NAME");
                // 字段长度
                int datasize = columnResultSet.getInt("COLUMN_SIZE");
                // 小数部分位数
                int digits = columnResultSet.getInt("DECIMAL_DIGITS");
                // 是否可为空 1代表可空 0代表不可为空
                int nullable = columnResultSet.getInt("NULLABLE");
                // 描述
                String remarks = columnResultSet.getString("REMARKS");
                System.out.println(columnName + " | " + columnType + " | " + datasize + " | " + digits + " | " + nullable + " | " + remarks);
            }
            System.out.println("=================================");
        }
    }
}

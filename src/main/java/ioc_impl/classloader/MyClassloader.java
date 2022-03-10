package ioc_impl.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

/**
 * 自定义类加载器
 */
public class MyClassloader extends ClassLoader {

    /**
     * 加载类
     *
     * @param classAbsolutePath 类文件的绝对路径
     * @param className 类名称
     * @return  类实例
     */
    public Class loadClass(String classAbsolutePath, String className) {
        try {
            Class<?> clazz = MyClassloader.class.getClassLoader().loadClass(className);
            // 已经加载的直接返回
            if(clazz != null) {
                return clazz;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 处理没有加载的
        byte[] data = loadDate(classAbsolutePath);
        // 将字节数组转换为类的实例
        return defineClass(className, data, 0, data.length);
    }

    /**
     * 获取字节数组
     *
     * @param classAbsolutePath 类的绝对路径
     * @return  类的字节数组
     */
    private byte[] loadDate(String classAbsolutePath) {
        try {
            FileInputStream inputStream = new FileInputStream(classAbsolutePath);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int b;
            while ((b=inputStream.read()) != -1){
                byteArrayOutputStream.write(b);
            }
            inputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

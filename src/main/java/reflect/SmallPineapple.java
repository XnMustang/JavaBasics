package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射常用的功能：
 * 在运行时获取一个类的CLass对象
 * 在运行时构造一个类的实力化对象
 * 在运行时获取一个类的所有信息：变量、方法、构造器、注解
 */
public class SmallPineapple {

    public String name;
    public int age;
    private double weight; // 体重只有自己知道

    public SmallPineapple (){

    }

    public SmallPineapple(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void getInfo() {
        System.out.print("["+ name + " 的年龄是：" + age + "]");
    }

    public static void main(String[] args) throws Exception {
        test05();
    }

    /**
     * 获取Class对象的三种方式
     * 1 类名.class
     * 2 实例.getClass()
     * 3 CLass.forName(类全限定名)
     */
    public void test01() throws Exception {
        Class class1 = SmallPineapple.class;
        System.out.println(class1);

        SmallPineapple smallPineapple = new SmallPineapple();
        Class<? extends SmallPineapple> aClass = smallPineapple.getClass();
        System.out.println(aClass == class1);

        Class<?> forName = Class.forName("reflect.SmallPineapple");
        System.out.println(forName == aClass);
        /**
         * jvm中只有一个Class对象，因为类家在加载机制和双亲委派机制，保证程序运行时，加载类时每个类在内存中仅会产生一个Class对象
         */
    }

    // 通过反射构造类的实例化对象 2种方式
    public static void test02() throws Exception {
        Class<SmallPineapple> smallPineappleClass = SmallPineapple.class;
        // newInstance() 默认会调用无参构造方法
        SmallPineapple smallPineapple = smallPineappleClass.newInstance();
        smallPineapple.getInfo();

        Class<?> aClass = Class.forName("reflect.SmallPineapple");
        // 获取指定参数类型的构造，参数顺序对应类中从上向下
        Constructor<?> constructor = aClass.getConstructor(String.class, int.class);
        // 将反射对象中的 accessible 标志位设置为 true，就意味着允许客户端拥有超级权限
        constructor.setAccessible(true);
        // newInstance传入构造方法的值，可以构建一个内部属性已经被复制的实例
        SmallPineapple smallPineapple2 = (SmallPineapple) constructor.newInstance("zhangsan", 19);
        smallPineapple2.getInfo();
    }

    // 获取类中的变量 Field
    public static void test03() throws Exception {
        Class<SmallPineapple> smallPineappleClass = SmallPineapple.class;
        // getFields() 获取所有被public修饰的变量
        Field[] fields = smallPineappleClass.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }

        // getField() 根据变量名获取类的一个变量，该变量必须被public修饰
        Field name = smallPineappleClass.getField("name");
        System.out.println(name);

        System.out.println("-------");
        // getDeclaredFields() 获取类中所有变量，但无法获取继承下来的变量
        Field[] declaredFields = smallPineappleClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }
        // getDeclaredField()  根据变量名获取类的一个变量，但无法获取继承下来的变量
        Field name1 = smallPineappleClass.getDeclaredField("age");
        System.out.println(name1);
    }

    // 获取类中的方法
    public static void test04() throws ClassNotFoundException, NoSuchMethodException {
        System.out.println("methods[]");
        Class<?> aClass = Class.forName("reflect.SmallPineapple");
        // getMethods() 获取类中所有被public修饰的方法
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(">" + declaredMethod);
        }
    }

    // 通过反射调用方法
    public static void test05() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<SmallPineapple> clazz = SmallPineapple.class;
        Constructor<SmallPineapple> constructor = clazz.getConstructor(String.class, int.class);
        constructor.setAccessible(true);
        SmallPineapple person = constructor.newInstance("wangjun", 29);
        Method test01 = clazz.getMethod("getInfo");
        if (test01 != null) {
            Object invoke = test01.invoke(person, null);
            System.out.println(invoke);
        }
    }
}
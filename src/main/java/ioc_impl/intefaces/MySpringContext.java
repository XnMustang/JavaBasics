package ioc_impl.intefaces;

import java.util.HashMap;
import java.util.Map;

/**
 * Spring上下文
 */
public interface MySpringContext {

    /** IOC容器 */
    Map<String, Object> IOC = new HashMap<String, Object>();

    //存储上下文的属性
    Map<Object,Object> ATTRS=new HashMap<Object, Object>();

    /**
     * 根据名称获取bean实例
     *
     * @param   beanName bean名称
     * @return  bean实例
     */
    Object getBean(String beanName);

    /**
     * 根据字节码文件获取bean实例
     *
     * @param   cls  beanClass
     * @return  bean实例
     */
    Object getBean(Class cls) throws Exception;

    /**
     * 获取上下文的属性
     *
     * @param object 键
     * @return Object 值
     */
    Object getAttr(Object object);

    /**
     * 设置上下文属性
     *
     * @param key 键
     * @param value 值
     */
    void setAttrs(Object key, Object value);

    /**
     * 销毁所有实例
     */
    void dispose();

    /**
     * 设置bean
     *
     * @param beanName bean名称
     * @param obj   bean实例
     */
    void setBean(String beanName, Object obj);

    /**
     * 设置bean
     *
     * @param clz bean类实例
     * @param obj
     */
    void setBean(Class clz, Object obj);

    /**
     * 打印IOC容器概览
     */
    void overview();

}

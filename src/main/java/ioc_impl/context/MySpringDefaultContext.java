package ioc_impl.context;

import ioc_impl.annotation.MyComponent;
import ioc_impl.annotation.MyScope;
import ioc_impl.constant.ScopeType;
import ioc_impl.intefaces.MySpringContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 默认上下文
 */
public class MySpringDefaultContext implements MySpringContext {

    @Override
    public Object getBean(String beanName) {
        Object bean = IOC.get(beanName);
        if (bean != null) {
            return bean;
        }
        return null;
    }

    @Override
    public Object getBean(Class cls) throws Exception {
        MyComponent component = (MyComponent) cls.getAnnotation(MyComponent.class);
        MyScope scope = (MyScope) cls.getAnnotation(MyScope.class);
        String scopeType = ScopeType.SINGLETON;
        if ((component != null && component.equals(ScopeType.PROTOTYPE)) || (scope != null && scope.equals(ScopeType.PROTOTYPE))) {
            scopeType = ScopeType.PROTOTYPE;
        }
        // 单例
        if (scopeType.equals(ScopeType.SINGLETON)) {
            String name = getName(cls);
            // 获取bean实例
            Object object = getBean(name);
            if(cls.isAnnotation()){
                throw new Exception("类型：注解"+cls.getName());
            }
            //找同类型的且优先同类型
            List<Object> instances = new ArrayList<>();
            for(Map.Entry<String, Object> entry : IOC.entrySet()){
                if(cls.isInstance(entry.getValue())){
                    instances.add(entry.getValue());
                }
            }
            // TODO 尚不完善
        }
        return null;
    }

    @Override
    public Object getAttr(Object object) {
        return ATTRS.get(object);
    }

    @Override
    public void setAttrs(Object key, Object value) {
        ATTRS.put(key, value);
    }

    @Override
    public void dispose() {
        IOC.clear();
        ATTRS.clear();
    }

    @Override
    public void setBean(String beanName, Object obj) {
        IOC.put(beanName, obj);
        Class<?> clz = obj.getClass();
    }

    @Override
    public void setBean(Class clz, Object obj) {
        String name = getName(clz);
        IOC.put(name, obj);

    }

    public String getName(Class cls) {
        return cls.getName();
    }

    @Override
    public void overview() {
        System.out.println("\n\n");
        System.out.println("-----------------IOC容器概览---------------------");
        System.out.println("IOC容器中共有" + IOC.size() + "个bean");
        for(String name:IOC.keySet()){
            System.out.println(name);
        }
        System.out.println("-------------------------------------------------");
        System.out.println("\n\n");
    }
}

package cool.leeson.library.util;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class CheckObjectIsNullUtils {
    /**
     * 判断对象是否为空，且对象的所有属性都为空 ps: boolean类型会有默认值false 判断结果不会为null 会影响判断结果 序列化的默认值也会影响判断结果
     *
     * @param object class
     * @return false true
     */
    @SuppressWarnings("rawtypes")
    public static boolean objCheckIsNull(Object object) {
        // 得到类对象
        Class clazz = object.getClass();
        // 得到所有属性
        Field[] fields = clazz.getDeclaredFields();
        // 定义返回结果，默认为false
        boolean flag = false;
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldValue = null;
            try {
                // 得到属性值
                fieldValue = field.get(object);
                // 得到属性类型
                Type fieldType = field.getGenericType();
                // 得到属性名
                String fieldName = field.getName();

            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
            // 只要有一个属性值为null 就返回false 表示对象为null
            if (null == fieldValue || "" == fieldValue) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public static boolean objCheckAllIsNull(Object object) {
        // 得到类对象
        Class clazz = object.getClass();
        // 得到所有属性
        Field[] fields = clazz.getDeclaredFields();
        // 定义返回结果，默认为false
        boolean flag = true;
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldValue = null;
            try {
                // 得到属性值
                fieldValue = field.get(object);
                // 得到属性类型
                Type fieldType = field.getGenericType();
                // 得到属性名
                String fieldName = field.getName();

            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
            // 只要有一个属性值为null 就返回false 表示对象为null
            if (null != fieldValue && "" != fieldValue) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}

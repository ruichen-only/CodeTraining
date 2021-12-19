package reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectDemo {
    public Object getObject(String name) {
        Object obj = null;
        try {
            Class classType = Class.forName(name);
            obj = classType.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static Object copy(Object obj) throws Exception {
        Class<?> classType = obj.getClass();
        Object objCopy = classType.getConstructor(new Class[]{})
                .newInstance(new Object[]{});
//        Object objCopyTemp = classType.newInstance();
        Field[] fields = classType.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String fieldName = field.getName();
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getMethodName = "get" + firstLetter + fieldName.substring(1);
            String setMethodName = "set" + firstLetter + fieldName.substring(1);
            Method getMethod = classType.getMethod(getMethodName, new Class[]{});
            Method setMethod = classType.getMethod(setMethodName, new Class[]{});
            Object value = getMethod.invoke(obj, new Object[]{});
            setMethod.invoke(objCopy, new Object[]{ value });
        }
        return objCopy;
    }

    public static void main(String[] args) throws Exception {
        Student stu = new Student("crr", 24);
        stu.setId(new Long(1));
        Student copyStu = (Student) copy(stu);

    }

}

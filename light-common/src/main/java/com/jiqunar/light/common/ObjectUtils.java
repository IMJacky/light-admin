package com.jiqunar.light.common;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 对象帮助类
 *
 * @author jieguang.wang
 * @date 2020/9/7 17:30
 */
public class ObjectUtils {
    /**
     * 对象比较器
     *
     * @param oldBean
     * @param newBean
     * @param <T>
     * @return
     */
    public <T> String compareObject(Object oldBean, Object newBean) {
        String str = "";
        try {
            T pojo1 = null;
            if (oldBean != null) {
                pojo1 = (T) oldBean;
            }
            T pojo2 = (T) newBean;
            Class clazz = pojo2.getClass();
            Field[] fields = pojo2.getClass().getDeclaredFields();
            for (Field field : fields) {
                ApiModelProperty fieldName = field.getAnnotation(ApiModelProperty.class);
                if (fieldName == null) {
                    continue;
                }
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method getMethod = pd.getReadMethod();
                Object o1 = null;
                if (pojo1 != null) {
                    o1 = getMethod.invoke(pojo1);
                }
                Object o2 = getMethod.invoke(pojo2);
                if (o2 == null) {
                    continue;
                }

                String objVal1 = "";
                if (o1 != null) {
                    if (o1 instanceof LocalDateTime) {
                        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        objVal1 = dtf2.format((LocalDateTime) o1);
                    }
                    if (o1 instanceof BigDecimal) {
                        BigDecimal decimal = new BigDecimal(o1.toString());
                        BigDecimal setScale = decimal.setScale(8, BigDecimal.ROUND_HALF_DOWN);
                        objVal1 = setScale.toString();
                    } else {
                        objVal1 = o1.toString();
                    }
                }
                String objVal2 = "";
                if (o2 != null) {
                    if (o2 instanceof LocalDateTime) {
                        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        objVal2 = dtf2.format((LocalDateTime) o2);
                    }
                    if (o2 instanceof BigDecimal) {
                        BigDecimal decimal = new BigDecimal(o2.toString());
                        BigDecimal setScale = decimal.setScale(8, BigDecimal.ROUND_HALF_DOWN);
                        objVal2 = setScale.toString();
                    } else {
                        objVal2 = o2.toString();
                    }
                }
                if (objVal1.equals(objVal2)) {
                    continue;
                }
                str += String.format("\r\n将%s，由“%s”修改为“%s”", StringUtils.isBlank(fieldName.value()) ? field.getName() : fieldName.value(), objVal1, objVal2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 获取对象数据
     *
     * @param object
     * @param <T>
     * @return
     */
    public static <T> String getObject(T object) {
        String str = "";
        try {
            Class clazz = object.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                ApiModelProperty fieldName = field.getAnnotation(ApiModelProperty.class);
                if (fieldName == null) {
                    continue;
                }
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method getMethod = pd.getReadMethod();
                Object o2 = getMethod.invoke(object);
                if (o2 == null) {
                    continue;
                }
                String objVal2 = "";
                if (o2 != null) {
                    if (o2 instanceof LocalDateTime) {
                        objVal2 = DateUtils.getDate((LocalDateTime) o2);
                    }
                    if (o2 instanceof BigDecimal) {
                        BigDecimal decimal = new BigDecimal(o2.toString());
                        BigDecimal setScale = decimal.setScale(8, BigDecimal.ROUND_HALF_DOWN);
                        objVal2 = setScale.toString();
                    } else {
                        objVal2 = o2.toString();
                    }
                }
                str += String.format("\r\n%s：%s", StringUtils.isBlank(fieldName.value()) ? field.getName() : fieldName.value(), objVal2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}

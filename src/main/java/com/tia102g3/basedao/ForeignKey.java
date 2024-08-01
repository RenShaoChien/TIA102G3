package com.tia102g3.basedao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName： ForeignKey
 * package：com.tia102g3.basedao
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/8/1 上午4:26
 * @Version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ForeignKey {
    Class<?> targetEntity();
    String keyField() default "id"; // 默認主键字段為 "id"
}

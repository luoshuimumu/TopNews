package com.example.luoshuimumu.TopNews.widget.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by luoshuimumu on 2018/2/9.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TestAnnotation {
    String strValue() default "lsmm";

    String[] strArrValue() default {"lsmm", "zfsn"};
}

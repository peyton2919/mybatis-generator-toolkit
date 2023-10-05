package cn.peyton.children.mgts.mybatis.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <h3></h3>
 * <pre>
 * @author <a href="http://www.peyton.cn">peyton</a>
 * @mail <a href="mailto:fz2919@tom.com">fz2919@tom.com</a>
 * @date 2018/8/11 8:54
 * @version 1.0.0
 * </pre>
 */
@Target({TYPE, FIELD})
@Retention(RUNTIME)
@Documented
public @interface Ref {
}

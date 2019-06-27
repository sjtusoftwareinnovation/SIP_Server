package cn.edu.sjtu.sip_server.interceptor;

        import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Access {
    String[] value() default {};

    String[] authorities() default {};

    int[] roles() default {};

}

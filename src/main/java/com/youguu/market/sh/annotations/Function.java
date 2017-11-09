package com.youguu.market.sh.annotations;

import java.lang.annotation.*;

@Target(value={ElementType.TYPE})
@Retention(value= RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Function {
    String value();
}

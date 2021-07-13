package kr.xcc.xccbot.bot.setting.annotation;

import java.lang.annotation.*;


/**
 * <h1> 리스너를 추가해주기 위한 어노테이션 입니다. </h1>
 * @author Lee Sang Min
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
public @interface Listener{

}

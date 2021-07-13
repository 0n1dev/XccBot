package kr.xcc.xccbot.bot.setting.annotation;

import java.lang.annotation.*;

/**
 * <h1> 봇이 확장 (생성) 될 경우 구분하기 위한 어노테이션 입니다. </h1>
 * @author Lee Sang Min
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
public @interface Bot {

    String botName();


}

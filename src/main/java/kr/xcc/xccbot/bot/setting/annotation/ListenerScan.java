package kr.xcc.xccbot.bot.setting.annotation;

import net.dv8tion.jda.api.JDABuilder;
import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

@Component
public class ListenerScan {

    Map<String, Object> objTable = new Hashtable<>();

    Reflections reflector = new Reflections("kr/xcc/xccbot/bot/setting/Listener", new TypeAnnotationsScanner());

    Set<Class<?>> listenerList = reflector.getTypesAnnotatedWith(Listener.class, true);

    String listenerPath;

    ListenerScan() {
    }

    /**
     * <h1> @Listener 이 붙은 클래스들을 JDABUilder 의 이벤트 리스너로 추가하는 메소드 입니다.</h1>
     *
     * @author Lee Sang Min
     * @throws Exception
     */
    public void setAddeventListener(JDABuilder jdaBuilder) throws Exception {
        Class<?> temp;
        Constructor listenerClass;
        String key = null;
        for (Class<?> listener :listenerList) {
            Constructor a = listener.getConstructor();
            jdaBuilder.addEventListeners(a.newInstance());
        }
    }


}

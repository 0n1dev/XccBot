package kr.xcc.xccbot.bot.setting;

import kr.xcc.xccbot.bot.setting.annotation.ListenerScan;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class XccBotDetailCustom {

    private BotConfig botConfig;
    private ListenerScan listenerScan;

    XccBotDetailCustom(BotConfig botConfig, ListenerScan listenerScan) {
        this.botConfig = botConfig;
        this.listenerScan = listenerScan;
    }

    @PostConstruct
    public void init() throws Exception {
        JDABuilder jdaBuilder = botConfig.defaultBuilder();
        listenerScan.setAddeventListener(jdaBuilder);
        botConfig.endBuilder();
    }


}
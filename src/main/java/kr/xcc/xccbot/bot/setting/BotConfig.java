package kr.xcc.xccbot.bot.setting;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lee Sang Min
 */
public interface BotConfig {

    void endBuilder();

    JDABuilder defaultBuilder();




}

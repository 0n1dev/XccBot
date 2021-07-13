package kr.xcc.xccbot.bot.setting;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.springframework.context.annotation.Configuration;

import javax.security.auth.login.LoginException;

@Configuration
@Slf4j
public class XccBotConfig implements BotConfig {

    private JDABuilder builder;

    private JDA jda;

    @Override
    public JDABuilder defaultBuilder() {
        this.builder = JDABuilder.createDefault("ODYyMDExNDg1MDIxNDA1MTg2.YOSIwQ.jwqYrqKNiIcZVrBbB7Qakc0mfSM")
        .setActivity(Activity.watching("XccBot vBeta"))
        .enableIntents(GatewayIntent.GUILD_MEMBERS)
        .setMemberCachePolicy(MemberCachePolicy.ALL)
        .setChunkingFilter(ChunkingFilter.NONE)
        .setLargeThreshold(250);
        return this.builder;
    }

    @Override
    public void endBuilder() {
        try {
            log.error("success XccBot Build!");
            this.jda = builder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}

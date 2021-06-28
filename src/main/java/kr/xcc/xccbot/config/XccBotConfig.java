package kr.xcc.xccbot.config;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

@Configuration
public class XccBotConfig {

	@Bean
	JDA jda(@Value("${jda.token}") final String jdaToken) {
		final JDABuilder builder = JDABuilder.createDefault(jdaToken);

		builder.setActivity(Activity.watching("XccBot vBeta"));
		builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
		builder.setMemberCachePolicy(MemberCachePolicy.ALL);
		builder.setChunkingFilter(ChunkingFilter.NONE);
		builder.setLargeThreshold(250);

		try {
			final JDA jda = builder.build();
			jda.awaitReady();
			return jda;
		} catch (final LoginException e) {
			e.printStackTrace();
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
}

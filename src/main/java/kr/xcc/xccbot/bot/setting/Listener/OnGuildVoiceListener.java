package kr.xcc.xccbot.bot.setting.Listener;

import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.GuildUnavailableEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class OnGuildVoiceListener extends ListenerAdapter {
	// voice
	@Override
	public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {
		TextChannel landingChannel = event.getGuild().getTextChannelById("860469507034447913"); // �Ϲ� �ؽ�Ʈ ä��
		User user = event.getMember().getUser();

		landingChannel.sendMessage(user.getAsMention() + "onGuildVoiceJoin.").queueAfter(2, TimeUnit.SECONDS);
		System.out.println("detected");
	}

	@Override
	public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {
		TextChannel landingChannel = event.getGuild().getTextChannelById("860469507034447913");
		User user = event.getMember().getUser();

		landingChannel.sendMessage(user.getAsMention() + "onGuildVoiceLeave.").queueAfter(2, TimeUnit.SECONDS);
		System.out.println("detected");
	}

	//event when Guild Unavailable
	@Override
	public void onGuildUnavailable(GuildUnavailableEvent event) {
		System.out.println("onGuildUnavailable.");
	}
}

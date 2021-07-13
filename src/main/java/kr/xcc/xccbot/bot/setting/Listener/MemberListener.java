package kr.xcc.xccbot.bot.setting.Listener;

import java.util.concurrent.TimeUnit;

import kr.xcc.xccbot.bot.setting.annotation.Listener;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateNicknameEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Listener
public class MemberListener extends ListenerAdapter {
	// member
	@Override
	public void onGuildMemberJoin(GuildMemberJoinEvent event) {
		Guild guild = event.getGuild();
		TextChannel landingChannel = guild.getDefaultChannel();
		System.out.println(landingChannel);
		if (landingChannel != null) {
			landingChannel.sendMessage(event.getUser().getAsMention() + "onGuildMemberJoin")
				.queueAfter(4, TimeUnit.SECONDS);
		}
	}

	@Override
	public void onGuildMemberRemove(GuildMemberRemoveEvent event) {
		Guild guild = event.getGuild();
		User user = event.getMember().getUser();
		TextChannel landingChannel = guild.getDefaultChannel();
		landingChannel.sendMessage(user.getAsMention() + "onGuildMemberRemove.")
			.queueAfter(4, TimeUnit.SECONDS);
	}

	@Override
	public void onGuildMemberUpdateNickname(GuildMemberUpdateNicknameEvent event) {
		Guild guild = event.getGuild();
		TextChannel landingChannel = guild.getDefaultChannel();
		if (landingChannel != null) {
			landingChannel.sendMessage(event.getOldNickname() + "to" + event.getNewNickname())
				.queueAfter(4, TimeUnit.SECONDS);
		}
		System.out.println("onGuildMemberUpdateNickname");
	}

	@Override
	public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event) {
		TextChannel landingChannel = event.getGuild().getTextChannelById("860469507034447913");
		landingChannel.sendMessage(event.getMember().getAsMention() + " onGuildMemberRoleAdd: "
			+ event.getGuild().getRolesByName("Mr.Pizza", true)).queueAfter(1, TimeUnit.SECONDS);
	}

	@Override
	public void onGuildMemberRoleRemove(GuildMemberRoleRemoveEvent event) {
		TextChannel landingChannel = event.getGuild().getTextChannelById("860469507034447913");
		landingChannel.sendMessage(event.getMember().getAsMention() + "onGuildMemberRoleRemove: "
			+ event.getGuild().getRolesByName("Mr.Pizza", true)).queueAfter(1, TimeUnit.SECONDS);
	}

}

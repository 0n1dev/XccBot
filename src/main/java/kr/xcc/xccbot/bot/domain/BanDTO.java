package kr.xcc.xccbot.bot.domain;

import java.time.OffsetDateTime;

import lombok.Builder;
import lombok.Getter;
import net.dv8tion.jda.api.entities.Member;

@Getter
public class BanDTO {

	private OffsetDateTime timeCreated;
	private String owner;
	private String reason;
	private String guild;
	private String ban;
	private String unban;

	@Builder
	public BanDTO(OffsetDateTime timeCreated, String owner, String member, String reason, String guild, String ban,
			String unban) {
		this.timeCreated = timeCreated;
		this.owner = owner;
		this.reason = reason;
		this.guild = guild;
		this.ban = ban;
		this.unban = unban;
	}

}
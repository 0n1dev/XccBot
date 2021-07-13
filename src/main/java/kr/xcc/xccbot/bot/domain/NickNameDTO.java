package kr.xcc.xccbot.bot.domain;

import java.time.OffsetDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
public class NickNameDTO {

	private OffsetDateTime timeCreated;
	private String owner;
	private String member;
	private String guild;
	private String oldNickName;
	private String newNickName;

	@Builder
	public NickNameDTO(OffsetDateTime timeCreated, String owner, String member, String guild, String oldNickName,
			String newNickName) {
		this.timeCreated = timeCreated;
		this.owner = owner;
		this.member = member;
		this.guild = guild;
		this.oldNickName = oldNickName;
		this.newNickName = newNickName;
	}
}
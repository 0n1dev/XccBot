package kr.xcc.xccbot.bot.domain;

import java.time.OffsetDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RoleDTO {

	/**
	 * 해당 메세지 생성 시간입니다.
	 */
	private OffsetDateTime timeCreated;
	/**
	 * 해당 메세지 생성 시간입니다.
	 */
	private String owner;
	/**
	 * 해당 메세지 생성 시간입니다.
	 */
	private String member;
	/**
	 * 해당 메세지 생성 시간입니다.
	 */
	private String guild;
	/**
	 * 해당 메세지 생성 시간입니다.
	 */
	private String RoleName;

	@Builder
	public RoleDTO(OffsetDateTime timeCreated, String owner, String member, String guild, String roleName) {
		this.timeCreated = timeCreated;
		this.owner = owner;
		this.member = member;
		this.guild = guild;
		this.RoleName = roleName;
	}

}
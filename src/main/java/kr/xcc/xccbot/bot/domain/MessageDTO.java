package kr.xcc.xccbot.bot.domain;

import java.time.OffsetDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import net.dv8tion.jda.api.entities.Emote;


/**
 * 메세지 이벤트 전송을 위한 클래스입니다.
 * @author Lee Sang Min
 */
@Getter
@ToString
public class MessageDTO {

	/**
	 * 해당 메세지 생성 시간입니다.
	 */
	private OffsetDateTime timeCreated;
	/**
	 * 해당 메세지를 보낸 사람의 이름입니다.
	 */
	private String ownerName;
	/**
	 * 해당 서버(길드) 의 이름입니다.
	 */
	private String guildName;
	/**
	 * 해당 채널의 이름입니다.
	 */
	private String textChannerName;
	/**
	 * 해당 메세지의 URL(경로) 입니다.
	 */
	private String messageUrl;


	@Builder public MessageDTO(
			OffsetDateTime timeCreated, String ownerName, String memberName, String guildName, String textChannerName
	,String messageUrl, List<Emote> emoteList) {

		this.timeCreated = timeCreated;
		this.ownerName = ownerName;
		this.guildName = guildName;
		this.textChannerName = textChannerName;
		this.messageUrl = messageUrl;
	}

}
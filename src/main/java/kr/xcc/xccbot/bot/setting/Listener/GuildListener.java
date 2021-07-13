package kr.xcc.xccbot.bot.setting.Listener;

import kr.xcc.xccbot.bot.domain.MessageDTO;
import kr.xcc.xccbot.bot.setting.annotation.Listener;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.guild.GuildBanEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.guild.GuildUnbanEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageEmbedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageUpdateEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveAllEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEmoteEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <h1> 길드 이벤트를 처리하기 위한 리스너입니다. </h1>
 * @author Lee Sang Min
 */
@Component
@Listener
public class GuildListener extends ListenerAdapter {

    /***
     * <h1> 길드(서버) 에서 텍스트를 입력할 시 트리거 되는 이벤트 입니다.</h1>
     * @see kr.xcc.xccbot.bot.domain.MessageDTO
     * @see kr.xcc.xccbot.bot.domain.UserInfoDTO
     * @author Lee Sang Min
     * @param event
     * @return kr.xcc.xccbot.bot.domain.MessageDTO, kr.xcc.xccbot.bot.domain.UserInfoDTO
     */
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        Message message = event.getMessage();
        MessageDTO messageDTO = MessageDTO.builder()
                .timeCreated(message.getTimeCreated())
                .ownerName(message.getAuthor().getName())
                .guildName(message.getGuild().getName())
                .textChannerName(message.getTextChannel().getName())
                .messageUrl(message.getJumpUrl())
                .build();
        System.out.println(messageDTO.toString());
    }

    @Override
    public void onGuildMessageEmbed(@NotNull GuildMessageEmbedEvent event) {
        System.out.println(event.getGuild());
        System.out.println("onGuildMessageEmbed");
        super.onGuildMessageEmbed(event);
    }

    /**
     * <h1> 차단하기 눌렀을 경우에 트리거 되는 이벤트 입니다. </h1>
     * @author Lee Sang Min
     * @param event
     */
    @Override
    public void onGuildBan(@NotNull GuildBanEvent event) {
        User user = event.getUser();
        Guild guild = event.getGuild();
        List<Member> memberList = guild.getMembers();
        System.out.println(event.getGuild());
        System.out.println("onGuildBan");
        super.onGuildBan(event);
    }

    /**
     * <h1> 차단하기를 해제한 경우에 트리거 되는 이벤트 입니다. </h1>
     * @author Lee Sang Min
     * @param event
     */
    @Override
    public void onGuildUnban(@NotNull GuildUnbanEvent event) {
        System.out.println(event.getGuild());
        System.out.println("onGuildUnban");
        super.onGuildUnban(event);
    }

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        System.out.println(event.getGuild());
        System.out.println("onGuildJoin");
        super.onGuildJoin(event);
    }

    @Override
    public void onGuildLeave(@NotNull GuildLeaveEvent event) {
        System.out.println(event.getGuild());
        System.out.println("onGuildLeave");
        super.onGuildLeave(event);
    }

    /***
     * <h1> 서버에 전송 된 텍스트를 수정할 경우 일어나는 이벤트 입니다.</h1>
     * 전송된 텍스트 -> 마우스 호버 -> 연필모양 (수정) -> 수정할 경우 발생하는 이벤트
     * @author Lee Sang Min
     * @param event
     */
    @Override
    public void onGuildMessageUpdate(@NotNull GuildMessageUpdateEvent event) {
        Guild guild = event.getGuild();
        System.out.println(event.getGuild());
        System.out.println("onGuildMessageUpdate");
        super.onGuildMessageUpdate(event);
    }

    /***
     * <h1> 길드(서버) 에서 입력된 텍스트를 삭제할 시 트리거 되는 이벤트 입니다.</h1>
     * 전송된 텍스트 -> 마우스 호버 -> 기타 -> 메시지 삭제하기
     * @author Lee Sang Min
     * @param event
     */
    @Override
    public void onGuildMessageDelete(@NotNull GuildMessageDeleteEvent event) {
        System.out.println(event.getGuild());
        System.out.println("onGuildMessageDelete");
        super.onGuildMessageDelete(event);
    }

    @Override
    public void onGuildMessageReactionRemoveAll(@NotNull GuildMessageReactionRemoveAllEvent event) {
        System.out.println(event.getGuild());
        System.out.println("onGuildMessageReactionRemoveAll");
        super.onGuildMessageReactionRemoveAll(event);
    }


    @Override
    public void onGuildMessageReactionRemoveEmote(@NotNull GuildMessageReactionRemoveEmoteEvent event) {
        System.out.println(event.getGuild());
        System.out.println("onGuildMessageReactionRemoveEmote");
        super.onGuildMessageReactionRemoveEmote(event);
    }

    /**
     * <h1> 서버에 전송된 메세지의 반응을 삭제하는 경우 트리거 됩니다.</h1>
     * 전송된 텍스트 -> 추가된 반응을 클릭 -> 삭제되는 경우 -> 해당 이벤트로 전송
     * @author Lee Sang Min
     * @param event
     */
    @Override
    public void onGuildMessageReactionRemove(@NotNull GuildMessageReactionRemoveEvent event) {
        System.out.println(event.getGuild());
        System.out.println("onGuildMessageReactionRemove");
        super.onGuildMessageReactionRemove(event);
    }


    /**
     * <h1> 서버에 전송된 메세지의 반응을 추가하는 경우 트리거 됩니다.</h1>
     * 전송된 텍스트 -> 마우스 호버 -> 반응 추가하기 -> 전송
     * @author Lee Sang Min
     * @param event
     */
    @Override
    public void onGuildMessageReactionAdd(@NotNull GuildMessageReactionAddEvent event) {
        System.out.println(event.getGuild());
        System.out.println("onGuildMessageReactionAdd");
        super.onGuildMessageReactionAdd(event);
    }
}

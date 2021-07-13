package kr.xcc.xccbot.bot.domain;

import lombok.Builder;

public class UserInfoDTO {

    private String memberList;


    @Builder
    UserInfoDTO(String memberList) {
        this.memberList = memberList;
    }

}

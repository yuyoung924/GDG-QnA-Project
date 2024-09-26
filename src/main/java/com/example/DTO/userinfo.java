package com.example.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder

public class userinfo {

    private Long id;

    private String email;

    private  String username;

}

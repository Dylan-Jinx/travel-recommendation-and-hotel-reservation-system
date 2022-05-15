package se.xmut.trahrs.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LoginDto implements Serializable {

    private String userName;
    private String userPwd;
}

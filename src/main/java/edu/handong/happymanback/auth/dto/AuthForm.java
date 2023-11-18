package edu.handong.happymanback.auth.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthForm {
    private String uniqueId;
    private String password;
}

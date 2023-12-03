package edu.handong.happymanback.auth.controller;
import edu.handong.happymanback.auth.dto.AuthDto;
import edu.handong.happymanback.auth.dto.AuthForm;
import edu.handong.happymanback.auth.jwt.JwtTokenUtil;
import edu.handong.happymanback.auth.service.AuthService;
import edu.handong.happymanback.user.domain.User;
import edu.handong.happymanback.user.dto.UserForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/happyman/auth")
public class AuthController {
    @Value("${custom.jwt.secret}")
    private String secretKey;
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/join")
    public ResponseEntity<Map<String, String>> joinUser(@RequestBody UserForm form) {
        String joinId = authService.join(form);
        return ResponseEntity.created(
                URI.create("/api/happyman/user/" + joinId)
        ).body(Map.of("id", joinId));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthDto> login(@RequestBody AuthForm form) {

        User user = authService.login(form);

        // 로그인 아이디나 비밀번호가 틀린 경우 global error return
        if(user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthDto(null,"Login ID or password is incorrect."));
        }


        // 로그인 성공 => Jwt Token 발급
        long expireTimeMs = 1000 * 60 * 60;     // Token 유효 시간 = 60분
        String jwtToken = JwtTokenUtil.createToken(user.getUniqueId(), secretKey, expireTimeMs);

        return ResponseEntity.ok().body(new AuthDto(jwtToken,null));
    }

    @GetMapping("/me")
    public ResponseEntity<User> getMe(@RequestHeader("Authorization") String bearerToken) {
        String jwtToken = bearerToken.split(" ")[1];
        String uniqueId = JwtTokenUtil.getUniqueId(jwtToken, secretKey);
        User user = authService.getLoginUserByUniqueId(uniqueId);
        return ResponseEntity.ok(user);
    }
}

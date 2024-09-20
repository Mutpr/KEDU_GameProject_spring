package com.kedu.game.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean

    // cors 설정이랑 똑같음 인터셉터 쓰지말란 이야기 아님!!
    //어차피 시큐리티 쓰는 목적이 보안설정 하라는거-> 스프링 시큐리티, corsConfig 사이 차이 보안설정만 바뀐거
    //csrf: 일반 사용자인척 공격이 들어오는것
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.cors(cors->cors.configurationSource(request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedOrigins(Arrays.asList("http://192.168.0.100:3000"));
            config.setAllowedOrigins(Arrays.asList("http://192.168.1.238:3000"));
            config.setAllowedHeaders(Arrays.asList("*"));
            config.setAllowedMethods(Arrays.asList("*"));
            return config;
        })).csrf(csrf-> csrf.disable())
                .formLogin(form->form.disable())//기본 설정 로그인 페이지 꺼줌
                .httpBasic(basic->basic.disable())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/socket/chat").permitAll() // WebSocket 경로에 대한 접근 제한
                        .requestMatchers(HttpMethod.POST, "/user/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/user/register").permitAll()

                        .requestMatchers(HttpMethod.GET,"/friend").permitAll()
                        .requestMatchers(HttpMethod.POST,"/friend").permitAll()
                        .requestMatchers(HttpMethod.GET,"/friend/{seq}").permitAll()
                        .requestMatchers(HttpMethod.GET,"/friend/userSearch").permitAll()
                        .requestMatchers(HttpMethod.POST,"/friend/addFriend").permitAll()
                        .requestMatchers(HttpMethod.GET,"/friend/findRequest").permitAll()
                        .requestMatchers(HttpMethod.GET,"/friend/findReceivedRequest").permitAll()
                        .requestMatchers(HttpMethod.POST,"/friend/requestAgree").permitAll()
                        .requestMatchers(HttpMethod.POST,"/friend/requestDisagree").permitAll()
                        .requestMatchers(HttpMethod.POST,"/friend/delete/{deletedUserSeq}/{parsedSeq}").permitAll()
                        .anyRequest().authenticated()
                );

        //formLogin, httpBasic: 우리가 restful api 사용하기때문에 꺼놓고 csrf: csrf 공격 방지 기능 보안 설정으로 실습중에는 꺼둘것!!
        ;//csrf 보안설정 꺼둠(csrf 설정하는 순간 난이도 상승함
        return http.build();
    }
}

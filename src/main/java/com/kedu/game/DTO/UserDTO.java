package com.kedu.game.DTO;

import com.kedu.game.controller.UserController;

public class UserDTO {

    private int user_seq;
    private String user_id;
    private String user_password;
    private String user_name;
    private String user_tag_id;



    private int friend_list_seq;

    public UserDTO(){
        super();
    }
    //회원 가입용 생성자
    public UserDTO(String user_id, String user_password, String user_name, String user_tag_id){
        this.user_id = user_id;
        this.user_password = user_password;
        this.user_name = user_name;
        this.user_tag_id = user_tag_id;
    }
//로그인용 생성자
    public UserDTO(String user_id, String user_password){
        this.user_id= user_id;
        this.user_password = user_password;
    }

    //친구 검색 용 생성자
    public UserDTO(int user_seq, String user_tag_id,String user_name){
        this.user_seq = user_seq;
        this.user_name=user_name;
        this.user_tag_id = user_tag_id;
    }

    public int getUser_seq() {
        return user_seq;
    }

    public void setUser_seq(int user_seq) {
        this.user_seq = user_seq;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_tag_id(){
        return user_tag_id;
    }

    public void setUser_tag_id(String user_tag_id){
        this.user_tag_id = user_tag_id;
    }

    public int getFriend_list_seq() {
        return friend_list_seq;
    }

    public void setFriend_list_seq(int friend_list_seq) {
        this.friend_list_seq = friend_list_seq;
    }
}

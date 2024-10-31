package com.kedu.game.DTO;

import java.sql.Timestamp;

public class ChatRoomDTO {

    public ChatRoomDTO(){
        super();
    }
    //채팅방 생성용 생성자
    public ChatRoomDTO(String chatroom_id, String chatroom_title, String chatroom_user_list, String chatroom_desc){
        this.chatroom_id = chatroom_id;
        this.chatroom_title = chatroom_title;
        this.chatroom_user_list = chatroom_user_list;
        this.chatroom_desc = chatroom_desc;
    }

    public ChatRoomDTO(String chatroom_title, String chatroom_desc){
        this.chatroom_title = chatroom_title;
        this.chatroom_desc = chatroom_desc;
    }
    //유저 초대용 생성자
    public ChatRoomDTO(String chatroom_user_list){
        this.chatroom_user_list = chatroom_user_list;
    }
    private int chatroom_seq;
    private String chatroom_id;
    private String chatroom_title;
    private String chatroom_user_list;
    private Timestamp chatroom_reg_date;
    private String chatroom_desc;

    public String getChatroom_desc() {
        return chatroom_desc;
    }

    public void setChatroom_desc(String chatroom_desc) {
        this.chatroom_desc = chatroom_desc;
    }

    public int getChatroom_seq() {
        return chatroom_seq;
    }

    public void setChatroom_seq(int chatroom_seq) {
        this.chatroom_seq = chatroom_seq;
    }

    public String getChatroom_id() {
        return chatroom_id;
    }

    public void setChatroom_id(String chatroom_id) {
        this.chatroom_id = chatroom_id;
    }

    public String getChatroom_title() {
        return chatroom_title;
    }

    public void setChatroom_title(String chatroom_title) {
        this.chatroom_title = chatroom_title;
    }

    public String getChatroom_user_list() {
        return chatroom_user_list;
    }

    public void setChatroom_user_list(String chatroom_user_list) {
        this.chatroom_user_list = chatroom_user_list;
    }

    public Timestamp getChatroom_reg_date() {
        return chatroom_reg_date;
    }

    public void setChatroom_reg_date(Timestamp chatroom_reg_date) {
        this.chatroom_reg_date = chatroom_reg_date;
    }

}

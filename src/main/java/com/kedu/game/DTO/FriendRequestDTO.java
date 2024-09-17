package com.kedu.game.DTO;

public class FriendRequestDTO {

    public FriendRequestDTO(){
        super();
    }

    //친추용 생성자
    public FriendRequestDTO(int friend_request_owner_seq, int friend_request_sender_seq){
        this.friend_request_owner_seq = friend_request_owner_seq;
        this.friend_request_sender_seq=friend_request_sender_seq;
    }
    private int friend_request_seq;
    private int friend_request_owner_seq;
    private int friend_request_sender_seq;
    private String friend_request_status;

    public int getFriend_request_seq() {
        return friend_request_seq;
    }

    public void setFriend_request_seq(int friend_request_seq) {
        this.friend_request_seq = friend_request_seq;
    }

    public int getFriend_request_owner_seq() {
        return friend_request_owner_seq;
    }

    public void setFriend_request_owner_seq(int friend_request_owner_seq) {
        this.friend_request_owner_seq = friend_request_owner_seq;
    }

    public int getFriend_request_sender_seq() {
        return friend_request_sender_seq;
    }

    public void setFriend_request_sender_seq(int friend_request_sender_seq) {
        this.friend_request_sender_seq = friend_request_sender_seq;
    }

    public String getFriend_request_status() {
        return friend_request_status;
    }

    public void setFriend_request_status(String friend_request_status) {
        this.friend_request_status = friend_request_status;
    }
}

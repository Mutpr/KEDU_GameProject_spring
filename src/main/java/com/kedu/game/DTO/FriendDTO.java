package com.kedu.game.DTO;

public class FriendDTO {
    private int friend_seq;
    private int friend_owner_seq;
    private String friend_list;

    public FriendDTO(){
        super();
    }

    public FriendDTO(int friend_seq, int friend_owner_seq, String friend_list){
        this.friend_seq = friend_seq;
        this.friend_owner_seq = friend_owner_seq;
        this.friend_list = friend_list;
    }


    //friend_seq는 자동으로 추가되고 friend_list는 처음에 []만 삽입
    public FriendDTO(int friend_owner_seq){
        this.friend_owner_seq = friend_owner_seq;
    }

    public int getFriend_seq() {
        return friend_seq;
    }

    public void setFriend_seq(int friend_seq) {
        this.friend_seq = friend_seq;
    }

    public int getFriend_owner_seq() {
        return friend_owner_seq;
    }

    public void setFriend_owner_seq(int friend_owner_seq) {
        this.friend_owner_seq = friend_owner_seq;
    }

    public String getFriend_list() {
        return friend_list;
    }

    public void setFriend_list(String friend_list) {
        this.friend_list = friend_list;
    }


}

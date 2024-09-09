package com.kedu.game.services;

import com.kedu.game.DAO.FriendDAO;
import com.kedu.game.DTO.FriendDTO;
import com.kedu.game.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FriendService {

    @Autowired
    FriendDAO friendDAO;

    public Map<String, Object> selectFriendList(int friend_owner_seq){
        return friendDAO.selectFriendList(friend_owner_seq);
    }

    public int insertFriendList(int friend_owner_seq){
        return friendDAO.insertFriendList(friend_owner_seq);
    }
    public List<UserDTO> findUserAsKeyword(String user_tag_id){
        return friendDAO.findUserAsKeyword(user_tag_id);
    }
}

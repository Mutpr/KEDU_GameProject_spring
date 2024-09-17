package com.kedu.game.services;

import com.kedu.game.DAO.FriendDAO;
import com.kedu.game.DTO.FriendDTO;
import com.kedu.game.DTO.FriendRequestDTO;
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
    public List<UserDTO> findUserAsKeyword(Map<String, Object> params){
        return friendDAO.findUserAsKeyword(params);
    }

    public int addFriendRequest(Map<String, String> params){
        return friendDAO.addFriendRequest(params);
    }

    public List<FriendRequestDTO> findFriendRequest(int user_seq){
        return friendDAO.findFriendRequest(user_seq);
    }
    public List<FriendRequestDTO> findReceivedFriendRequest(int user_seq){
        return friendDAO.findReceivedFriendRequest(user_seq);
    }

    public int requestAgree(int user_seq){
        return friendDAO.requestAgree(user_seq);
    }

    public int requestDisagree(int request_seq){
        return friendDAO.requestDisagree(request_seq);
    }

    public int findRequestSeqByUser(Map<String, Integer> params){
        return friendDAO.findRequestSeqByUser(params);
    }
}

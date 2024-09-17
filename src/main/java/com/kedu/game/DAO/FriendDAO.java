package com.kedu.game.DAO;

import com.kedu.game.DTO.FriendDTO;
import com.kedu.game.DTO.FriendRequestDTO;
import com.kedu.game.DTO.UserDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class FriendDAO {

    @Autowired
    SqlSession session;

    private final static String NAMESPACE="Friend.";

    public Map<String, Object> selectFriendList(int friend_owner_seq){
        return session.selectOne(NAMESPACE+"selectFriendListClob", friend_owner_seq);
    }
    public int insertFriendList(int friend_owner_seq){
        return session.insert(NAMESPACE+"insertFriendList", friend_owner_seq);
    }

    public List<UserDTO> findUserAsKeyword(Map<String, Object> params){
        return session.selectList(NAMESPACE+"findUserAsKeyword", params);
    }

    public int addFriendRequest(Map<String, String> params){
        int friend_request_owner_seq = Integer.parseInt(params.get("friend_request_owner_seq"));
        int friend_request_sender_seq = Integer.parseInt(params.get("friend_request_sender_seq"));
        FriendRequestDTO dto = new FriendRequestDTO(friend_request_owner_seq, friend_request_sender_seq);
        return session.insert(NAMESPACE+"addFriendRequest", dto);
    }

    public List<FriendRequestDTO> findFriendRequest(int user_seq){
        return session.selectList(NAMESPACE+"findFriendRequest", user_seq);
    }

    public List<FriendRequestDTO> findReceivedFriendRequest(int user_seq){
        return session.selectList(NAMESPACE+"findReceivedFriendRequest", user_seq);
    }

    public int requestAgree(int request_seq){
        return session.update(NAMESPACE+"requestAgree", request_seq);
    }

    public int requestDisagree(int request_seq){
        return session.update(NAMESPACE+"requestDisagree", request_seq);
    }

    public int findRequestSeqByUser(Map<String, Integer> params){
        return session.selectOne(NAMESPACE+"findRequestSeqByUser", params);
    }
//
//    //agree랑 동시 발생
//    public int friendListUpdate(Map){
//        return session.update(NAMESPACE+"friendListUpdate", friend_list);
//    }

    //같은 유저에게서 같은 유저에게 발송된 친추요청 있는지 확인
    public int friendRequestTest(FriendRequestDTO friendRequestDTO){
        return session.selectOne(NAMESPACE+"friendRequestTest", friendRequestDTO);
    }
}

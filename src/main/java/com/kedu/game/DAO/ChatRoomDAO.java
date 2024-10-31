package com.kedu.game.DAO;

import com.kedu.game.DTO.ChatRoomDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatRoomDAO {
    @Autowired
    SqlSession session;
    private final static String NAMESPACE="Chat.";

    public int insertChatRoom(ChatRoomDTO chatRoomDTO){
        return session.insert(NAMESPACE+"insertChatRoom", chatRoomDTO);
    }

    public int selectNextval(){
        return session.selectOne(NAMESPACE+"selectNextval");
    }

    public List<ChatRoomDTO> selectChatByUserSeq(String user_seq){
        return session.selectList(NAMESPACE+"selectChatByUserSeq", user_seq);
    }
}

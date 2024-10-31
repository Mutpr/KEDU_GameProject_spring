package com.kedu.game.services;

import com.kedu.game.DAO.ChatRoomDAO;
import com.kedu.game.DTO.ChatRoomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRoomService {

    @Autowired
    ChatRoomDAO chatRoomDAO;

    public int insertChatRoom(ChatRoomDTO chatRoomDTO){
       return chatRoomDAO.insertChatRoom(chatRoomDTO);
    }

    public int selectNextval(){
        return chatRoomDAO.selectNextval();
    }

    public List<ChatRoomDTO> selectChatByUserSeq(String user_seq){
        return chatRoomDAO.selectChatByUserSeq(user_seq);
    }
}

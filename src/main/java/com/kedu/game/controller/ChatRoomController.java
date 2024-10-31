package com.kedu.game.controller;

import com.kedu.game.DTO.ChatRoomDTO;
import com.kedu.game.services.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/chatroom")
public class ChatRoomController {

    @Autowired
    ChatRoomService chatRoomService;

    @PostMapping
    public ResponseEntity<Integer> insertChatRoom(@RequestBody ChatRoomDTO insertParams){
        System.out.println("title::: "+insertParams.getChatroom_title());
//        int nextval = chatRoomService.selectNextval();
//        System.out.println(nextval);
        String chatRoomId = getUUIDFromnextVal();
        String chatroomUserlist = "["+insertParams.getChatroom_user_list()+"]";
        ChatRoomDTO chatRoomDTO = new ChatRoomDTO(chatRoomId,insertParams.getChatroom_title(),chatroomUserlist,insertParams.getChatroom_desc());
        int chatRoomInsertResult = chatRoomService.insertChatRoom(chatRoomDTO);
        System.out.println(chatRoomInsertResult);
        return ResponseEntity.ok(chatRoomInsertResult);
    }

    public String getUUIDFromnextVal(){
        UUID randomUUID = UUID.randomUUID();
        return randomUUID.toString();
    }

    @GetMapping
    public ResponseEntity<List<ChatRoomDTO>> selectChatByUserSeq(@RequestParam String userSeq){
        List<ChatRoomDTO> chatRoomDTOList = chatRoomService.selectChatByUserSeq(String.valueOf(userSeq));
        System.out.println("chatroomList::: "+chatRoomDTOList.toString());
        return ResponseEntity.ok(chatRoomDTOList);
//        System.out.println(userSeq);
//        return ResponseEntity.ok().build();
    }


}

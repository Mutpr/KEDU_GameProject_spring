package com.kedu.game.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kedu.game.DTO.FriendDTO;
import com.kedu.game.DTO.UserDTO;
import com.kedu.game.services.FriendService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    FriendService friendService;

    @GetMapping
    public ResponseEntity<Boolean> getFriendList(@RequestParam Map<String, Integer> user_seq){
        int userSeq = Integer.parseInt(String.valueOf(user_seq.get("user_seq")));
        Map<String, Object> friendList = friendService.selectFriendList(userSeq);
        System.out.println(friendList.get("friend_list"));
        if(friendList.get("friend_list").toString().equals("[]")){
            return ResponseEntity.ok(false);
        }else{
            return ResponseEntity.ok(true);
        }
    }

    @PostMapping
    public ResponseEntity<Integer> insertFriendList(@RequestBody Map<String, Object> userSeq) {
        // 데이터를 바로 출력
        System.out.println(userSeq);

        // 숫자가 Double이고 정수인 경우 Integer로 변환
        userSeq.forEach((key, value) -> {
            if (value instanceof Double) {
                Double doubleValue = (Double) value;
                if (doubleValue == doubleValue.intValue()) {
                    userSeq.put(key, doubleValue.intValue());
                }
            }
        });

        // 변환된 맵 확인
        System.out.println(userSeq);
        Map<String, String> map = (Map<String, String>) userSeq.get("params");
        // 데이터베이스에 사용자 시퀀스 추가 작업 등
         int result = friendService.insertFriendList(Integer.parseInt(String.valueOf(map.get("user_seq"))));
        System.out.println("friend list insert result::::"+result);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/userSearch")
    public ResponseEntity<List<Map<String, Object>>> searchUser(@RequestParam Map<String, Object> params) {
        Object keywordObj = params.get("searchKeyword");
        if (keywordObj == null || !(keywordObj instanceof String)) {
            return ResponseEntity.badRequest().build();
        }

        String keyword = keywordObj.toString();
        System.out.println("keyword::::: " + keyword);
        List<UserDTO> searchedUserList = friendService.findUserAsKeyword(keyword);
        List<Map<String, Object>> userList = new ArrayList<>();

        for (UserDTO user : searchedUserList) {
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("user_seq", user.getUser_seq());
            userMap.put("user_tag_id", user.getUser_tag_id());
            userMap.put("user_name", user.getUser_name());
            System.out.println("username:::: " + user.getUser_name());
            userList.add(userMap);
        }

        return ResponseEntity.ok(userList);
    }

}

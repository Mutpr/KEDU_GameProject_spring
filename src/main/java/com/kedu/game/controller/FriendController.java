package com.kedu.game.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kedu.game.DTO.FriendDTO;
import com.kedu.game.DTO.FriendRequestDTO;
import com.kedu.game.DTO.UserDTO;
import com.kedu.game.services.FriendService;
import com.kedu.game.services.UserService;
import org.apache.catalina.User;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.*;

@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    FriendService friendService;

    @Autowired
    UserService userService;

    @GetMapping("/{seq}")
    public ResponseEntity<List<UserDTO>> getFriendList(@PathVariable int seq){
//        int userSeq = Integer.parseInt(String.valueOf(user_seq.get("user_seq")));
//        Map<String, Object> friendList = friendService.selectFriendList(userSeq);
//        System.out.println(friendList.get("friend_list"));
        int friend_list_seq = userService.selectFriendListSeq(seq);
        System.out.println("friend_list_seq::::::  "+friend_list_seq);
        List<UserDTO> userList = new ArrayList<>();
//        UserDTO user = userService.getUserBySeq()
        //일단 friendListSeq 에서 friendSeq 몇번인지 가져와야함

        return ResponseEntity.ok(userList);
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
        Object userSeqObj = params.get("userSeq");

        if (keywordObj == null || !(keywordObj instanceof String)) {
            return ResponseEntity.badRequest().build();
        }

        String keyword = keywordObj.toString();
        int Seq= Integer.parseInt(userSeqObj.toString());

        System.out.println("keyword::::: " + keyword);
        System.out.println("Seq::::: " + Seq);

        Map<String, Object> param = new HashMap<>();

        param.put("user_tag_id", keywordObj.toString());
        param.put("user_seq", Integer.parseInt(userSeqObj.toString()));

        List<UserDTO> searchedUserList = friendService.findUserAsKeyword(param);
        List<Map<String, Object>> userList = new ArrayList<>();
//유저에게 친추 리퀘스트 보낸적 있는지 확인하는 메소드도 추가해야함!!
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

    //친추 요청 저장하는 메소드
    @PostMapping("/addFriend")
    public ResponseEntity<String> addFriend(@RequestBody Map<String, Object> params) throws JsonProcessingException {

        Object param = params.get("params");
        System.out.println("add friend param:::: "+param);
        String paramToString = param.toString().trim();
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>() {}.getType();
        Map<String, String> map = gson.fromJson(paramToString, type);
        System.out.println("map:::: "+map);

        int friend_request_owner_seq = Integer.parseInt(map.get("friend_request_owner_seq"));
        int friend_request_sender_seq = Integer.parseInt(map.get("friend_request_sender_seq"));

        FriendRequestDTO friendRequestDTO = new FriendRequestDTO(friend_request_owner_seq, friend_request_sender_seq);
        int findResult = friendService.friendRequestTest(friendRequestDTO);
        System.out.println("findResult:::: "+findResult);
        if(findResult > 0) {
//            System.out.println("이미 친구 신청을 보낸 유저입니다.");
            return ResponseEntity.ok("이미 친구 요청을 보낸 유저입니다.");
        }else{
            int result = friendService.addFriendRequest(map);
            if(result <= 0){
                return ResponseEntity.ok("친구 요청을 전송하는데 실패했습니다.");
            }else{
                return ResponseEntity.ok("친구 요청을 전송하는데 성공했습니다.");
            }
        }

//        return ResponseEntity.ok().build();
    }


    //내가 보낸 친구 요청 리스트 찾는 메소드
    @GetMapping("/findRequest")
    public ResponseEntity<List<UserDTO>> findFriendRequest(@RequestParam Map<String ,Object> user_seq){
        List<FriendRequestDTO> requestList = friendService.findFriendRequest(Integer.parseInt(user_seq.get("user_seq").toString()));
        List<UserDTO> userList = new ArrayList<>();
        for(FriendRequestDTO request: requestList){
            UserDTO user = userService.findUserAsUserSeq(request.getFriend_request_sender_seq());
            System.out.println(user.getUser_tag_id());
            userList.add(user);
        }
        return ResponseEntity.ok(userList);
//        System.out.println(requestList.toString());
//        return ResponseEntity.ok().build();
    }

    //내가 받은 친구 요청 찾는 메소드
    @GetMapping("/findReceivedRequest")
    public ResponseEntity<List<UserDTO>> findReceivedFriendRequest(@RequestParam int user_seq){
        List<FriendRequestDTO> recievedRequestList = friendService.findReceivedFriendRequest(user_seq);
        List<UserDTO> userList = new ArrayList<>();
        for(FriendRequestDTO request:recievedRequestList){
            UserDTO user = userService.findUserAsUserSeq(request.getFriend_request_owner_seq());
            System.out.println(user.getUser_tag_id());
            userList.add(user);
        }

        return ResponseEntity.ok(userList);
    }

    @PostMapping("/requestAgree")
    public ResponseEntity<Boolean> requestAgree(@RequestBody Map<String, Object> params){
//        System.out.println("user_seq:::: "+params.toString());
        Object param = params.get("params");
        System.out.println("add friend param:::: "+param);
        String paramToString = param.toString().trim();
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>() {}.getType();
        Map<String, String> map = gson.fromJson(paramToString, type);
        System.out.println("map:::: "+map);
        String friendList = userService.getFriendList(Integer.parseInt(map.get("user_seq")));
        Map<String, Object> parameter = new HashMap<>();
        System.out.println("friendList::: "+friendList);
        if(friendList.equals("[]")){
            parameter.put("friend_list", "["+map.get("owner_seq")+"]");
            parameter.put("user_seq", map.get("user_seq"));
          int updateFriendList = userService.friendListUpdate(parameter);
//            System.out.println("["+map.get("owner_seq")+"]");
            System.out.println(updateFriendList);
        }else{
            String str = getUpdatedUserList("[1,2]", Integer.parseInt(map.get("owner_seq")));
            System.out.println("str:::: "+str);
        }
//        System.out.println(splitResult);
        //friendList update 해야하고
//        int result = friendService.requestAgree();
        //friendRequest 상태 업데이트
        return ResponseEntity.ok().build();
    }

    @PostMapping("/requestDisagree")
    public ResponseEntity<Boolean> requestDisagree(@RequestBody Map<String, Object> params){
        //friendRequest 만 삭제
        Object param = params.get("params");
        String paramToString = param.toString().trim();
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Integer>>() {}.getType();
        Map<String, Integer> map = gson.fromJson(paramToString, type);
        int requestSeq = friendService.findRequestSeqByUser(map);
        System.out.println(requestSeq);
        int disagreeResult = friendService.requestDisagree(requestSeq);
        System.out.println(disagreeResult);
//        Map<String, Integer>
        return ResponseEntity.ok().build();

    }
//userList 가공하는 메소드
    public String getUpdatedUserList(String originalFriendList, int addedUserSeq){
        //[1,2,3] 형태인지 필히 확인할것!!!!
        String str = originalFriendList.substring(1,originalFriendList.length()-1);
        System.out.println("str::: "+str);
        String updatedStr = str+","+addedUserSeq;
        System.out.println(updatedStr);
        return "["+updatedStr+"]";
//        String [] splitList = str.split(",");
//        //        split
//        List<String> list = new ArrayList<>(Arrays.asList(splitList));
//        list.add(addedUserSeq+"");
//        for(String split: list){
//            System.out.println(split);
//        }
//        return "";
    }
}

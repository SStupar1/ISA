package isa.demo.controller;

import isa.demo.dto.response.FindRoomDTOResponse;
import isa.demo.model.Room;
import isa.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public ResponseEntity<?> findAllRooms() {
        List<Room> rooms = roomService.findAll();
        List<FindRoomDTOResponse> response = new ArrayList<>();
        for(Room r : rooms){
            response.add(new FindRoomDTOResponse(r.getId(),r.getName(),r.getNumber(),null));
        }
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}

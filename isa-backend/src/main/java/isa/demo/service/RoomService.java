package isa.demo.service;

import isa.demo.model.Room;
import isa.demo.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Room findOneByName(String name) {return roomRepository.findByName(name);}
    public List<Room> findByNameAndNumber(String name, int number) { return roomRepository.findByNameAndNumber(name,number);}
    public Room findOneById(Long id) {return roomRepository.findOneById(id);}
    public List<Room> findAll() {return roomRepository.findAll();}
}

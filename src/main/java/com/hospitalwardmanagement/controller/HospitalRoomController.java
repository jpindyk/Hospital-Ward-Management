package com.hospitalwardmanagement.controller;

import com.hospitalwardmanagement.model.hospitalroom.HospitalRoom;
import com.hospitalwardmanagement.payload.HospitalRoomDTO;
import com.hospitalwardmanagement.service.HospitalRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/hospitalrooms")
public class HospitalRoomController {

    @Autowired
    private HospitalRoomService hospitalRoomService;

    @PostMapping
    public ResponseEntity<HospitalRoom> addHospitalRoom (@Valid @RequestBody HospitalRoomDTO hospitalRoomDTO) {
        return new ResponseEntity<HospitalRoom>(hospitalRoomService.addHospitalRoom(hospitalRoomDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HospitalRoom>> getAllHospitalRooms () {
        return new ResponseEntity<List<HospitalRoom>>(hospitalRoomService.getAllHospitalRooms(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalRoom> getHospitalRoomById (@PathVariable Long id) {
        return new ResponseEntity<HospitalRoom>(hospitalRoomService.getHospitalRoomById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HospitalRoom> deleteHospitalRoomById (@PathVariable Long id) {
        hospitalRoomService.deleteHospitalRoomById(id);
        return new ResponseEntity<HospitalRoom>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HospitalRoom> updateHospitalRoomById (@PathVariable Long id, @Valid @RequestBody HospitalRoomDTO hospitalRoomDTO) {
        return new ResponseEntity<HospitalRoom>(hospitalRoomService.updateHospitalRoomById(id, hospitalRoomDTO), HttpStatus.OK);
    }
}

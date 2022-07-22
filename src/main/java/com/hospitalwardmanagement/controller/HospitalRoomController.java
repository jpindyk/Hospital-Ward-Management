package com.hospitalwardmanagement.controller;

import com.hospitalwardmanagement.model.hospitalroom.HospitalRoom;
import com.hospitalwardmanagement.payload.HospitalRoomDTO;
import com.hospitalwardmanagement.service.HospitalRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/hospitalrooms")
public class HospitalRoomController {

    @Autowired
    private HospitalRoomService hospitalRoomService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') || hasRole('NURSE_WARD')")
    public ResponseEntity<HospitalRoom> addHospitalRoom (@Valid @RequestBody HospitalRoomDTO hospitalRoomDTO) {
        return new ResponseEntity<HospitalRoom>(hospitalRoomService.addHospitalRoom(hospitalRoomDTO), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') || hasRole('NURSE_WARD')")
    public ResponseEntity<List<HospitalRoom>> getAllHospitalRooms () {
        return new ResponseEntity<List<HospitalRoom>>(hospitalRoomService.getAllHospitalRooms(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') || hasRole('NURSE_WARD')")
    public ResponseEntity<HospitalRoom> getHospitalRoomById (@PathVariable Long id) {
        return new ResponseEntity<HospitalRoom>(hospitalRoomService.getHospitalRoomById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') || hasRole('NURSE_WARD')")
    public ResponseEntity<HospitalRoom> deleteHospitalRoomById (@PathVariable Long id) {
        hospitalRoomService.deleteHospitalRoomById(id);
        return new ResponseEntity<HospitalRoom>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') || hasRole('NURSE_WARD')")
    public ResponseEntity<HospitalRoom> updateHospitalRoomById (@PathVariable Long id, @Valid @RequestBody HospitalRoomDTO hospitalRoomDTO) {
        return new ResponseEntity<HospitalRoom>(hospitalRoomService.updateHospitalRoomById(id, hospitalRoomDTO), HttpStatus.OK);
    }
}

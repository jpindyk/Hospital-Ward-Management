package com.hospitalwardmanagement.service;

import com.hospitalwardmanagement.model.hospitalroom.HospitalRoom;
import com.hospitalwardmanagement.payload.HospitalRoomDTO;

import java.util.List;

public interface HospitalRoomService {
    HospitalRoom addHospitalRoom(HospitalRoomDTO hospitalRoomDTO);

    HospitalRoom updateHospitalRoomById(Long id, HospitalRoomDTO hospitalRoomDTO);

    void deleteHospitalRoomById(Long id);

    HospitalRoom getHospitalRoomById(Long id);

    List<HospitalRoom> getAllHospitalRooms();

}
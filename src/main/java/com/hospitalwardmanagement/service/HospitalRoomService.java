package com.hospitalwardmanagement.service;

import com.hospitalwardmanagement.model.hospitalroom.HospitalRoom;

import java.util.List;

public interface HospitalRoomService {
    HospitalRoom addHospitalRoom(HospitalRoom hospitalRoom);

    HospitalRoom updateHospitalRoomById(Long id, HospitalRoom hospitalRoom);

    void deleteHospitalRoomById(Long id);

    HospitalRoom getHospitalRoomById(Long id);

    List<HospitalRoom> getAllHospitalRooms();

}
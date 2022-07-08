package com.hospitalwardmanagement.service.implementation;

import com.hospitalwardmanagement.exceptions.ResourceNotFoundException;
import com.hospitalwardmanagement.model.hospitalroom.HospitalRoom;
import com.hospitalwardmanagement.repository.HospitalRoomRepository;
import com.hospitalwardmanagement.service.HospitalRoomService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HospitalRoomServiceImplementation implements HospitalRoomService {

    @Autowired
    HospitalRoomRepository hospitalRoomRepository;

    @Override
    public HospitalRoom addHospitalRoom(HospitalRoom hospitalRoom) {
        return hospitalRoomRepository.save(hospitalRoom);
    }

    @Override
    public HospitalRoom updateHospitalRoomById(Long id, HospitalRoom hospitalRoom) {
        HospitalRoom existingHospitalRoom = getHospitalRoomById(id);

        existingHospitalRoom.setName(hospitalRoom.getName()==null ? existingHospitalRoom.getName() : hospitalRoom.getName());
        existingHospitalRoom.setCapacity(hospitalRoom.getCapacity()==null ? existingHospitalRoom.getCapacity() : hospitalRoom.getCapacity());

        return hospitalRoomRepository.save(existingHospitalRoom);
    }

    @Override
    public void deleteHospitalRoomById(Long id) {
        HospitalRoom hospitalRoomToDelete = getHospitalRoomById(id);
        hospitalRoomRepository.delete(hospitalRoomToDelete);
    }

    @Override
    public HospitalRoom getHospitalRoomById(Long id) {
        return hospitalRoomRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Hospital Room","id",id)
        );
    }

    @Override
    public List<HospitalRoom> getAllHospitalRooms() {
        return hospitalRoomRepository.findAll();
    }
}

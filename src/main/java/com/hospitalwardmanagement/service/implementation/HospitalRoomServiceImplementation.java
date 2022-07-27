package com.hospitalwardmanagement.service.implementation;

import com.hospitalwardmanagement.exceptions.ResourceNotFoundException;
import com.hospitalwardmanagement.model.hospitalroom.HospitalRoom;
import com.hospitalwardmanagement.payload.HospitalRoomDTO;
import com.hospitalwardmanagement.repository.HospitalRoomRepository;
import com.hospitalwardmanagement.repository.PatientRepository;
import com.hospitalwardmanagement.service.HospitalRoomService;
import com.hospitalwardmanagement.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HospitalRoomServiceImplementation implements HospitalRoomService {

    @Autowired
    private HospitalRoomRepository hospitalRoomRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;

    @Override
    public HospitalRoom addHospitalRoom(HospitalRoomDTO hospitalRoomDTO) {
        HospitalRoom hospitalRoom = mapToEntity(hospitalRoomDTO);
        hospitalRoom.getObjectAudit().setCreatedByUser(userService.getLoggedInUser().getId());
        return hospitalRoomRepository.save(hospitalRoom);
    }

    @Override
    public HospitalRoom updateHospitalRoomById(Long id, HospitalRoomDTO hospitalRoomDTO) {
        HospitalRoom existingHospitalRoom = getHospitalRoomById(id);

        existingHospitalRoom.setName(hospitalRoomDTO.getName()==null ? existingHospitalRoom.getName() : hospitalRoomDTO.getName());
        existingHospitalRoom.setCapacity(hospitalRoomDTO.getCapacity()==null ? existingHospitalRoom.getCapacity() : hospitalRoomDTO.getCapacity());
        existingHospitalRoom.getObjectAudit().setLastChangeByUser(userService.getLoggedInUser().getId());

        return hospitalRoomRepository.save(existingHospitalRoom);
    }

    @Override
    public void deleteHospitalRoomById(Long id) {
        patientRepository.findByHospitalRoom(id).stream().forEach(p->p.setHospitalRoom(null));
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

    public HospitalRoom mapToEntity (HospitalRoomDTO hospitalRoomDTO) {
        HospitalRoom hospitalRoom = mapper.map(hospitalRoomDTO, HospitalRoom.class);
        return hospitalRoom;
    }
}

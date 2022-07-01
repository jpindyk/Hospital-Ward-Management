package com.hospitalwardmanagement.repository;

import com.hospitalwardmanagement.model.hospitalroom.HospitalRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRoomRepository extends JpaRepository<HospitalRoom, Long> {
}

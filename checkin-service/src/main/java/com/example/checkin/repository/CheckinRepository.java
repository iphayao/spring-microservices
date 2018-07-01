package com.example.checkin.repository;

import com.example.checkin.entiry.CheckinRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckinRepository extends JpaRepository<CheckinRecord, Long> {
}

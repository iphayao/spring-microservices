package com.example.book.repository;

import com.example.book.entity.BookingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingRecord, Long> {
    BookingRecord findOne(long id);
}

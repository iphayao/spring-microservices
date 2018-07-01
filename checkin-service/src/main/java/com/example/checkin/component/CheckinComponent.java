package com.example.checkin.component;

import com.example.checkin.entiry.CheckinRecord;
import com.example.checkin.repository.CheckinRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CheckinComponent {
    private static final Logger log = LoggerFactory.getLogger(CheckinComponent.class);

    private CheckinRepository checkinRepository;
    private Sender sender;

    @Autowired
    public CheckinComponent(CheckinRepository checkinRepository, Sender sender) {
        this.checkinRepository = checkinRepository;
        this.sender = sender;
    }

    public long checkIn(CheckinRecord checkIn) {
        checkIn.setCheckInTime(new Date());
        log.info("Saving checkin");

        // save
        long id = checkinRepository.save(checkIn).getId();
        log.info("Successfully saved checkin");
        // send a message back to booking to update status
        log.info("Sending booking id " + id);
        sender.send(id);

        return id;
    }

    public CheckinRecord getCheckinRecord(long id) {
        return checkinRepository.findById(id).get();
    }
}

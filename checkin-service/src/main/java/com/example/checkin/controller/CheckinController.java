package com.example.checkin.controller;

import com.example.checkin.component.CheckinComponent;
import com.example.checkin.entiry.CheckinRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/checkin")
public class CheckinController {
    CheckinComponent checkinComponent;

    @Autowired
    public CheckinController(CheckinComponent checkinComponent) {
        this.checkinComponent = checkinComponent;
    }

    @RequestMapping("/{id}")
    CheckinRecord getCheckin(@PathVariable long id) {
        return checkinComponent.getCheckinRecord(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    long checkIn(@RequestBody CheckinRecord checkIn) {
        return checkinComponent.checkIn(checkIn);
    }
}

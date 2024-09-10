package com.loop614.analyticstribble.swipe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.loop614.analyticstribble.swipe.SwipeService;
import com.loop614.analyticstribble.swipe.entity.Swipe;
import com.loop614.analyticstribble.swipe.transfer.SwipeFilterTransfer;

public class SwipeController {
    @Autowired
    private final SwipeService swipeService;

    public SwipeController(SwipeService swipeService) {
        this.swipeService = swipeService;
    }

    @GetMapping("/swipe/{domain}/{customer}/{dateNanoFrom}/{dateNanoTo}")
    @CrossOrigin(origins = "http://localhost:6969")
    public ResponseEntity<List<Swipe>> getSwipes(@ModelAttribute SwipeFilterTransfer swipeFilter) {
        return new ResponseEntity<>(this.swipeService.getSwipes(swipeFilter), HttpStatus.OK);
    }
}

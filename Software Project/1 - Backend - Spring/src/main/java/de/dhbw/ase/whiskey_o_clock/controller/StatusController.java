package de.dhbw.ase.whiskey_o_clock.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RestController
@RequestMapping("")
public class StatusController {

    @GetMapping(value = "")
    public String isOnline() {
        return new StringBuilder("Status of the Backend : " + "!!! ** active ** !!!").toString();
    }

}

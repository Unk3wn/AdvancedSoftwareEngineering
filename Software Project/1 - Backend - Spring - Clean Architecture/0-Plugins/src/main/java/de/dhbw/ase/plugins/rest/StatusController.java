package de.dhbw.ase.plugins.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

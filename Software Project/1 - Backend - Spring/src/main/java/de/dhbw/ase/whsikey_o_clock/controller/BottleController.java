package de.dhbw.ase.whsikey_o_clock.controller;

import de.dhbw.ase.whsikey_o_clock.model.Bottle;
import de.dhbw.ase.whsikey_o_clock.service.BottleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bottle")
public class BottleController{

    @Autowired
    BottleService bottleService;

    @GetMapping("")
    public List<Bottle> getAllBottles() {
        return bottleService.getBottles();
    }
}

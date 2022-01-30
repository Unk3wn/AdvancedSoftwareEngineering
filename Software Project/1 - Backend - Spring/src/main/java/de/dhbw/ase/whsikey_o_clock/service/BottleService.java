package de.dhbw.ase.whsikey_o_clock.service;

import de.dhbw.ase.whsikey_o_clock.model.Bottle;
import de.dhbw.ase.whsikey_o_clock.repository.BottleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BottleService {

    @Autowired
    BottleRepository bottleRepository;

    public List<Bottle> getBottles(){
        return bottleRepository.findAll();
    }

}

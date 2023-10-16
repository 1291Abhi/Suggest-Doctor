package com.example.Assignment_lattice.Controller;

import com.example.Assignment_lattice.Exception.DoctorNotFoundException;
import com.example.Assignment_lattice.Model.Doctor;
import com.example.Assignment_lattice.Service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    DoctorService doctorService;
    @PostMapping("/add")
    public ResponseEntity addDoctor(@Valid @RequestBody Doctor doctor){
        doctorService.addDoctor(doctor);
        return new ResponseEntity("Doctor added Successfully",HttpStatus.CREATED);
    }
    @DeleteMapping("remove/{doctorId}")
    public ResponseEntity removeDoctor(@PathVariable Integer doctorId){
        try{
            doctorService.removeDoctor(doctorId);
            return new ResponseEntity("Doctor removed Successfully",HttpStatus.OK);
        } catch (DoctorNotFoundException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }


    }
}

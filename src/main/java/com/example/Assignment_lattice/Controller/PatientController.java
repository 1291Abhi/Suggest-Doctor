package com.example.Assignment_lattice.Controller;

import com.example.Assignment_lattice.Exception.PatientNotFoundException;
import com.example.Assignment_lattice.Model.Patient;
import com.example.Assignment_lattice.Service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    PatientService patientService;

    @PostMapping("/add")
    public ResponseEntity addPatient(@Valid @RequestBody Patient patient){
        patientService.addPatient(patient);
        return new ResponseEntity("Patient added Successfully", HttpStatus.CREATED);
    }
    @DeleteMapping("/remove/{patientId}")
    public ResponseEntity removePatient(@PathVariable Integer patientId){
        try {
            patientService.removePatient(patientId);
            return new ResponseEntity("Patient removed Successfully",HttpStatus.OK);
        } catch (PatientNotFoundException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}

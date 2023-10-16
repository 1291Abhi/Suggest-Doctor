package com.example.Assignment_lattice.Controller;

import com.example.Assignment_lattice.Exception.CityNotCoveredException;
import com.example.Assignment_lattice.Exception.DoctorNotPresentException;
import com.example.Assignment_lattice.Exception.PatientNotFoundException;
import com.example.Assignment_lattice.Model.Doctor;
import com.example.Assignment_lattice.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/suggestDoctor")
public class SuggestedDoctorController {
    @Autowired
    PatientService patientService;
    @GetMapping("/{patientId}")
    public ResponseEntity suggestedDoctor(@PathVariable Integer patientId){
        List<Doctor> doctorList= null;
        try {
            doctorList = patientService.suggestedDoctor(patientId);
            return new ResponseEntity(doctorList, HttpStatus.OK);
        } catch (PatientNotFoundException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (CityNotCoveredException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (DoctorNotPresentException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }

}

package com.example.Assignment_lattice.Controller;


import com.example.Assignment_lattice.Exception.*;
import com.example.Assignment_lattice.Model.Appointment;
import com.example.Assignment_lattice.ResponseDTO.AppointmentResponseDTO;
import com.example.Assignment_lattice.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;
    @PostMapping("/add/{patientId}/{doctorId}")
    public ResponseEntity addAppointment(@PathVariable int patientId,@PathVariable int doctorId ){
        try {
            AppointmentResponseDTO appointment=appointmentService.addAppointment(patientId,doctorId);
            return new ResponseEntity(appointment,HttpStatus.OK);
        } catch (PatientNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (DoctorNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add/suggested_doctor/{patientId}")
    public ResponseEntity addAppointmentWithSugeestedDoctor(@PathVariable int patientId){
        try {
            AppointmentResponseDTO appointment=appointmentService.addAppointmentWithSuggestedDoctor(patientId);
            return new ResponseEntity(appointment,HttpStatus.OK);
        } catch (DoctorNotPresentException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (PatientNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (CityNotCoveredException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (DoctorNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("find_appointment_by_patient/{patientId}")
    public ResponseEntity findAppointmentByPatientId(@PathVariable int patientId){
        try {
            List<AppointmentResponseDTO> appointment=appointmentService.findAppointmentByPatientId(patientId);
            return new ResponseEntity(appointment,HttpStatus.FOUND);
        } catch (PatientNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (AppointmentNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("find_appointment_by_doctor/{doctorId}")
    public ResponseEntity findAppointmentByDoctorId(@PathVariable int doctorId){
        try {
            List<AppointmentResponseDTO> appointment=appointmentService.findAppointmentByDoctorId(doctorId);
            return new ResponseEntity(appointment,HttpStatus.FOUND);
        } catch (DoctorNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (AppointmentNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

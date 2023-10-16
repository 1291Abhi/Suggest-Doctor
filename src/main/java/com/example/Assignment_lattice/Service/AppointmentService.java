package com.example.Assignment_lattice.Service;

import com.example.Assignment_lattice.Controller.SuggestedDoctorController;
import com.example.Assignment_lattice.Exception.*;
import com.example.Assignment_lattice.Model.Appointment;
import com.example.Assignment_lattice.Model.Doctor;
import com.example.Assignment_lattice.Model.Patient;
import com.example.Assignment_lattice.Repository.AppointmentRepository;
import com.example.Assignment_lattice.Repository.DoctorRepository;
import com.example.Assignment_lattice.ResponseDTO.AppointmentResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    PatientService patientService;
    @Autowired
    DoctorService doctorService;


    public AppointmentResponseDTO addAppointment(int patientId, int doctorId) throws PatientNotFoundException, DoctorNotFoundException {
        Patient patient=patientService.findPatientById(patientId);
        Doctor doctor=doctorService.findDoctorById(doctorId);
        Appointment appointment=new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointmentRepository.save(appointment);
        AppointmentResponseDTO appointmentResponseDTO=new AppointmentResponseDTO();
        return appointmentResponseDTO.appointmentToAppointmentResponseDTO(appointment);


    }

    public List<AppointmentResponseDTO> findAppointmentByPatientId(int patientId) throws PatientNotFoundException, AppointmentNotFoundException {
        Patient patient=patientService.findPatientById(patientId);
        Optional<List<Appointment>> appointment=appointmentRepository.findByPatient(patient);
        if(appointment.get().size()==0)
            throw new AppointmentNotFoundException("No Appointment found for the patient");
        List<AppointmentResponseDTO> appointmentResponseDTOList=new ArrayList<>();
        for(Appointment appointment1:appointment.get()){
            AppointmentResponseDTO appointmentResponseDTO=new AppointmentResponseDTO();
            appointmentResponseDTOList.add(appointmentResponseDTO.appointmentToAppointmentResponseDTO(appointment1));
        }
        return appointmentResponseDTOList;
    }

    public List<AppointmentResponseDTO> findAppointmentByDoctorId(int doctorId) throws DoctorNotFoundException, AppointmentNotFoundException {
        Doctor doctor=doctorService.findDoctorById(doctorId);
        Optional<List<Appointment>> appointment=appointmentRepository.findByDoctor(doctor);
        if(appointment.get().size()==0)
            throw new AppointmentNotFoundException("No Appointment found for the doctor");
        List<AppointmentResponseDTO> appointmentResponseDTOList=new ArrayList<>();
        for(Appointment appointment1:appointment.get()){
            AppointmentResponseDTO appointmentResponseDTO=new AppointmentResponseDTO();
            appointmentResponseDTOList.add(appointmentResponseDTO.appointmentToAppointmentResponseDTO(appointment1));
        }
        return appointmentResponseDTOList;
    }

    public AppointmentResponseDTO addAppointmentWithSuggestedDoctor(int patientId) throws DoctorNotPresentException, PatientNotFoundException, CityNotCoveredException, DoctorNotFoundException {
        List<Doctor> suggestedDoctor=patientService.suggestedDoctor(patientId);
        return addAppointment(patientId,suggestedDoctor.get(0).getId());

    }
}

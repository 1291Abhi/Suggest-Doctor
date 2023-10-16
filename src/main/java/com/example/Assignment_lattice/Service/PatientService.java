package com.example.Assignment_lattice.Service;

import com.example.Assignment_lattice.Enum.City;
import com.example.Assignment_lattice.Enum.Speciality;
import com.example.Assignment_lattice.Enum.Symptom;
import com.example.Assignment_lattice.Exception.CityNotCoveredException;
import com.example.Assignment_lattice.Exception.DoctorNotPresentException;
import com.example.Assignment_lattice.Exception.PatientNotFoundException;
import com.example.Assignment_lattice.Model.Doctor;
import com.example.Assignment_lattice.Model.Patient;
import com.example.Assignment_lattice.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorService doctorService;
    public void addPatient(Patient patient) {
        patientRepository.save(patient);
    }

    public void removePatient(Integer patientId) throws PatientNotFoundException {
        Optional<Patient> optional=patientRepository.findById(patientId);
        if(optional.isEmpty()){
            throw new PatientNotFoundException("Patient with given patientId is not present in database");
        }
        else {
            patientRepository.deleteById(patientId);
        }
    }

    public List<Doctor> suggestedDoctor(Integer patientId) throws PatientNotFoundException, CityNotCoveredException, DoctorNotPresentException {
        Optional<Patient> patientOptional=patientRepository.findById(patientId);
        List<Doctor> doctorList;
        if(patientOptional.isEmpty()){
            throw new PatientNotFoundException("Patient with the given id is not present in the database");
        }else {
            String city = patientOptional.get().getCity().toUpperCase();
            Symptom symptom = patientOptional.get().getSymptom();
            Speciality speciality = null;
            if (symptom.equals(Symptom.ARTHRITIS) || symptom.equals(Symptom.BACK_PAIN) || symptom.equals(Symptom.TISSUE_INJURIES)){
                speciality = Speciality.ORTHOPEDIC;
             }
            else if (symptom.equals(Symptom.DYSMENORRHEA)) {
                speciality=Speciality.GYNECOLOGY;
            } else if (symptom.equals(Symptom.SKIN_BURN) || symptom.equals(Symptom.SKIN_INFECTION)){
                speciality=Speciality.DERMATOLOGY;
            }else if(symptom.equals(Symptom.EAR_PAIN) || symptom.equals(Symptom.EYE_PAIN)){
                speciality=Speciality.ENT;
            }
            if(city.equals("FARIDABAD") || city.equals("DELHI") || city.equals("NOIDA")){
               doctorList=doctorService.suggestDoctorByLocationAndSpeciality(city,speciality);
            }
            else{
                throw new CityNotCoveredException("We are still waiting to expand to your location");
            }

            return doctorList;
        }
    }

    public Patient findPatientById(int patientId) throws PatientNotFoundException {
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if (optionalPatient.isEmpty())
            throw new PatientNotFoundException("Patient with given id is not present in the database... Try with correct Id");
        return optionalPatient.get();
    }
}

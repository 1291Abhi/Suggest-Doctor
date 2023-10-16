package com.example.Assignment_lattice.Service;

import com.example.Assignment_lattice.Enum.City;
import com.example.Assignment_lattice.Enum.Speciality;
import com.example.Assignment_lattice.Exception.DoctorNotFoundException;
import com.example.Assignment_lattice.Exception.DoctorNotPresentException;
import com.example.Assignment_lattice.Exception.PatientNotFoundException;
import com.example.Assignment_lattice.Model.Doctor;
import com.example.Assignment_lattice.Model.Patient;
import com.example.Assignment_lattice.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;
    public void addDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public void removeDoctor(Integer doctorId) throws DoctorNotFoundException {
        Optional<Doctor> optionalDoctor=doctorRepository.findById(doctorId);
        if(optionalDoctor.isEmpty()){
            throw new DoctorNotFoundException("Doctor with given doctorId is not present in the database");
        }
        else{
            doctorRepository.deleteById(doctorId);
        }
    }

    public List<Doctor> suggestDoctorByLocationAndSpeciality(String city, Speciality speciality) throws DoctorNotPresentException {
        Optional<List<Doctor>> optionalList=doctorRepository.findByCityAndSpeciality(City.valueOf(city),speciality);
        if(optionalList.get().size()==0){
            throw new DoctorNotPresentException("There isn't any doctor present at your location for your symptom");
        }
        return optionalList.get();
    }

    public Doctor findDoctorById(int doctorId) throws DoctorNotFoundException {
        Optional<Doctor> optionalPatient = doctorRepository.findById(doctorId);
        if (optionalPatient.isEmpty())
            throw new DoctorNotFoundException("Doctor with given id is not present in the database... Try with correct Id");
        return optionalPatient.get();
    }
}

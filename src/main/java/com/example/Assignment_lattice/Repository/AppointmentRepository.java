package com.example.Assignment_lattice.Repository;

import com.example.Assignment_lattice.Model.Appointment;
import com.example.Assignment_lattice.Model.Doctor;
import com.example.Assignment_lattice.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {

    Optional<List<Appointment>> findByPatient(Patient patient);

    Optional<List<Appointment>> findByDoctor(Doctor doctor);
}

package com.example.Assignment_lattice.Repository;

import com.example.Assignment_lattice.Enum.City;
import com.example.Assignment_lattice.Enum.Speciality;
import com.example.Assignment_lattice.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {

    Optional<List<Doctor>> findByCityAndSpeciality(City city, Speciality speciality);
}

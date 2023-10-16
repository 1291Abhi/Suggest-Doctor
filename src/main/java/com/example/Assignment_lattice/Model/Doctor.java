package com.example.Assignment_lattice.Model;

import com.example.Assignment_lattice.Enum.City;
import com.example.Assignment_lattice.Enum.Speciality;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Size(min=3,message = "Name must have at least 3 character")
    String name;

    @Enumerated(EnumType.STRING)
    City city;

    @Email(message = "Please provide valid email ID")
    String email;

    @Size(min=10,message = "Phone number must have 10 number at least")
    String phoneNumber;

    @Enumerated(EnumType.STRING)
    Speciality speciality;

    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
    List<Appointment> appointments=new ArrayList<>();

}

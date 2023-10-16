package com.example.Assignment_lattice.Model;

import com.example.Assignment_lattice.Enum.Symptom;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
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
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Size(min=3,message = "Name must have at least 3 character")
    String name;

    @Size(max = 20,message = "City must have character not more than 20")
    String city;

    @Email(message = "Please provide valid email ID")
    String email;

    @Size(min=10,message = "Phone number must have 10 number at least")
    String phoneNumber;

    @Enumerated(EnumType.STRING)
    Symptom symptom;

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
    List<Appointment> appointments=new ArrayList<>();
}

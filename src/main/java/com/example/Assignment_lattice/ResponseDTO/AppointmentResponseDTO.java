package com.example.Assignment_lattice.ResponseDTO;

import com.example.Assignment_lattice.Model.Appointment;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.AccessType;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentResponseDTO {
    int appointmentId;
    String patientName;
    String doctorName;
    Date dateOfAppointment;

    public AppointmentResponseDTO appointmentToAppointmentResponseDTO(Appointment appointment){
        AppointmentResponseDTO appointmentResponseDTO= AppointmentResponseDTO.builder()
                .appointmentId(appointment.getId())
                .patientName(appointment.getPatient().getName())
                .doctorName(appointment.getDoctor().getName())
                .dateOfAppointment(appointment.getDateOfAppointment())
                .build();
        return appointmentResponseDTO;
    }
}

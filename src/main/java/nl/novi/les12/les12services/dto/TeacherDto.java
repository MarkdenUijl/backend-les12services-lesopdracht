package nl.novi.les12.les12services.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class TeacherDto {
    public Long id;

    @NotBlank
    @Size(min=3, max=128)
    public String firstName;

    @Size(min=3, max=128)
    public String lastName;

    @Past(message = "mag niet in verleden")
    public LocalDate dob;
    @Max(100000)
    public int salary;
}

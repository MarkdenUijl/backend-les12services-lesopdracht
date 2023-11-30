package nl.novi.les12.les12services.service;

import nl.novi.les12.les12services.dto.TeacherDto;
import nl.novi.les12.les12services.exceptions.DuplicateException;
import nl.novi.les12.les12services.model.Teacher;
import nl.novi.les12.les12services.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository repos;

    public TeacherService(TeacherRepository repos) {
        this.repos = repos;
    }

    public List<TeacherDto> getAllTeachers() {
        List<Teacher> teachers = repos.findAll();
        List<TeacherDto> teacherDtos = new ArrayList<>();

        for (Teacher t : teachers) {
            TeacherDto teacherDto = teacherToDto(t);

            teacherDtos.add(teacherDto);
        }

        return teacherDtos;
    }

    public TeacherDto createTeacher(TeacherDto teacherDto) {
        Teacher teacher = dtoToTeacher(teacherDto);
        String firstName = teacher.getFirstName();
        String lastName = teacher.getLastName();

        List<Teacher> teachersWithMatchingName = repos.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName,lastName);

        //Optional <List<Teacher>> existingTeacher = Optional.ofNullable(repos.findByFirstNameAndLastName(firstName, lastName));

        if (teachersWithMatchingName.size() == 0) {
            repos.save(teacher);
            teacherDto.id = teacher.getId();

            return teacherDto;
        } else {
            throw new DuplicateException("We already found an entry with this name.");
        }
    }

    public Teacher dtoToTeacher(TeacherDto teacherDto) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherDto.firstName);
        teacher.setLastName(teacherDto.lastName);
        teacher.setDob(teacherDto.dob);
        teacher.setSalary(teacherDto.salary);

        return teacher;
    }

    public TeacherDto teacherToDto(Teacher teacher) {
        TeacherDto dto = new TeacherDto();

        dto.id = teacher.getId();
        dto.firstName = teacher.getFirstName();
        dto.lastName = teacher.getLastName();
        dto.dob = teacher.getDob();
        dto.salary = teacher.getSalary();

        return dto;
    }
}

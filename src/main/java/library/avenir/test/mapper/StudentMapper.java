package library.avenir.test.mapper;

import library.avenir.test.dto.student.StudentCreateDto;
import library.avenir.test.dto.student.StudentDto;
import library.avenir.test.dto.student.StudentUpdateDto;
import library.avenir.test.entity.Student;

public interface StudentMapper {
    StudentDto toStudentDto(Student student);
    Student toStudent(StudentCreateDto studentCreateDto);
    Student toStudent(Student updatingStudent, StudentUpdateDto studentUpdateDto);
}

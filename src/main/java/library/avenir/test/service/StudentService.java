package library.avenir.test.service;

import library.avenir.test.dto.student.StudentCreateDto;
import library.avenir.test.dto.student.StudentDto;
import library.avenir.test.dto.student.StudentUpdateDto;
import library.avenir.test.entity.Book;
import library.avenir.test.entity.Student;
import library.avenir.test.mapper.StudentMapper;
import library.avenir.test.repository.StudentRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final BookService bookService;

    public List<StudentDto> findAll() {
        return studentRepository.findAll().stream()
                .map(studentMapper::toStudentDto)
                .collect(Collectors.toList());
    }

    public StudentDto save(StudentCreateDto studentCreateDto) {
        Student student = studentMapper.toStudent(studentCreateDto);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toStudentDto(savedStudent);
    }

    public StudentDto getById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no Student with id " + id));
        return studentMapper.toStudentDto(student);
    }

    public StudentDto update(Long id, StudentUpdateDto studentUpdateDto) {
        Student updatingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no Student with id " + id));
        Student student = studentMapper.toStudent(updatingStudent, studentUpdateDto);
        Student updatedStudent = studentRepository.save(student);
        return studentMapper.toStudentDto(updatedStudent);
    }

	public void delete(Long id) {
		Student deletingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no Student with id " + id));
		studentRepository.delete(deletingStudent);
	}

	/*
	public void borrowBooks(Long id, BookIdsDto dto) {
		Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no Student with id " + id));
		List<Book> books = bookService.findAllById(dto.getIds());
		student.setBorrowings(Sets.newHashSet(books));
		studentRepository.save(student);
	}
	 */
}

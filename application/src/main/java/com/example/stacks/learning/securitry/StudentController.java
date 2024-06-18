package com.example.stacks.learning.securitry;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "api/v1/students")
public class StudentController {

    private final Set<Student> students = new HashSet<>();

    public StudentController() {
        students.add(new Student(1, "Test1"));
        students.add(new Student(2, "Test2"));
        students.add(new Student(3, "Test3"));
        students.add(new Student(4, "Test4"));
        students.add(new Student(5, "Test5"));
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN')")
    public Student getStudent(@PathVariable("id") Integer id) {
        return students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('student:read', 'student:write')")
    public Set<Student> getAllStudents() {
        return students;
    }

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('student:write')")
    public Student addStudent(@RequestBody Student student) {
        Student e = Student.of(students.size() + 1, student.getName());
        students.add(student);
        return e;
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public Student update(@RequestBody Student student) {
        students.stream()
                .filter(st -> st.getId() != null)
                .filter(st -> st.getId().equals(student.getId()))
                .findFirst()
                .ifPresent(st -> st.setName(student.getName()));
        return student;
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public boolean delete(@PathVariable Integer id) {
        return students.removeIf(st -> id.equals(st.getId()));
    }
}

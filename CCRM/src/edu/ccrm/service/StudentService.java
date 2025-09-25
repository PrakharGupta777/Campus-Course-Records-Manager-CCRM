package edu.ccrm.service;

import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;
import java.util.*;

public class StudentService {
    private final Map<String, Student> studentsById = new HashMap<>();

    public void addStudent(Student s) {
        studentsById.put(s.getId(), s);
    }

    public Optional<Student> findById(String id) {
        Student student = studentsById.get(id);
        return student == null ? Optional.empty() : Optional.of(student);
    }

    public void listStudents() {
        for (Student student : studentsById.values()) {
            System.out.println(student);
        }
    }

    public void printTranscript(String studentId) {
        Optional<Student> optStudent = findById(studentId);
        if (optStudent.isPresent()) {
            Student student = optStudent.get();
            System.out.println("Transcript for " + student.getFullName() + " (RegNo: " + student.getRegNo() + ")");
            List<Enrollment> enrollments = student.getEnrollments();
            if (enrollments.isEmpty()) {
                System.out.println("No courses enrolled yet.");
            } else {
                for (Enrollment enrollment : enrollments) {
                    String courseTitle = enrollment.getCourse().getTitle();
                    String grade = enrollment.getGrade() == null ? "N/A" : enrollment.getGrade().toString();
                    System.out.println(courseTitle + " - Grade: " + grade);
                }
            }
            System.out.println("-----------------------------");
        } else {
            System.out.println("Student with ID " + studentId + " not found.");
        }
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(studentsById.values());
    }
}

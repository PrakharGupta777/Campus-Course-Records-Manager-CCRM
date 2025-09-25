package edu.ccrm.service;

import edu.ccrm.domain.*;
import java.util.*;

public class EnrollmentService {
    private final List<Enrollment> enrollments = new ArrayList<>();
    private final int MAX_CREDITS_PER_SEMESTER = 18;

    public void enroll(Student student, Course course) {
        boolean alreadyEnrolled = enrollments.stream()
            .anyMatch(e -> e.getStudent().equals(student) && e.getCourse().equals(course));
        if (alreadyEnrolled) {
            System.out.println("Already enrolled!");
            return;
        }

        int currentCredits = enrollments.stream()
            .filter(e -> e.getStudent().equals(student))
            .mapToInt(e -> e.getCourse().getCredits())
            .sum();

        if (currentCredits + course.getCredits() > MAX_CREDITS_PER_SEMESTER) {
            System.out.println("Cannot enroll! Max credits per semester exceeded.");
            return;
        }

        Enrollment enrollment = new Enrollment(student, course);
        enrollments.add(enrollment);
        student.addEnrollment(enrollment);
        student.enrollCourse(course);

        System.out.println("Enrollment successful!");
    }

    public void unenroll(Student student, Course course) {
        Enrollment enrollment = enrollments.stream()
            .filter(e -> e.getStudent().equals(student) && e.getCourse().equals(course))
            .findFirst()
            .orElse(null);

        if (enrollment != null) {
            enrollments.remove(enrollment);
            student.getEnrollments().remove(enrollment);
            student.getEnrolledCourses().remove(course);
            System.out.println("Unenrolled successfully from " + course.getTitle());
        } else {
            System.out.println("Enrollment not found!");
        }
    }

    public void assignGrade(Student student, Course course, Grade grade) {
        Enrollment enrollment = enrollments.stream()
            .filter(e -> e.getStudent().equals(student) && e.getCourse().equals(course))
            .findFirst()
            .orElse(null);

        if (enrollment != null) {
            enrollment.setGrade(grade);
            System.out.println("Grade assigned!");
        } else {
            System.out.println("Enrollment not found!");
        }
    }

    public void listEnrollments() {
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments yet.");
            return;
        }
        enrollments.forEach(System.out::println);
    }

    public double calculateGPA(Student student) {
        int totalCredits = 0;
        int weightedPoints = 0;

        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudent().equals(student) && enrollment.getGrade() != null) {
                int credits = enrollment.getCourse().getCredits();
                totalCredits += credits;
                weightedPoints += credits * enrollment.getGrade().getPoints();
            }
        }

        if (totalCredits == 0) {
            return 0.0;
        }
        return (double) weightedPoints / totalCredits;
    }

    public List<Enrollment> getAllEnrollments() {
        return new ArrayList<>(enrollments);
    }
}

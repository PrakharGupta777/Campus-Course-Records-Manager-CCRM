package edu.ccrm.service;

import edu.ccrm.domain.Course;
import java.util.*;
import java.util.stream.Collectors;

public class CourseService {
    private final Map<String, Course> coursesByCode = new HashMap<>();

    public void addCourse(Course c) {
        coursesByCode.put(c.getCode(), c);
    }

    public void listCourses() {
        if (coursesByCode.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }
        for (Course course : coursesByCode.values()) {
            System.out.println(course);
        }
    }

    public Optional<Course> findByCode(String code) {
        return Optional.ofNullable(coursesByCode.get(code));
    }

    public List<Course> searchByInstructor(String instructorName) {
        return coursesByCode.values().stream()
                .filter(course -> instructorName.equalsIgnoreCase(course.getInstructor()))
                .collect(Collectors.toList());
    }

    public List<Course> filterByDepartment(String department) {
        List<Course> result = new ArrayList<>();
        for (Course course : coursesByCode.values()) {
            try {
                Object dept = course.getClass().getMethod("getDepartment").invoke(course);
                if (department.equalsIgnoreCase(dept.toString())) {
                    result.add(course);
                }
            } catch (Exception ignored) {}
        }
        return result;
    }

    public List<Course> filterBySemester(String semester) {
        List<Course> result = new ArrayList<>();
        for (Course course : coursesByCode.values()) {
            try {
                Object sem = course.getClass().getMethod("getSemester").invoke(course);
                if (semester.equalsIgnoreCase(sem.toString())) {
                    result.add(course);
                }
            } catch (Exception ignored) {}
        }
        return result;
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(coursesByCode.values());
    }
}

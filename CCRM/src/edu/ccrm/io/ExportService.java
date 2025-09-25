package edu.ccrm.io;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ExportService {

    public void exportData(String fileName, List<Student> students, List<Course> courses, List<Enrollment> enrollments) throws IOException {
        Path exportPath = Paths.get("exports", fileName);
        Files.createDirectories(exportPath.getParent());

        StringBuilder output = new StringBuilder();

        output.append("=== Courses ===\n");
        for (Course course : courses) {
            output.append(course.getCode()).append(",")
                  .append(course.getTitle()).append(",")
                  .append(course.getCredits()).append(",")
                  .append(course.getInstructor()).append(",")
                  .append(course.isActive()).append("\n");
        }

        output.append("\n=== Students ===\n");
        for (Student student : students) {
            output.append(student.getId()).append(",")
                  .append(student.getFullName()).append(",")
                  .append(student.getRegNo()).append(",")
                  .append(student.isActive()).append("\n");
        }

        output.append("\n=== Enrollments ===\n");
        for (Enrollment enrollment : enrollments) {
            output.append(enrollment.getStudent().getId()).append(",")
                  .append(enrollment.getCourse().getCode()).append(",")
                  .append(enrollment.getGrade() != null ? enrollment.getGrade().toString() : "N/A")
                  .append("\n");
        }

        Files.write(exportPath, output.toString().getBytes());
        System.out.println("Data exported to " + exportPath.toAbsolutePath());
    }

    public void importStudentsFromCSV(Path filePath, List<Student> students) throws IOException {
        if (!Files.exists(filePath)) {
            System.out.println("CSV file not found: " + filePath);
            return;
        }
        List<String> csvLines = Files.readAllLines(filePath);
        for (String line : csvLines) {
            String[] fields = line.split(",");
            if (fields.length >= 3) {
                String id = fields[0].trim();
                String fullName = fields[1].trim();
                String regNo = fields[2].trim();
                students.add(new Student(id, fullName, id + "@school.com", regNo));
            }
        }
        System.out.println("Imported " + students.size() + " students from CSV.");
    }

    public void importCoursesFromCSV(Path filePath, List<Course> courses) throws IOException {
        if (!Files.exists(filePath)) {
            System.out.println("CSV file not found: " + filePath);
            return;
        }
        List<String> csvLines = Files.readAllLines(filePath);
        for (String line : csvLines) {
            String[] fields = line.split(",");
            if (fields.length >= 4) {
                String code = fields[0].trim();
                String title = fields[1].trim();
                int credits = Integer.parseInt(fields[2].trim());
                String instructor = fields[3].trim();
                String department = fields.length >= 5 ? fields[4].trim() : "General";
                String semester = fields.length >= 6 ? fields[5].trim() : "Fall";
                courses.add(new Course(code, title, credits, instructor, department, semester));
            }
        }
        System.out.println("Imported " + courses.size() + " courses from CSV.");
    }
}

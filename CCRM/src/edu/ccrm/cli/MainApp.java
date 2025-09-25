package edu.ccrm.cli;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Grade;
import edu.ccrm.domain.Student;
import edu.ccrm.io.BackupService;
import edu.ccrm.io.ExportService;
import edu.ccrm.io.ImportService;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.EnrollmentService;
import edu.ccrm.service.StudentService;
import java.util.Scanner;

public class MainApp {

    private static final Scanner input = new Scanner(System.in);
    private static final StudentService stuService = new StudentService();
    private static final CourseService crsService = new CourseService();
    private static final EnrollmentService enrService = new EnrollmentService();
    private static final ExportService expService = new ExportService();
    private static final BackupService bkpService = new BackupService();
    private static final ImportService impService = new ImportService();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            String option = input.nextLine().trim();

            if ("17".equals(option)) {
                System.out.println("Program terminated.");
                input.close();
                break;
            }
            if ("1".equals(option)) addStudent();
            else if ("2".equals(option)) stuService.listStudents();
            else if ("3".equals(option)) deactivateStudent();
            else if ("4".equals(option)) addCourse();
            else if ("5".equals(option)) crsService.listCourses();
            else if ("6".equals(option)) deactivateCourse();
            else if ("7".equals(option)) enrollStudent();
            else if ("8".equals(option)) unenrollStudent();
            else if ("9".equals(option)) enrService.listEnrollments();
            else if ("10".equals(option)) assignGrade();
            else if ("11".equals(option)) showGpa();
            else if ("12".equals(option)) printTranscript();
            else if ("13".equals(option)) searchCourses();
            else if ("14".equals(option)) importData();
            else if ("15".equals(option)) exportData();
            else if ("16".equals(option)) backupData();
            else System.out.println("Unknown selection. Try again.");
        }
    }

    private static void showMenu() {
        System.out.println("\n--- Welcome to the CCRM System ---");
        System.out.println("Hi there! What would you like to do today?");
        System.out.println("1. Register a new student (add someone to our community)");
        System.out.println("2. See all students currently registered");
        System.out.println("3. Remove or deactivate a student (if someone leaves)");
        System.out.println("4. Add a new course to the catalog");
        System.out.println("5. Browse all available courses");
        System.out.println("6. Remove or deactivate a course");
        System.out.println("7. Enroll a student in a course");
        System.out.println("8. Unenroll a student from a course");
        System.out.println("9. View all current enrollments");
        System.out.println("10. Assign grades to students");
        System.out.println("11. Check a student's GPA");
        System.out.println("12. Print a student's transcript");
        System.out.println("13. Search for courses by instructor name");
        System.out.println("14. Import data from CSV files (students, courses, enrollments)");
        System.out.println("15. Export all data to a file for safekeeping");
        System.out.println("16. Create a backup of your data");
        System.out.println("17. Exit the application");
        System.out.print("Please enter your choice (1-17): ");
    }

    private static void addStudent() {
        System.out.print("Student ID: "); String id = input.nextLine();
        System.out.print("Full Name: "); String name = input.nextLine();
        System.out.print("Email Address: "); String email = input.nextLine();
        System.out.print("Registration Number: "); String regNo = input.nextLine();
        Student s = new Student(id, name, email, regNo);
        stuService.addStudent(s);
        System.out.println("Student registered.");
    }

    private static void deactivateStudent() {
        System.out.print("Student ID to remove: ");
        String sid = input.nextLine();
        stuService.findById(sid).ifPresentOrElse(
            Student::deactivate,
            () -> System.out.println("No such student found.")
        );
    }

    private static void addCourse() {
        try {
            System.out.print("Course Code: "); String code = input.nextLine();
            System.out.print("Course Title: "); String title = input.nextLine();
            System.out.print("Credit Value: "); int credits = Integer.parseInt(input.nextLine());
            System.out.print("Instructor Name: "); String instructor = input.nextLine();
            System.out.print("Department Name: "); String dept = input.nextLine();
            System.out.print("Semester: "); String sem = input.nextLine();
            Course c = new Course(code, title, credits, instructor, dept, sem);
            crsService.addCourse(c);
            System.out.println("Course registered.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid credits input.");
        }
    }

    private static void deactivateCourse() {
        System.out.print("Course Code to remove: ");
        String code = input.nextLine();
        crsService.findByCode(code).ifPresentOrElse(
            Course::deactivate,
            () -> System.out.println("No such course found.")
        );
    }

    private static void enrollStudent() {
        System.out.print("Student ID: "); String sid = input.nextLine();
        System.out.print("Course Code: "); String code = input.nextLine();
        stuService.findById(sid).ifPresentOrElse(
            student -> crsService.findByCode(code).ifPresentOrElse(
                course -> enrService.enroll(student, course),
                () -> System.out.println("Course not found.")
            ),
            () -> System.out.println("Student not found.")
        );
    }

    private static void unenrollStudent() {
        System.out.print("Student ID: "); String sid = input.nextLine();
        System.out.print("Course Code: "); String code = input.nextLine();
        stuService.findById(sid).ifPresentOrElse(
            student -> crsService.findByCode(code).ifPresentOrElse(
                course -> enrService.unenroll(student, course),
                () -> System.out.println("Course not found.")
            ),
            () -> System.out.println("Student not found.")
        );
    }

    private static void assignGrade() {
        try {
            System.out.print("Student ID: "); String sid = input.nextLine();
            System.out.print("Course Code: "); String code = input.nextLine();
            System.out.print("Grade (S/A/B/C/D/F): "); String g = input.nextLine().toUpperCase();
            stuService.findById(sid).ifPresentOrElse(
                student -> crsService.findByCode(code).ifPresentOrElse(
                    course -> enrService.assignGrade(student, course, Grade.valueOf(g)),
                    () -> System.out.println("Course not found.")
                ),
                () -> System.out.println("Student not found.")
            );
        } catch (IllegalArgumentException e) {
            System.out.println("Grade input error.");
        }
    }

    private static void showGpa() {
        System.out.print("Student ID: "); String sid = input.nextLine();
        stuService.findById(sid).ifPresentOrElse(
            student -> System.out.println("Current GPA: " + enrService.calculateGPA(student)),
            () -> System.out.println("Student not found.")
        );
    }

    private static void printTranscript() {
        System.out.print("Student ID: "); String sid = input.nextLine();
        stuService.printTranscript(sid);
    }

    private static void searchCourses() {
        System.out.print("Instructor to search: "); String instr = input.nextLine();
        var filtered = crsService.searchByInstructor(instr);
        System.out.println("Matching courses:");
        filtered.forEach(System.out::println);
    }

    private static void importData() {
        try {
            System.out.print("Students CSV path: "); String studentFile = input.nextLine();
            impService.importStudents(studentFile, stuService);
            System.out.print("Courses CSV path: "); String courseFile = input.nextLine();
            impService.importCourses(courseFile, crsService);
            System.out.print("Enrollments CSV path: "); String enrollFile = input.nextLine();
            impService.importEnrollments(enrollFile, stuService, crsService, enrService);
            System.out.println("Import completed.");
        } catch (Exception e) {
            System.out.println("Import error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void exportData() {
        try {
            expService.exportData(
                "export.txt",
                stuService.getAllStudents(),
                crsService.getAllCourses(),
                enrService.getAllEnrollments()
            );
            System.out.println("Exported to export.txt");
        } catch (Exception e) {
            System.out.println("Export error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void backupData() {
        try {
            bkpService.backup("exports");
            System.out.println("Backup successful.");
        } catch (Exception e) {
            System.out.println("Backup error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

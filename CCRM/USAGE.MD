## Usage
This guide will walk you through how to use the Campus Course & Records Manager (CCRM) system. The application is a command-line tool for managing students, courses, and enrollments.

**Getting Started**
Launch the Application: Run the MainApp class from your compiled Java project.

View the Menu: Once started, you'll see a menu with a list of all available actions.

**--- Welcome to the CCRM System ---**
Hi there! What would you like to do today?
1. Register a new student (add someone to our community)
2. See all students currently registered
...
17. Exit the application
Please enter your choice (1-17):
Select an Action: Enter the number corresponding to the action you want to perform and press Enter. The application will then prompt you for any required information.

**Available Commands**
Here is a brief overview of what each menu option does:

**Student Management**
1. Register a new student: Adds a new student to the system. You will be asked for an ID, full name, email, and registration number.
2. See all students: Displays a list of all currently registered students.
3. Remove or deactivate a student: Marks a student as inactive in the system using their ID.
11. Check a student's GPA: Calculates and displays the Grade Point Average for a specific student.
12. Print a student's transcript: Shows a detailed academic record for a specific student.

**Course Management**
4. Add a new course: Adds a new course to the catalog. You will need to provide a course code, title, credit value, instructor, department, and semester.
5. Browse all available courses: Displays a list of all available courses.
6. Remove or deactivate a course: Marks a course as inactive using its course code.
13. Search for courses by instructor name: Finds and lists all courses taught by a specific instructor.

*Enrollment & Grading**
7. Enroll a student in a course: Enrolls an existing student into an available course.
8. Unenroll a student from a course: Removes a student's enrollment from a course.
9. View all current enrollments: Shows a complete list of which students are enrolled in which courses.
10. Assign grades to students: Assigns a letter grade (S, A, B, C, D, F) to a student for a specific course.

**Data Management**
14. Import data: Bulk imports students, courses, and enrollments from specified CSV files.
15. Export all data: Saves all current student, course, and enrollment data to a single export.txt file.
16. Create a backup: Creates a backup of your data in the exports directory.
17. Exit: Closes the application.
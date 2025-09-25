# Campus Course & Records Manager (CCRM)

Welcome to the Campus Course & Records Manager (CCRM)!  
This is a user-friendly, console-based Java application designed to help educational institutions manage student and course information with ease.

---

## 📝 Project Overview & How to Run

CCRM provides a simple command-line interface (CLI) for administrators to handle essential academic record-keeping tasks. With CCRM, you can:

- Manage students, courses, enrollments, and grades
- Import/export data and create backups
- Generate transcripts and calculate GPAs

### Prerequisites

- **Java Development Kit (JDK):** Version 17 or later recommended

### Steps to Run

1. **Navigate to the Source Directory**  
    Open a terminal or command prompt and go to the `src` directory.

2. **Compile the Project**  
    Compile all Java files (output to a `bin` directory):

    ```bash
    javac -d ../bin edu/ccrm/cli/MainApp_Refactored_Methods.java edu/ccrm/domain/*.java edu/ccrm/io/*.java edu/ccrm/service/*.java
    ```
    > *Note: Adjust the command if you have files in the `util` package.*

3. **Run the Application**  
    Move to the `bin` directory and start the main class:

    ```bash
    cd ../bin
    java edu.ccrm.cli.MainApp_Refactored_Methods
    ```

    The CCRM menu will appear in your console—you're ready to go!

---

## 🔄 Evolution of Java (Short Bullets)

- **1995:** Java 1.0 released (write once, run anywhere)
- **Java 2 (1998):** Introduced SE, EE, ME editions
- **Java 5 (2004):** Generics, annotations, enhanced for-loop
- **Java 8 (2014):** Lambdas, streams, default methods
- **Java 9+:** Modular system, local variable type inference, records, pattern matching

---

## 🆚 Java ME vs SE vs EE

| Edition | Target | Use Case | Example Features |
|---------|--------|----------|-----------------|
| **ME**  | Mobile/Embedded | Devices, IoT | Lightweight VM, limited APIs |
| **SE**  | Desktop/Server | General-purpose | Core libraries, GUI, networking |
| **EE**  | Enterprise | Web, distributed | Servlets, EJB, JMS, JPA |

---

## ☕ JDK, JRE, JVM Explained

- **JDK (Java Development Kit):** Tools for developing and compiling Java apps (includes JRE + compilers/debuggers)
- **JRE (Java Runtime Environment):** Libraries and JVM to run Java apps (no compilers)
- **JVM (Java Virtual Machine):** Executes Java bytecode on any platform

---

## 🪟 Windows Install Steps & Eclipse Setup

### Install JDK on Windows

1. Download JDK from [Oracle](https://www.oracle.com/java/technologies/downloads/).
2. Run the installer and follow prompts.
3. Set `JAVA_HOME` and update `PATH` environment variable.

### Eclipse Setup

1. Download Eclipse IDE from [eclipse.org](https://www.eclipse.org/downloads/).
2. Extract and run `eclipse.exe`.
3. Select a workspace directory.
4. Import the CCRM project:  
    `File` → `Import` → `Existing Projects into Workspace`

---

## 🗺️ Mapping Table: Syllabus Topic → File/Class/Method

| Syllabus Topic         | File/Class/Method                          |
|------------------------|--------------------------------------------|
| OOP Concepts           | `domain/Student.java`, `domain/Course.java`|
| File I/O               | `io/DataImporter.java`, `io/DataExporter.java`|
| Collections            | `service/StudentService.java`              |
| Exception Handling     | `service/EnrollmentService.java`           |
| Assertions             | `util/AssertionsDemo.java`                 |
| CLI Interaction        | `cli/MainApp_Refactored_Methods.java`      |

---

## 📝 Notes on Enabling Assertions

- **Enable assertions at runtime:**  
  Add `-ea` flag when running Java:

  ```bash
  java -ea edu.ccrm.cli.MainApp_Refactored_Methods
  ```

- **Sample assertion usage:**
  ```java
  assert student != null : "Student object should not be null";
  ```

---

## 🛠️ Technologies Used

- **Language:** Java  
- **Platform:** Java SE (Standard Edition)


---

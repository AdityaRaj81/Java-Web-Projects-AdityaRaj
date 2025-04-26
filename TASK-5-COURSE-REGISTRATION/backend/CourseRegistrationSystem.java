// Task - 5

import java.util.*;

class Course {
    String code;
    String title;
    String description;
    int capacity;
    String schedule;
    List<String> registeredStudents;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = new ArrayList<>();
    }

    public boolean isAvailable() {
        return registeredStudents.size() < capacity;
    }

    public void displayCourse() {
        System.out.println("Course Code: " + code);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Schedule: " + schedule);
        System.out.println("Capacity: " + registeredStudents.size() + "/" + capacity);
        System.out.println("Available Slots: " + (capacity - registeredStudents.size()));
        System.out.println("-----------------------------------");
    }
}

class Student {
    String id;
    String name;
    List<String> registeredCourses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public void displayCourses() {
        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered yet.");
        } else {
            System.out.println("Registered Courses:");
            for (String courseCode : registeredCourses) {
                System.out.println("- " + courseCode);
            }
        }
    }
}

public class CourseRegistrationSystem {
    static Map<String, Course> courseMap = new HashMap<>();
    static Map<String, Student> studentMap = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        seedCourses();
        System.out.print("Enter your Student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter your Name: ");
        String name = scanner.nextLine();

        Student student = studentMap.getOrDefault(id, new Student(id, name));
        studentMap.put(id, student);

        int choice;
        do {
            System.out.println("\n📚 STUDENT COURSE REGISTRATION MENU 📚");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt(); scanner.nextLine();

            switch (choice) {
                case 1 -> viewCourses();
                case 2 -> registerCourse(student);
                case 3 -> dropCourse(student);
                case 4 -> student.displayCourses();
                case 5 -> System.out.println("Thank you! Goodbye!");
                default -> System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 5);
    }

    private static void seedCourses() {
        courseMap.put("CS101", new Course("CS101", "Introduction to Programming", "Learn Java basics", 3, "Mon/Wed 10-11 AM"));
        courseMap.put("CS102", new Course("CS102", "Data Structures", "Learn arrays, stacks, queues", 2, "Tue/Thu 11-12 AM"));
        courseMap.put("CS103", new Course("CS103", "Web Development", "Learn HTML, CSS, JS", 2, "Fri 2-4 PM"));
    }

    private static void viewCourses() {
        System.out.println("\n📘 AVAILABLE COURSES:");
        for (Course course : courseMap.values()) {
            course.displayCourse();
        }
    }

    private static void registerCourse(Student student) {
        System.out.print("Enter Course Code to Register: ");
        String courseCode = scanner.nextLine().toUpperCase();
        Course course = courseMap.get(courseCode);

        if (course == null) {
            System.out.println("❌ Course not found.");
            return;
        }

        if (!course.isAvailable()) {
            System.out.println("❌ Course is full.");
            return;
        }

        if (student.registeredCourses.contains(courseCode)) {
            System.out.println("⚠️ Already registered for this course.");
            return;
        }

        course.registeredStudents.add(student.id);
        student.registeredCourses.add(courseCode);
        System.out.println("✅ Registered for " + courseCode + " successfully!");
    }

    private static void dropCourse(Student student) {
        System.out.print("Enter Course Code to Drop: ");
        String courseCode = scanner.nextLine().toUpperCase();
        Course course = courseMap.get(courseCode);

        if (course == null || !student.registeredCourses.contains(courseCode)) {
            System.out.println("❌ You are not registered in this course.");
            return;
        }

        course.registeredStudents.remove(student.id);
        student.registeredCourses.remove(courseCode);
        System.out.println("✅ Dropped " + courseCode + " successfully.");
    }
}

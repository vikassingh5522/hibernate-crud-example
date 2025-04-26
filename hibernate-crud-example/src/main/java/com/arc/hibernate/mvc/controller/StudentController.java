package com.arc.hibernate.mvc.controller;

import com.arc.hibernate.mvc.dao.*;
import com.arc.hibernate.mvc.model.Student;

import java.util.List;
import java.util.Scanner;

public class StudentController {
    private final StudentDAO dao = new StudentDAOImpl();
    private final Scanner sc = new Scanner(System.in);

    public void startApp() {
        int choice;
        do {
            System.out.println("\n--- Student Management Menu ---");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. View Student by ID");
            System.out.println("5. View All Students");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 : addStudent(); break;
                case 2 : updateStudent();break;
                case 3 : deleteStudent();break;
                case 4 : viewStudent();break;
                case 5 : viewAllStudents();break;
                case 0 : System.out.println("Exiting...");break;
                default : System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    private void addStudent() {
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();

        dao.save(new Student(name, email, phone));
        System.out.println("Student added.");
    }

    private void updateStudent() {
        System.out.print("Enter ID to update: ");
        int id = sc.nextInt(); sc.nextLine();
        Student student = dao.getById(id);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.print("New Name (" + student.getName() + "): ");
        student.setName(sc.nextLine());
        System.out.print("New Email (" + student.getEmail() + "): ");
        student.setEmail(sc.nextLine());
        System.out.print("New Phone (" + student.getPhone() + "): ");
        student.setPhone(sc.nextLine());

        dao.update(student);
        System.out.println("Student updated.");
    }

    private void deleteStudent() {
        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt(); sc.nextLine();
        dao.delete(id);
        System.out.println("Student deleted.");
    }

    private void viewStudent() {
        System.out.print("Enter ID to view: ");
        int id = sc.nextInt(); sc.nextLine();
        Student student = dao.getById(id);
        if (student != null)
            System.out.println(student);
        else
            System.out.println("Student not found.");
    }

    private void viewAllStudents() {
        List<Student> list = dao.getAll();
        list.forEach(System.out::println);
    }
}

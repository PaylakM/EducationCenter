package educationCenter.storage;

import educationCenter.exception.StudentNotFoundException;
import educationCenter.model.Student;

import java.security.PublicKey;

public class StudentStorage {

    private Student[] students;
    private int size;

    public StudentStorage(int capacity) {
        students = new Student[capacity];
    }

    public StudentStorage() {
        students = new Student[16];
    }

    public void add(Student student) {
        if (size == students.length) {
            extend();
        }
        students[size++] = student;
    }

    private void extend() {
        Student[] tmp = new Student[students.length + 16];
        System.arraycopy(students, 0, tmp, 0, students.length);
        students = tmp;
    }

    public void printAll() {
        for (int i = 0; i < size; i++) {
            System.out.println(students[i]);
        }
    }

    public void searchStudent(String studentName) {
        boolean isFind = false;
        for (int i = 0; i < size; i++) {
            if (students[i].getName().contains(studentName)) {
                isFind = true;
                System.out.println(students[i]);
            }
        }
        if (!isFind) {
            System.out.println("nman girq chka");
        }
    }

    public void searchStudentbyLessonName(String name) {
        boolean isFind = false;
        for (int i = 0; i < size; i++) {
            if (students[i].getLesson().getName().equals(name)) {
                isFind = true;
                System.out.println(students[i]);
            }
        }
        if (!isFind) {
            System.out.println("chkaaaaa");
        }
    }

    public Student findStudentEmail(String email) {
        if (email != null) {
            for (int i = 0; i < size; i++) {
                if (students[i].getEmail().equals(email)) {
                    return students[i];
                }
            }
        }
        throw new StudentNotFoundException("nman usanox chka [" + email + "]");
    }

    public Student getStudentEmail(String email) {
        for (int i = 0; i < size; i++) {
            if(students[i].getEmail().equals(email))
                return students[i];
        }
        return null;
    }
}

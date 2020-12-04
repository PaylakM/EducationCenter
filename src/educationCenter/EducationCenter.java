package educationCenter;

import educationCenter.model.Lesson;
import educationCenter.model.Student;
import educationCenter.storage.LessonStorage;
import educationCenter.storage.StudentStorage;
import jdk.swing.interop.SwingInterOpUtils;

import java.util.Scanner;

public class EducationCenter {

    private static LessonStorage lessonStorage = new LessonStorage();
    private static StudentStorage studentStorage = new StudentStorage();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        boolean isRun = true;

        while (isRun) {
            System.out.println("EXIT = 0");
            System.out.println("ADD_STUDENT = 1");
            System.out.println("ADD_LESSON = 2");
            System.out.println("PRINT_STUDENTS = 3");
            System.out.println("PRINT_LESSONS = 4");
            String commands = scanner.nextLine();
            switch (commands) {
                case "0":
                    isRun = false;
                    break;
                case "1":
                    addStudent();
                    break;
                case "2":
                    addLesson();
                    break;
                case "3":
                    printAllStudend();
                    break;
                case "4":
                    printAllLesson();
                    break;
                default:
                    System.out.println("nman hraman chka");

            }

        }

    }

    private static void printAllLesson() {
        lessonStorage.printAll();
    }

    private static void printAllStudend() {
        studentStorage.PrintAll();
    }

    private static void addLesson() {
        System.out.println("nermuceq Lessoni tvyalner@ ------name,duration,price,lecturenAme");
        String lessonInfo = scanner.nextLine();
        String[] splitLesson = lessonInfo.split(",");
        Lesson lesson=new Lesson();
        lesson.setName(splitLesson[0]);
        lesson.setDuration(Integer.parseInt(splitLesson[1]));
        lesson.setPrice(Double.parseDouble(splitLesson[2]));
        lesson.setLecturerName(splitLesson[3]);
        lessonStorage.Add(lesson);
    }

    private static void addStudent() {
        System.out.println("please write lesson name");
        String lessonName = scanner.nextLine();
        Lesson lessonByName = lessonStorage.getLessonByName(lessonName);
        if (lessonByName != null) {
            System.out.println("write student info------ name,surname,phone,email");
            String studenInfo = scanner.nextLine();
            String[] splitStudent = studenInfo.split(",");
            Student student=new Student();
            student.setName(splitStudent[0]);
            student.setSurname(splitStudent[1]);
            student.setPhone(splitStudent[2]);
            student.setEmail(splitStudent[3]);
            student.setLesson(lessonByName);
            studentStorage.Add(student);
            studentStorage.PrintAll();
        } else {
            System.out.println("nman Lesson chka");
        }
    }





}

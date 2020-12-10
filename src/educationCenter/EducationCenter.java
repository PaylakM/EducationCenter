package educationCenter;

import educationCenter.model.Post;
import educationCenter.model.Student;
import educationCenter.storage.LessonStorage;
import educationCenter.storage.StudentStorage;

import java.util.Scanner;

public class EducationCenter implements Commands {

    private static LessonStorage lessonStorage = new LessonStorage();
    private static StudentStorage studentStorage = new StudentStorage();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean isRun = true;
        while (isRun) {
            numberCommands();
            String commands = scanner.nextLine();
            switch (commands) {
                case EXIT:
                    isRun = false;
                    break;
                case ADD_STUDENT:
                    addStudent();
                    break;
                case ADD_LESSON:
                    addLesson();
                    break;
                case PRINT_STUDENTS:
                    printAllStudent();
                    break;
                case PRINT_LESSONS:
                    printAllLesson();
                    break;
                case SEARCH_STUDENT_BY_NAME:
                    searchStudenByName();
                    break;
                case SEARCH_LESSON_BY_NAME:
                    searchLessonByName();
                    break;
                case SEARCH_STUDENTS_BY_LESSON_NAME:
                    searchStudentByLessonName();
                    break;
                case CHANGE_STUDENT_LESSON:
                    changeLessonByStudentEmail();
                    break;
                case CHANGE_STUDENT_PHONE:
                    changeStudentByPhone();
                    break;
                default:
                    System.out.println("nman hraman chka");
            }

        }

    }

    private static void changeStudentByPhone() {
        System.out.println("greq popoxvaxi Studeni emaile");
        String getStudentEmail = scanner.nextLine();
        Student studentEmail = studentStorage.getStudentEmail(getStudentEmail);
        if (studentEmail == null) {
            System.out.println("nman emailov student chka");
        } else {
            System.out.println("nermuceq studenti nor tvyalner@------name,surname,phone,email");
            String studentInfo = scanner.nextLine();
            String[] splitStudentInfo = studentInfo.split(",");
            studentEmail.setName(splitStudentInfo[0]);
            studentEmail.setSurname(splitStudentInfo[1]);
            studentEmail.setPhone(splitStudentInfo[2]);
            studentEmail.setEmail(splitStudentInfo[3]);
            System.out.println(studentInfo);
        }
    }

    private static void changeLessonByStudentEmail() {
        System.out.println("greq Lessoni tvyalnere");
        String studentEmail = scanner.nextLine();
        Student backEmail = studentStorage.findStudentEmail(studentEmail);
        System.out.println("nermuceq------name,duration,price,lecturename");
        String newLessonInfo = scanner.nextLine();
        String[] splitLessonName = newLessonInfo.split(",");
        backEmail.getLesson().setName(splitLessonName[0]);
        backEmail.getLesson().setDuration(Integer.parseInt(splitLessonName[1]));
        backEmail.getLesson().setPrice(Double.parseDouble(splitLessonName[2]));
        backEmail.getLesson().setLecturerName(splitLessonName[3]);
        System.out.println(backEmail);
    }

    private static void searchStudentByLessonName() {
        System.out.println("nermuceq studenti lessone");
        String studenNameByLesson = scanner.nextLine();
        studentStorage.searchStudentbyLessonName(studenNameByLesson);

    }

    private static void searchLessonByName() {
        System.out.println("nermuceq Lessone pntrelu hamar");
        String lessonName = scanner.nextLine();
        lessonStorage.searchLessonByName(lessonName);
    }

    private static void numberCommands() {
        System.out.println("EXIT " + EXIT);
        System.out.println("ADD_STUDENT =" + ADD_STUDENT);
        System.out.println("ADD_LESSON =" + ADD_LESSON);
        System.out.println("PRINT_STUDENTS =" + PRINT_STUDENTS);
        System.out.println("PRINT_LESSONS =" + PRINT_LESSONS);
        System.out.println("SEARCH_STUDENT_BY_NAME =" + SEARCH_STUDENT_BY_NAME);
        System.out.println("SEARCH_LESSON_BY_NAME =" + SEARCH_LESSON_BY_NAME);
        System.out.println("SEARCH_STUDENTS_BY_LESSON_NAME =" + SEARCH_STUDENTS_BY_LESSON_NAME);
        System.out.println("CHANGE_STUDENT_LESSON =" + CHANGE_STUDENT_LESSON);
        System.out.println("CHANGE_STUDENT_PHONE =" + CHANGE_STUDENT_PHONE);
    }

    private static void searchStudenByName() {
        System.out.println("Input serach student name");
        String studentName = scanner.nextLine();
        studentStorage.searchStudent(studentName);
    }

    private static void printAllLesson() {
        lessonStorage.printAll();
    }

    private static void printAllStudent() {
        studentStorage.printAll();
    }

    private static void addLesson() {
        System.out.println("nermuceq Lessoni tvyalner@ ------name,duration,price,lecturenAme");
        try {
            String lessonInfo = scanner.nextLine();
            String[] splitLesson = lessonInfo.split(",");
            Post lesson = new Post();
            lesson.setName(splitLesson[0]);
            lesson.setDuration(Integer.parseInt(splitLesson[1]));
            lesson.setPrice(Double.parseDouble(splitLesson[2]));
            lesson.setLecturerName(splitLesson[3]);
            lessonStorage.add(lesson);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void addStudent() {
        System.out.println("please write lesson name");
        try {
            String lessonName = scanner.nextLine();
            Post lessonByName = lessonStorage.getLessonByName(lessonName);
            if (lessonByName != null) {
                System.out.println("write student info------ name,surname,phone,email");
                String studenInfo = scanner.nextLine();
                String[] splitStudent = studenInfo.split(",");
                Student student = new Student();
                student.setName(splitStudent[0]);
                student.setSurname(splitStudent[1]);
                student.setPhone(splitStudent[2]);
                student.setEmail(splitStudent[3]);
                student.setLesson(lessonByName);
                studentStorage.add(student);
                studentStorage.printAll();
            } else {
                System.out.println("nman Lesson chka");
            }
        }catch (ArrayIndexOutOfBoundsException | NumberFormatException e){
            System.err.println(e.getMessage());
        }
    }


}

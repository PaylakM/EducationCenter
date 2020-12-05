package educationCenter;

import educationCenter.model.Lesson;
import educationCenter.model.Student;
import educationCenter.storage.LessonStorage;
import educationCenter.storage.StudentStorage;

import java.util.Scanner;

public class EducationCenter {

    private static LessonStorage lessonStorage = new LessonStorage();
    private static StudentStorage studentStorage = new StudentStorage();
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        boolean isRun = true;
        while (isRun) {
            numberCommands();
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
                case "5":
                    searchStudenByName();
                    break;
                case "6":
                    searchLessonByName();
                    break;
                case "7":
                    searchStudentByLessonName();
                    break;
                case "8":
                    changeLessonByStudentEmail();
                    break;
                case "9":
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
        if(studentEmail==null){
            System.out.println("nman emailov student chka");
        }
        else {
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
        if (backEmail == null) {
            System.out.println("nman usanox chka");
        } else {
            System.out.println("nermuceq------name,duration,price,lecturename");
            String newLessonInfo = scanner.nextLine();
            String[] splitLessonName = newLessonInfo.split(",");
            backEmail.getLesson().setName(splitLessonName[0]);
            backEmail.getLesson().setDuration(Integer.parseInt(splitLessonName[1]));
            backEmail.getLesson().setPrice(Double.parseDouble(splitLessonName[2]));
            backEmail.getLesson().setLecturerName(splitLessonName[3]);
            System.out.println(backEmail);

        }
    }

    private static void searchStudentByLessonName() {
        String studenNameByLesson = scanner.nextLine();
        studentStorage.searchStudentbyLessonName(studenNameByLesson);

    }

    private static void searchLessonByName() {
        String lessonName = scanner.nextLine();
        lessonStorage.searchLessonByName(lessonName);
    }

    private static void numberCommands() {
        System.out.println("EXIT = 0");
        System.out.println("ADD_STUDENT = 1");
        System.out.println("ADD_LESSON = 2");
        System.out.println("PRINT_STUDENTS = 3");
        System.out.println("PRINT_LESSONS = 4");
        System.out.println("SEARCH_STUDENT_BY_NAME=5");
        System.out.println("SEARCH_LESSON_BY_NAME=6");
        System.out.println("SEARCH_STUDENTS_BY_LESSON_NAME=7");
        System.out.println("CHANGE_STUDENT_LESSON=8");
        System.out.println("CHANGE_STUDENT_PHONE=9");
    }

    private static void searchStudenByName() {
        String studentName = scanner.nextLine();
        studentStorage.searchStudent(studentName);
    }

    private static void printAllLesson() {
        lessonStorage.printAll();
    }

    private static void printAllStudend() {
        studentStorage.printAll();
    }

    private static void addLesson() {
        System.out.println("nermuceq Lessoni tvyalner@ ------name,duration,price,lecturenAme");
        String lessonInfo = scanner.nextLine();
        String[] splitLesson = lessonInfo.split(",");
        Lesson lesson = new Lesson();
        lesson.setName(splitLesson[0]);
        lesson.setDuration(Integer.parseInt(splitLesson[1]));
        lesson.setPrice(Double.parseDouble(splitLesson[2]));
        lesson.setLecturerName(splitLesson[3]);
        lessonStorage.add(lesson);
    }

    private static void addStudent() {
        System.out.println("please write lesson name");
        String lessonName = scanner.nextLine();
        Lesson lessonByName = lessonStorage.getLessonByName(lessonName);
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
    }
}

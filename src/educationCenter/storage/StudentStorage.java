package educationCenter.storage;

import educationCenter.model.Student;

public class StudentStorage {

    private Student[] students;
    private int size;

    public StudentStorage(int capacity) {
        students = new Student[capacity];
    }

    public StudentStorage() {
        students = new Student[16];
    }

    public void Add(Student student){
        if(size==students.length){
            extend();
        }
        students[size++]=student;
    }

    private void extend() {
        Student[]tmp=new Student[students.length+16];
        System.arraycopy(students,0,tmp,0,students.length);
        students=tmp;
    }

    public void PrintAll(){
        for (int i = 0; i < size; i++) {
            System.out.println(students[i]);
        }
    }


}

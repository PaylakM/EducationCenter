package educationCenter.storage;

import educationCenter.model.Lesson;

public class LessonStorage {

    private Lesson[] lessons;
    private int size;

    public LessonStorage(int capacity) {
        lessons = new Lesson[capacity];
    }

    public LessonStorage() {
        lessons = new Lesson[16];
    }

    public void Add(Lesson lesson) {
        if (size == lessons.length) {
            extend();
        }
        lessons[size++] = lesson;
    }

    private void extend() {
        Lesson[] tmp = new Lesson[lessons.length + 16];
        System.arraycopy(lessons, 0, tmp, 0, lessons.length);
        lessons = tmp;
    }

    public void printAll() {
        for (int i = 0; i < size; i++) {
            System.out.println(lessons[i]);
        }
    }

    public Lesson getLessonByName(String name) {
        for (int i = 0; i < size; i++) {
            if (lessons[i].getName().equals(name)) {
                return lessons[i];
            }
        }
        return null;
    }

}


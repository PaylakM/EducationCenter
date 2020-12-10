package educationCenter.storage;

import educationCenter.model.Post;

public class LessonStorage {

    private Post[] lessons;
    private int size;

    public LessonStorage(int capacity) {
        lessons = new Post[capacity];
    }

    public LessonStorage() {
        lessons = new Post[16];
    }

    public void add(Post lesson) {
        if (size == lessons.length) {
            extend();
        }
        lessons[size++] = lesson;
    }

    private void extend() {
        Post[] tmp = new Post[lessons.length + 16];
        System.arraycopy(lessons, 0, tmp, 0, lessons.length);
        lessons = tmp;
    }

    public void printAll() {
        for (int i = 0; i < size; i++) {
            System.out.println(lessons[i]);
        }
    }

    public Post getLessonByName(String name) {
        for (int i = 0; i < size; i++) {
            if (lessons[i].getName().equals(name)) {
                return lessons[i];
            }
        }
        return null;
    }

    public void searchLessonByName(String name) {
        boolean isFind = false;
        for (int i = 0; i < size; i++) {
            if (lessons[i].getName().contains(name))
                isFind = true;
            System.out.println(lessons[i]);
        }
        if (!isFind) {
            System.out.println("nman Lesson chka");
        }
    }
}


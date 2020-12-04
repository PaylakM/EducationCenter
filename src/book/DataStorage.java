package book;

public class DataStorage {
    private Book[] books = new Book[16];
    private int size = 0;

    public void add(Book value) {
        if (size == books.length) {
            extend();
        }
        books[size++] = value;
    }

    private void extend() {
        Book[] tmp = new Book[books.length + 10];
        System.arraycopy(books, 0, tmp, 0, books.length);
        books = tmp;
    }

    public void printAll() {
        for (int i = 0; i < size; i++) {
            System.out.println(books);
        }
    }

    public Book get(int index) {
        return books[index];
    }

    public Book ReturnBookByTitle(String title){
        for (int i = 0; i < size; i++) {
            if(books[i].getTitle().equals(title)){
                return books[i];
            }
        }
        return null;
    }

}

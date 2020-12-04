package book;

import java.sql.SQLOutput;
import java.util.Scanner;

public class BookTest {

    private static DataStorage dataStorage = new DataStorage();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean isRun = true;

        while (isRun) {
           showCommands();
            String command = scanner.nextLine();
            switch (command) {
                case "0":
                    isRun = false;
                    break;
                case "1":
                    addBook();
                    break;
                case "2":
                   searchByTitle();
                    break;
                default:
                    System.out.println("wrong comman");
            }
            System.out.println("thanks for use our program");

        }

    }

    private static void showCommands() {
        System.out.println("please input 0 for exit");
        System.out.println("please input 1 for add book");
        System.out.println("please input 2 for search book by title");
    }


    private static void addBook() {
        System.out.println("greq arajin grqi tvyalner@");
        System.out.println("title");
        String title = scanner.nextLine();
        System.out.println("authorName");
        String authorName = scanner.nextLine();
        System.out.println("price");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.println("count");
        int count = Integer.parseInt(scanner.nextLine());
        Book book = new Book(title, authorName, price, count);
        dataStorage.add(book);
        System.out.println("Book was addet");
    }

    private static void searchByTitle(){
        System.out.println("please input title for search");
        String searchtitle = scanner.nextLine();
        Book book1 = dataStorage.ReturnBookByTitle(searchtitle);
        if (searchtitle == null) {
            System.out.println("pntrvoc girq" + searchtitle + "chkaaa");
        } else {
            System.out.println(book1);
        }
    }

}

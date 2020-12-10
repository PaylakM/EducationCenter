package blog;

import blog.exception.PostNotFoundException;
import blog.model.Post;
import blog.storage.PostStorageImpl;

import java.util.Date;
import java.util.Scanner;

public class BlogMain implements Commands {
    static PostStorageImpl postStorage = new PostStorageImpl();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            commands();
            int commandInt;
            try {
                commandInt=Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                commandInt = -1;
            }
            switch (commandInt) {
                case EXIT:
                    isRun = false;
                    break;
                case ADD_POST:
                    addPost();
                    break;
                case GET_POST_BY_TITLE:
                    getPostByTitle();
                    break;
                case POSTS_BY_CATEGORY:
                    postByCategory();
                    break;
                case SEARCH_BY_KEYWORD:
                    searchByKeyword();
                    break;
                case ALL_POSTS:
                    printAllPost();
                    break;
                default:
                    System.out.println("not found commands");
            }
        }


    }

    private static void searchByKeyword() {
        System.out.println("please input search text");
        String searchKeyword = scanner.next();
        try {
            postStorage.searchPostsByKeyword(searchKeyword);
        }catch (PostNotFoundException e){
            System.out.println(e.getMessage());
        }

    }

    private static void postByCategory() {
        System.out.println("please input categorty text");
        String categoryText = scanner.nextLine();
        postStorage.printPostsByCategory(categoryText);
    }

    private static void printAllPost() {
        postStorage.printAllPosts();
    }

    private static void addPost() {
        System.out.println("please input post info TITLE,TEXT,CATEGORY");
        try {
            String postInfo = scanner.nextLine();
            String[] splitPostInfo = postInfo.split(",");
            Post post=new Post();
            post.setTitle(splitPostInfo[0]);
            post.setText(splitPostInfo[1]);
            post.setCategory(splitPostInfo[2]);
            post.setCreatedData(new Date());
            postStorage.add(post);
            postStorage.printAllPosts();
        }catch (ArrayIndexOutOfBoundsException | NumberFormatException e){
            System.out.println("sxal mutq");
        }

    }

    private static void getPostByTitle() {
        System.out.println("please input post title");
        String postTitle = scanner.nextLine();
        Post postByTitle = postStorage.getPostByTitle(postTitle);
        if (postByTitle == null) {
            System.out.println("please input post");
        } else {
            System.out.println(postByTitle);
        }
    }

    private static void commands() {
        System.out.println("input " + EXIT + " for exit");
        System.out.println("input " + ADD_POST + " to add post");
        System.out.println("input " + GET_POST_BY_TITLE + " to search post");
        System.out.println("input " + POSTS_BY_CATEGORY + " to see category");
        System.out.println("input " + ALL_POSTS + " to see all post");
        System.out.println("input " + SEARCH_BY_KEYWORD + " to search keyword");


    }
}

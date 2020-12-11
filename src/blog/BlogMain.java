package blog;

import blog.exception.PostNotFoundException;
import blog.exception.UnauthorizedException;
import blog.model.Post;
import blog.model.User;
import blog.storage.PostStorageImpl;
import blog.storage.UserStorage;

import java.util.Date;
import java.util.Scanner;

public class BlogMain implements Commands, LoginRegisterCommands {

    private static UserStorage userStorage = new UserStorage();
    private static PostStorageImpl postStorage = new PostStorageImpl();
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser = null;

    public static void main(String[] args) {
        loginRegisterSwitch();

    }

    private static void login() {
        try {
            System.out.println("please input EMAIL & PASSWORD");
            String loginPassword = scanner.nextLine();
            String[] splitLoginPassword = loginPassword.split(",");
            User userLoginRegister = userStorage.searchUser(splitLoginPassword[0], splitLoginPassword[1]);
            if (userLoginRegister == null) {
                System.err.println("please input correct email password");
            } else {
                currentUser = userLoginRegister;
                loginCommandsSwitchCase();

            }
        } catch (ArrayIndexOutOfBoundsException | UnauthorizedException e) {
            System.out.println("user not found");
        }

    }

    private static void searchByKeyword() {
        System.out.println("please input search text");
        String searchKeyword = scanner.next();
        try {
            postStorage.searchPostsByKeyword(searchKeyword);
        } catch (PostNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void postByCategory() {
        System.out.println("please input category text");
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
            Post post = new Post();
            post.setTitle(splitPostInfo[0]);
            post.setText(splitPostInfo[1]);
            post.setCategory(splitPostInfo[2]);
            post.setCreatedData(new Date());
            post.setUser(currentUser);
            postStorage.add(post);
            postStorage.printAllPosts();
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
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
        System.out.println("input " + LOGOUT + " to logout");

    }

    private static void guestCommands() {
        System.out.println("input " + EXIT + " for exit");
        System.out.println("input " + GET_POST_BY_TITLE + " to search post");
        System.out.println("input " + POSTS_BY_CATEGORY + " to see category");
        System.out.println("input " + ALL_POSTS + " to see all post");
        System.out.println("input " + SEARCH_BY_KEYWORD + " to search keyword");
    }

    private static void loginCommands() {
        System.out.println("please input " + LOGIN + " to login");
        System.out.println("please input " + REGISTER + " to register");
        System.out.println("please input " + GUEST + " to visit by guest");
    }

    private static void loginCommandsSwitchCase() {
        boolean isRun = true;
        while (isRun) {
            commands();
            int commandInt;
            try {
                commandInt = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
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
                case LOGOUT:
                    loginRegisterSwitch();
                    break;
                default:
                    System.out.println("not found commands");
            }
        }
    }

    private static void loginCommandsSwitchCaseGuest() {
        boolean isRun = true;
        while (isRun) {
            guestCommands();
            int commandInt;
            try {
                commandInt = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                commandInt = -1;
            }
            switch (commandInt) {
                case EXIT:
                    isRun = false;
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

    private static void loginRegisterSwitch() {
        boolean runLoginRegister = true;
        while (runLoginRegister) {
            loginCommands();
            String loginRegister = scanner.nextLine();
            int intLoginRegister = Integer.parseInt(loginRegister);
            switch (intLoginRegister) {
                case LOGIN:
                    login();
                    break;
                case REGISTER:
                    register();
                    break;
                case GUEST:
                    loginCommandsSwitchCaseGuest();
                default:
                    System.out.println("not found commands");
            }
        }
    }

    private static void register() {
        try {
            System.out.println("please input user info NAME,SURNAME,EMAIL,PASSWORD");
            String userInfo = scanner.nextLine();
            String[] split = userInfo.split(",");
            User user = new User(split[0], split[1], split[2], split[3]);
            userStorage.add(user);
            System.out.println("thanks for register");
            loginRegisterSwitch();
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("incorrect input text");
        }

    }
}

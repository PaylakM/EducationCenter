package blog.storage;

import blog.exception.UnauthorizedException;
import blog.model.User;

public class UserStorage {

    private User[] users;
    private int size;

    public UserStorage(int capacity) {
        users = new User[capacity];
    }

    public UserStorage() {
        users = new User[16];
    }

    private void extend() {
        User[] tmp = new User[users.length + 16];
        System.arraycopy(users, 0, tmp, 0, users.length);
        users = tmp;
    }

    public void add(User user) {
        {
            if (size == users.length) {
                extend();
            }
            users[size++] = user;
        }
    }

    public User searchUser(String email, String password) {
        for (int i = 0; i < size; i++) {
            if (users[i].getEmail().equals(email) && users[i].getPassword().equals(password)) {
            return users[i];
            }
        }
           throw new UnauthorizedException("user not found");
        }
    }

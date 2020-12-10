package blog.storage;

import blog.exception.PostNotFoundException;
import blog.model.Post;

public class PostStorageImpl implements PostStorage {
    private Post[] posts;
    private int size;

    public PostStorageImpl(int capacity) {
        posts = new Post[capacity];
    }

    public PostStorageImpl() {
        posts = new Post[16];
    }

    private void extend() {
        Post[] tmp = new Post[posts.length + 16];
        System.arraycopy(posts, 0, tmp, 0, posts.length);
        posts = tmp;
    }

    @Override
    public void add(Post post) {
        {
            if (size == posts.length) {
                extend();
            }
            posts[size++] = post;
        }
    }

    @Override
    public Post getPostByTitle(String title) {
        for (int i = 0; i < size; i++) {
            if (posts[i].getTitle().equals(title)) {
                return posts[i];
            }
        }
        return null;
    }

    @Override
    public void printAllPosts() {
        for (int i = 0; i < size; i++) {
            System.out.println(posts[i]);
        }
    }

    @Override
    public void searchPostsByKeyword(String keyword) {
        boolean postKeyword=false;
        for (int i = 0; i < size; i++) {
            if (posts[i].getTitle().contains(keyword) || posts[i].getText().contains(keyword)) {
                postKeyword=true;
                System.out.println(posts[i]);
            }
        }
        if(postKeyword==false){
            throw new PostNotFoundException("not found keyword");
        }

    }

    @Override
    public void printPostsByCategory(String category) {
        boolean postByCategory=false;
        for (int i = 0; i < size; i++) {
            if(posts[i].getCategory().equals(category)){
                postByCategory=true;
                System.out.println(posts[i]);
            }
        }
        if(postByCategory==false)
        System.out.println("not found category post");
    }
}

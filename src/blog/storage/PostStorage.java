package blog.storage;


import blog.model.Post;

public interface PostStorage {

    void add(Post post);

    Post getPostByTitle(String title);

    void printAllPosts();

    void searchPostsByKeyword(String keyword);

    void printPostsByCategory(String category);
}

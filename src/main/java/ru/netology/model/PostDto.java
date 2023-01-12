package ru.netology.model;

/**
 * @author Vladimir Troshin
 * @since 12.01.2023
 */
public class PostDto {
    private final long id;
    private final String content;

    public PostDto(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}

package ru.netology.service;

import org.springframework.stereotype.Service;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.model.PostDto;
import ru.netology.repository.PostRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<PostDto> all() {
        return repository.all().stream()
                .filter(post -> !post.isRemoved())
                .map(this::postToDto)
                .collect(Collectors.toList());
    }

    public PostDto getById(long id) {
        Post post = repository.getById(id).orElseThrow(NotFoundException::new);
        if (post.isRemoved()) {
            throw new NotFoundException();
        }
        return postToDto(post);
    }

    public PostDto save(PostDto postDto) {
        long id = postDto.getId();
        PostDto foundPostDto = getById(id);
        return postToDto(repository.save(new Post(id, foundPostDto.getContent(), false)));
    }

    public void removeById(long id) {
        repository.removeById(id);
    }

    private PostDto postToDto(Post post) {
        return new PostDto(post.getId(), post.getContent());
    }
}


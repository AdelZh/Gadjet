package peaksoft.service.impl;


import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import peaksoft.entity.Comment;
import peaksoft.entity.Product;
import peaksoft.entity.User;
import peaksoft.exception.NotFoundException;
import peaksoft.repo.CommentRepo;
import peaksoft.repo.ProductRepo;
import peaksoft.repo.UserRepo;
import peaksoft.request.CommentRequest;
import peaksoft.response.CommentResponse;
import peaksoft.service.CommentService;
import peaksoft.service.ProductService;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepo commentRepo;
    private final ProductRepo productRepo;
    private final UserRepo userRepo;

    @Override
    public CommentResponse saveComment(CommentRequest commentRequest) {
        Product product=productRepo.getProductsByName(commentRequest.name()).orElseThrow(
                () -> {
                    String message="such product with name: "+commentRequest.name()+" not found";
                    log.error(message);
                    return new NotFoundException(message);
                });

        User user=userRepo.getUserByEmail(commentRequest.email()).orElseThrow(EntityNotFoundException::new);

        Comment comment=new Comment();
        comment.setUser(user);
        comment.setProduct(product);
        comment.setComment(commentRequest.commentName());

        comment.setCreatedAt(ZonedDateTime.now());
        commentRepo.save(comment);

        return CommentResponse.builder()
                .commentName(comment.getComment())
                .name(comment.getProduct().getName())
                .build();
    }
}

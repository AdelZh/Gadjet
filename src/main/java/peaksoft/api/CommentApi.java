package peaksoft.api;


import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.request.CommentRequest;
import peaksoft.response.CommentResponse;
import peaksoft.service.CommentService;

@RestController
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
@RequestMapping("/comment")
public class CommentApi {

    private final CommentService commentService;



    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @PostMapping("/save")
    public CommentResponse save(@RequestBody CommentRequest commentRequest){
        return commentService.saveComment(commentRequest);
    }
}

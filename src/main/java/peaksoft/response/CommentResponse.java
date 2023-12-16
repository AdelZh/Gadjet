package peaksoft.response;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CommentResponse {

    @NotNull(message = "must be not null")
    private String name;
    @NotNull(message = "must be not null")
    private String commentName;

    public CommentResponse(String name, String commentName) {
        this.name=name;
        this.commentName = commentName;
    }
}

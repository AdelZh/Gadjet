package peaksoft.repo;

import jdk.dynalink.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.entity.Comment;

import java.util.Optional;

public interface CommentRepo extends JpaRepository<Comment, Long> {

}

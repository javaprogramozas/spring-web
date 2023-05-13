package hu.bearmaster.springtutorial.web.repository;

import hu.bearmaster.springtutorial.web.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}

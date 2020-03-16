package br.com.luizgustavo.postsupvotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luizgustavo.postsupvotes.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}

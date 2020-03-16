package br.com.luizgustavo.postsupvotes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.luizgustavo.postsupvotes.model.Post;
import br.com.luizgustavo.postsupvotes.model.dto.PostDto;
import br.com.luizgustavo.postsupvotes.model.form.PostForm;
import br.com.luizgustavo.postsupvotes.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRep;
	
	public PostDto insert(PostForm form) {
		Post post = new Post();
		post.setText(form.getText());
		post.setAuthor(form.getAuthor());
		post = postRep.save(post);
		return new PostDto(post);
	}
	
	public List<PostDto> findAll(Pageable pageable) {
		pageable = PageRequest.of(pageable.getPageNumber(), 
				pageable.getPageSize(), Sort.by("dtPersist").descending());
		Page<Post> posts = postRep.findAll(pageable);
		return posts.map((post) -> new PostDto(post)).getContent();
	}
	
	public PostDto addUpvote(Long idPost) {
		Post post = postRep.findById(idPost).orElseThrow(() -> new EmptyResultDataAccessException(1));		 
		post.addUpvote();
		post = postRep.save(post);
		return new PostDto(post);
	}
}

package br.com.luizgustavo.postsupvotes.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.luizgustavo.postsupvotes.model.dto.PostDto;
import br.com.luizgustavo.postsupvotes.model.form.PostForm;
import br.com.luizgustavo.postsupvotes.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping
	public ResponseEntity<PostDto> insert(@RequestBody @Valid PostForm form, HttpServletResponse response) {
		PostDto post = postService.insert(form);		
		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequestUri()
					.path("/{id}")
					.buildAndExpand(post.getIdPost())
					.toUri();
		response.setHeader("Location", uri.toASCIIString());
		return ResponseEntity.status(HttpStatus.CREATED).body(post);
	}

	@GetMapping
	public ResponseEntity<List<PostDto>> findAll(Pageable pageable) {
		List<PostDto> postsDto = postService.findAll(pageable);
		return ResponseEntity.ok(postsDto);
	}

	@PutMapping("/{idPost}/upvote")
	public ResponseEntity<PostDto> addUpvote(@PathVariable("idPost") Long idPost) {
		PostDto post = postService.addUpvote(idPost);
		return ResponseEntity.ok(post);
	}
}
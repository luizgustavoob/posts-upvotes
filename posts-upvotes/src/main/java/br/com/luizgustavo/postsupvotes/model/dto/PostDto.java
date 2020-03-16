package br.com.luizgustavo.postsupvotes.model.dto;

import java.time.LocalDateTime;

import br.com.luizgustavo.postsupvotes.model.Post;
import lombok.Data;

@Data
public class PostDto {

	private Long idPost;
	private String text;
	private String author;
	private int upvotes;
	private LocalDateTime dtPersist;
	
	public PostDto() {
	}
	
	public PostDto(Long idPost, String text, String author) {
		this.idPost = idPost;
		this.text = text;
		this.author = author;
	}
	
	public PostDto(String text, String author) {
		this.text = text;
		this.author = author;
	}
	
	public PostDto(Post post) {
		this.idPost = post.getIdPost();
		this.text = post.getText();
		this.author = post.getAuthor();		
		this.upvotes = post.getUpvotes() != null ? post.getUpvotes().intValue() : 0;
		this.dtPersist = post.getDtPersist();
	}
}

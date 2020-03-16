package br.com.luizgustavo.postsupvotes.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"idPost"})
public class Post {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPost;	
	
	@Column(name = "author")	
	private String author;
	
	private String text;	
	
	@Column(name = "upvotes")
	private Integer upvotes;
	
	@Column(name = "dtpersist")
	private LocalDateTime dtPersist;
	
	@PrePersist
	private void prePersist() {
		this.dtPersist = LocalDateTime.now();
	}
	
	public void addUpvote() {
		if (this.upvotes == null) {
			this.upvotes = 0;
		}
		++this.upvotes;
	}
}

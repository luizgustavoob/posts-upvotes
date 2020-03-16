package br.com.luizgustavo.postsupvotes.service;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.luizgustavo.postsupvotes.model.dto.PostDto;
import br.com.luizgustavo.postsupvotes.model.form.PostForm;

@RunWith(SpringRunner.class)
public class PostServiceTest {

	@MockBean
	private PostService postService;

	@Test
	public void insertShouldPersistData() {
		PostForm form = new PostForm("Lorem ipsum", "Luiz");
		PostDto dto = new PostDto(1L, "Lorem ipsum", "Luiz");
		BDDMockito.when(postService.insert(form)).thenReturn(dto);
		PostDto postDto = postService.insert(form);
		assertTrue(dto.equals(postDto));
	}

	@Test
	public void findAllShouldReturnList() {
		Pageable pageable = PageRequest.of(0, 5, Sort.by("dtPersist").descending());
		List<PostDto> posts = Arrays.asList(new PostDto(1L, "Lorem ipsum", "Luiz"),
				new PostDto(2L, "Ipsum lorem", "Gustavo"));
		BDDMockito.when(postService.findAll(pageable)).thenReturn(posts);
		List<PostDto> postsDto = postService.findAll(pageable);
		assertTrue(postsDto.equals(posts));
	}
	
	@Test
	public void addUpvoteShouldUpdatePost() {
		PostDto postDto = new PostDto(1L, "Lorem ipsum", "Luiz");
		postDto.setUpvotes(1);
		BDDMockito.when(postService.addUpvote(1L)).thenReturn(postDto);
		PostDto post = postService.addUpvote(1L);
		assertTrue(post.equals(postDto));
	}

}

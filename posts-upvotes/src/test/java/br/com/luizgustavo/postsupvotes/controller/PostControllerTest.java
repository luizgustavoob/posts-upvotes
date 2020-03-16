package br.com.luizgustavo.postsupvotes.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.luizgustavo.postsupvotes.model.dto.PostDto;
import br.com.luizgustavo.postsupvotes.model.form.PostForm;
import br.com.luizgustavo.postsupvotes.service.PostService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PostControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	private PostService postService;

	@Test
	public void insertShouldReturnStatusCode201() throws URISyntaxException {
		PostForm post = new PostForm("Lorem ipsum", "Luiz");
		PostDto dto = new PostDto("Lorem ipsum", "Luiz");

		BDDMockito.when(postService.insert(post)).thenReturn(dto);

		ResponseEntity<String> response = restTemplate.postForEntity("/posts", post, String.class);

		BDDMockito.verify(postService, times(1)).insert(post);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	}

	@Test
	public void findAllShouldReturnStatusCode200() {
		Pageable pageable = PageRequest.of(0, 5, Sort.by("dtPersist").descending());
		List<PostDto> posts = Arrays.asList(new PostDto("Lorem ipsum", "Luiz"), new PostDto("Ipsum lorem", "Gustavo"));

		BDDMockito.when(postService.findAll(pageable)).thenReturn(posts);

		ResponseEntity<List> response = restTemplate.getForEntity("/posts?page=0&size=5&sort=dtPersist,DESC", List.class);

		BDDMockito.verify(postService, times(1)).findAll(pageable);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertEquals(2, posts.size());
	}

	@Test
	public void addUpvoteShouldReturnStatusCode200() {
		PostDto postDto = new PostDto(1L, "Lorem ipsum", "Luiz");
		postDto.setUpvotes(1);
		
		BDDMockito.when(postService.addUpvote(postDto.getIdPost())).thenReturn(postDto);

		Map<String, Long> param = new HashMap<String, Long>();
		param.put("id", postDto.getIdPost());
		HttpEntity<PostDto> requestEntity = new HttpEntity<PostDto>(postDto, new HttpHeaders());
		
		ResponseEntity<PostDto> response = restTemplate.exchange("/posts/{id}/upvote", HttpMethod.PUT, requestEntity,
				PostDto.class, param);
		
		BDDMockito.verify(postService, times(1)).addUpvote(postDto.getIdPost());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo(postDto);
		assertTrue(postDto.getUpvotes() == 1);
	}
	
	@Test
	public void insertWithTextNullShouldReturnStatusCode400() {
		PostForm post = new PostForm(null, "Luiz");
		ResponseEntity<String> response = restTemplate.postForEntity("/posts", post, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void insertWithTextBlankShouldReturnStatusCode400() {
		PostForm post = new PostForm("", "Luiz");
		ResponseEntity<String> response = restTemplate.postForEntity("/posts", post, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void insertWithAuthorGreaterThan256ReturnStatusCode400() {
		PostForm post = new PostForm("Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum " + 
				"Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum " + 
				"Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum " + 
				"Lorem ipsum", "Luiz");
		ResponseEntity<String> response = restTemplate.postForEntity("/posts", post, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void insertWithAuthorNullShouldReturnStatusCode400() {
		PostForm post = new PostForm("Lorem ipsum", null);
		ResponseEntity<String> response = restTemplate.postForEntity("/posts", post, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void insertWithAuthorBlankShouldReturnStatusCode400() {
		PostForm post = new PostForm("Lorem ipsum", "");
		ResponseEntity<String> response = restTemplate.postForEntity("/posts", post, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void insertWithAuthorGreaterThan20ReturnStatusCode400() {
		PostForm post = new PostForm("Lorem ipsum", "Luiz Gustavo Bourscheit");
		ResponseEntity<String> response = restTemplate.postForEntity("/posts", post, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	}

}

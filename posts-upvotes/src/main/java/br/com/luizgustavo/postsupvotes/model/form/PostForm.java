package br.com.luizgustavo.postsupvotes.model.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostForm {

	@NotBlank @NotNull @Length(max = 256)
	private String text;
	@NotBlank @NotNull @Length(max = 20)
	private String author;
}

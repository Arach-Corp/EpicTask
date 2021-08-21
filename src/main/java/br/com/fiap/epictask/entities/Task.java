package br.com.fiap.epictask.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "{task.title.empty}")
	private String title;

	@NotBlank(message = "{task.description.size}")
//	@Size(message = "{task.description.size}", min = 20)
	private String description;

	@Min(message = "{task.points.min}", value = 10)
	private int points;
		
}

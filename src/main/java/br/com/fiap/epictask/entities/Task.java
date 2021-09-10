package br.com.fiap.epictask.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

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
    private String description;

    @Min(message = "{task.points.min}", value = 10)
    private int points;

    @CreationTimestamp
    private LocalDateTime creationTime;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

	public Task(Long id, String title, String description, int points, User user) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.points = points;
		this.user = user;
	}

}

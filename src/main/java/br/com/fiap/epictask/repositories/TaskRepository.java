package br.com.fiap.epictask.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.epictask.entities.Task;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long>{

	List<Task> findAllByTitleAndDescription(String title, String description);
	
}
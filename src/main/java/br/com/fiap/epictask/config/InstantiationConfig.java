package br.com.fiap.epictask.config;

import br.com.fiap.epictask.entities.Task;
import br.com.fiap.epictask.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class InstantiationConfig implements CommandLineRunner {

    @Autowired
    private TaskRepository repository;

    @Override
    public void run(String... args) throws Exception {

        Task task = Task.builder()
                .description("Teste 1")
                .points(11)
                .title("Teste 1assssssa")
                .build();

        Task task1 = Task.builder()
                .description("Teste 2")
                .points(11)
                .title("Teste 2asdasdasddddddsad")
                .build();

        Task task2 = Task.builder()
                .description("Teste 3asdddddddd")
                .points(11)
                .title("Teste 3")
                .build();

        repository.saveAll(Arrays.asList(task, task1, task2));
    }

}

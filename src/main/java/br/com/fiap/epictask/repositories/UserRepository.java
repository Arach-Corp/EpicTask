package br.com.fiap.epictask.repositories;

import br.com.fiap.epictask.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

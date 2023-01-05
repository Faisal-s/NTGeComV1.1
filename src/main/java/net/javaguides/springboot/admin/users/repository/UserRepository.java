package net.javaguides.springboot.admin.users.repository;

import net.javaguides.springboot.admin.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}

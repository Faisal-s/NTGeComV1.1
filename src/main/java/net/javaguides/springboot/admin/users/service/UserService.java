package net.javaguides.springboot.admin.users.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.admin.users.model.User;

public interface UserService {
	List<User> getAllUsers();
	void saveUser(User user);
	User getUserById(long id);
	void deleteUserById(long id);
	Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}

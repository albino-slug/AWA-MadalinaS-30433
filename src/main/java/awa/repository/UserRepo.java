package awa.repository;

import awa.model.Role;
import awa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findById(Integer id);

    Optional<User> findByRole(Role role);

    public Boolean deleteById(Integer id);

    public Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);

}

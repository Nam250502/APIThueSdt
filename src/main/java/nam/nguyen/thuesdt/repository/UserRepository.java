package nam.nguyen.thuesdt.repository;



import nam.nguyen.thuesdt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findUserByEmail(String email);

    Boolean existsByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);


    boolean existsByUsername(String username);

    @Modifying
    @Query("UPDATE User u SET u.username = :newUsername, u.email = :newEmail WHERE u.id = :userId")
    void updateUser(@Param("userId") Long userId, @Param("newUsername") String newUsername, @Param("newEmail") String newEmail);
    @Query("SELECT u FROM User u WHERE u.username = :username")
    public User getUserByUsername(@Param("username") String username);
}
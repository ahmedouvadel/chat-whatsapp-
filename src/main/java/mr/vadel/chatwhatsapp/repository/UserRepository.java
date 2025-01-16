package mr.vadel.chatwhatsapp.repository;

import mr.vadel.chatwhatsapp.constants.UserConstant;
import mr.vadel.chatwhatsapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query(name = UserConstant.FIND_USER_BY_EMAIL)
    Optional<User> findByEmail(@Param("email") String userEmail);

    @Query(name = UserConstant.FIND_USER_BY_PUBLIC_ID)
    Optional<User> findByPublicId(String publicId);

    @Query(name = UserConstant.FIND_ALL_USERS_EXCEPT_SELF)
    List<User> findAllUsersExpectSelf(@Param("publicId") String senderId);
}

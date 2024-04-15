package conn.ra.service;

import conn.ra.model.dto.request.UserRegister;
import conn.ra.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    User register(User user);
    Page<User> getAll(Pageable pageable);
    User findById(Long id);
    void delete(Long id);
    User save(User user);
    User updateAcc(UserRegister userRegister, Long id);
    Optional<User> findByUserName(String userName);
}

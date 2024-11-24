package conn.ra.service;

import conn.ra.model.dto.request.UserRegister;
import conn.ra.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User register(UserRegister userRegister);

    List<User> getAll();

    User findById(Long id);

    void delete(Long id);

    User save(User user);

    void updateAcc(UserRegister userRegister, Long id);

    Optional<User> findByUserName(String userName);
}

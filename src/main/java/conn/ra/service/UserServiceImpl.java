package conn.ra.service;

import conn.ra.model.dto.request.UserRegister;
import conn.ra.model.entity.Role;
import conn.ra.model.entity.User;
import conn.ra.model.enums.ERole;
import conn.ra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User register(User user) {
        if (userRepository.existsByUserName ( user.getUserName () )) {
            throw new RuntimeException ( "UserName is exists" );
        }

        Set<Role> role = new HashSet<> ();
        role.add ( roleService.findByRoleName ( ERole.ROLE_USER ) );
        User users = User.builder ()
                .userName ( user.getUserName () )
                .password ( passwordEncoder.encode ( user.getPassword () ) )
                .email ( user.getEmail () )
                .status ( true )
                .roles ( role )
                .build ();
        userRepository.save ( users );
        return user;
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll ( pageable );
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById ( id ).orElse ( null );
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById ( id );
    }

    @Override
    public User save(User user) {
        return userRepository.save ( user );
    }

    @Override
    public User updateAcc(UserRegister userRegister, Long id) {
        if (userRepository.existsByUserName ( userRegister.getUsername () )) {
            throw new RuntimeException ( "username is exists" );
        }

        User userOld = findById ( id );

        Set<Role> roles = userOld.getRoles ();

        User users = User.builder ()
                .fullName ( userRegister.getFullName () )
                .userName ( userRegister.getUsername () )
                .password ( userOld.getPassword () )
                .email ( userRegister.getEmail () )
                .images ( userRegister.getImages () )
                .phone ( userRegister.getPhone () )
                .address ( userRegister.getAddress () )
                .status ( true )
                .roles ( roles )
                .build ();
        users.setId ( id );
        return userRepository.save ( users );
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return userRepository.findByUserName ( userName );
    }
}
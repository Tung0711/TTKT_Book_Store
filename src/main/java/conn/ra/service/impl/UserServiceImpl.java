package conn.ra.service.impl;

import conn.ra.model.dto.request.UserRegister;
import conn.ra.model.entity.Role;
import conn.ra.model.entity.User;
import conn.ra.model.enums.ERole;
import conn.ra.repository.UserRepository;
import conn.ra.service.RoleService;
import conn.ra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
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
    public User register(UserRegister userRegister) {
        Set<Role> role = new HashSet<> ();
        role.add ( roleService.findByRoleName ( ERole.ROLE_USER ) );
        User users = User.builder ()
                .fullName ( userRegister.getFullName () )
                .username ( userRegister.getUsername () )
                .password ( passwordEncoder.encode ( userRegister.getPassword () ) )
                .email ( userRegister.getEmail () )
                .status ( true )
                .roles ( role )
                .build ();
        userRepository.save ( users );
        return users;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll ();
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
    public void updateAcc(UserRegister userRegister, Long id) {
        User userOld = findById ( id );

        Set<Role> roles = userOld.getRoles ();

        User users = User.builder ()
                .fullName ( userRegister.getFullName () )
                .username ( userRegister.getUsername () )
                .dateOfBirth ( userRegister.getDateOfBirth () )
                .password ( userOld.getPassword () )
                .email ( userRegister.getEmail () )
                .avatar ( userOld.getAvatar () )
                .phone ( userRegister.getPhone () )
                .address ( userRegister.getAddress () )
                .status ( true )
                .roles ( roles )
                .build ();
        users.setId ( id );
        userRepository.save ( users );
    }

    @Override
    public Optional<User> findByUserName(String username) {
        return userRepository.findByUsername ( username );
    }

}
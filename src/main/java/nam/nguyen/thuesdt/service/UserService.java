package nam.nguyen.thuesdt.service;


import lombok.extern.slf4j.Slf4j;
import nam.nguyen.thuesdt.model.Role;
import nam.nguyen.thuesdt.model.User;
import nam.nguyen.thuesdt.repository.RoleRepository;
import nam.nguyen.thuesdt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRepository repo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role defaultRole = roleService.getDefaultRole();
        Set<Role> roles = new HashSet<>();
        roles.add(defaultRole);
        user.setRoles(roles);
        userRepository.save(user);

    }


}

package com.bulbul.bestpractice;

import com.bulbul.bestpractice.user.entity.User;
import com.bulbul.bestpractice.user.repository.UserRepository;
import com.bulbul.bestpractice.common.constant.ApplicationConstant;
import com.bulbul.bestpractice.rolemanagement.entity.Role;
import com.bulbul.bestpractice.rolemanagement.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * CommandLineAppStartupRunner
 * @author  BulbulAhmed;
 */


@Component
@Slf4j
@RequiredArgsConstructor
public class CommandLineAppStartupRunner implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) {

        log.info("..............App Running............");

        //create default role for ADMIN
        if (roleRepository.findByName(ApplicationConstant.ROLE_ADMIN).isEmpty()) {
            Role role = new Role(ApplicationConstant.ROLE_ADMIN);
            role.setIsActive(true);
            roleRepository.save(role);
        }

        //create default role for USER
        if (roleRepository.findByName(ApplicationConstant.ROLE_USER).isEmpty()) {
            Role role = new Role(ApplicationConstant.ROLE_USER);
            role.setIsActive(true);
            roleRepository.save(role);
        }

        //create default admin user
        Role roleAdmin = this.roleRepository.findByName(ApplicationConstant.ROLE_ADMIN).orElse(null);
        Optional<User> adminUser = this.userRepository.findByUsername("admin");
        if (adminUser.isEmpty()) {
            Set<Role> rolesAdminSet = new HashSet<>();
            User user = new User();
            user.setUsername("admin");
            user.setPassword(bCryptPasswordEncoder.encode("admin#user"));
            user.setEnabled(true);
            rolesAdminSet.add(roleAdmin);
            user.setRoles(rolesAdminSet);
            user.setIsActive(true);
            this.userRepository.save(user);
        }


        //create default normal user
        Role roleUser = this.roleRepository.findByName(ApplicationConstant.ROLE_USER).orElse(null);
        Optional<User> normalUser = this.userRepository.findByUsername("user");
        if (normalUser.isEmpty()) {
            Set<Role> rolesUserSet = new HashSet<>();
            User user = new User();
            user.setUsername("user");
            user.setPassword(bCryptPasswordEncoder.encode("user#user"));
            user.setEnabled(true);
            rolesUserSet.add(roleUser);
            user.setRoles(rolesUserSet);
            user.setIsActive(true);
            this.userRepository.save(user);
        }

    }
}

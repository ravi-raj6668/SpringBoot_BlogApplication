package io.innodev.blogapp;

import io.innodev.blogapp.config.Constant;
import io.innodev.blogapp.entity.Role;
import io.innodev.blogapp.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Slf4j
@SpringBootApplication
public class SpringBootBlogApplication implements CommandLineRunner {


    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    @Autowired
    public SpringBootBlogApplication(PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBlogApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            Role role = new Role();
            role.setId(Constant.ROLE_ADMIN);
            role.setRoleName("ADMIN_USER");

            Role role1 = new Role();
            role1.setId(Constant.ROLE_TEST_USER);
            role1.setRoleName("NORMAL_USER");

            List<Role> roles = List.of(role,role1);
            List<Role> roleList = roleRepository.saveAll(roles);

            roleList.forEach(r-> log.info(r.getRoleName()));
        }catch (Exception e){
            log.error("Getting error " + e.getMessage());
        }
    }
}

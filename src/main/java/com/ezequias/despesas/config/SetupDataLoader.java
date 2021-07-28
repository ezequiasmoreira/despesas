package com.ezequias.despesas.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ezequias.despesas.domain.Role;
import com.ezequias.despesas.domain.User;
import com.ezequias.despesas.repository.RoleRepository;
import com.ezequias.despesas.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Configuration
public class SetupDataLoader  implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        userRepository.deleteAll();
        roleRepository.deleteAll();
        
        Role roleAdmin = createRoleIfNotFound("ROLE_ADMIN");
        Role roleUser = createRoleIfNotFound("ROLE_USER");
        
        User joao = new User(null,"João", "Souza", "joao@gmail.com",passwordEncoder.encode("123"),true);
        User maria = new User(null,"Maria", "Teixeira", "maria@gmail.com",passwordEncoder.encode("123"),true);

        joao.setRoles(Arrays.asList(roleAdmin));        
        maria.setRoles(Arrays.asList(roleUser));
        createUserIfNotFound(joao);
        createUserIfNotFound(maria);
        
        
    }

    private User createUserIfNotFound(final User user) {
        Optional<User> obj = userRepository.findByEmail(user.getEmail());
        if(obj.isPresent()) {
            return obj.get();
        }
        return userRepository.save(user);
    }
    
    private Role createRoleIfNotFound(String name){
        Optional<Role> role = roleRepository.findByName(name);
        if (role.isPresent()){
            return role.get();
        }
        return roleRepository.save(new Role(name));
    }

}

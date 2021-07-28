package com.ezequias.despesas.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ezequias.despesas.domain.Role;
import com.ezequias.despesas.domain.Usuario;
import com.ezequias.despesas.repository.RoleRepository;
import com.ezequias.despesas.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Configuration
public class SetupDataLoader  implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UsuarioRepository usuarioRepository;
    
    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

    	usuarioRepository.deleteAll();
    	usuarioRepository.deleteAll();
        
        Role roleAdmin = createRoleIfNotFound("ROLE_ADMIN");
        Role roleUser = createRoleIfNotFound("ROLE_USER");
        
        Usuario joao = new Usuario(null,"João", "Souza", "joao@gmail.com",passwordEncoder.encode("123"),true);
        Usuario maria = new Usuario(null,"Maria", "Teixeira", "maria@gmail.com",passwordEncoder.encode("123"),true);

        joao.setRoles(Arrays.asList(roleAdmin));        
        maria.setRoles(Arrays.asList(roleUser));
        createUserIfNotFound(joao);
        createUserIfNotFound(maria);
        
        
    }

    private Usuario createUserIfNotFound(final Usuario user) {
        Optional<Usuario> obj = usuarioRepository.findByEmail(user.getEmail());
        if(obj.isPresent()) {
            return obj.get();
        }
        return usuarioRepository.save(user);
    }
    
    private Role createRoleIfNotFound(String name){
        Optional<Role> role = roleRepository.findByName(name);
        if (role.isPresent()){
            return role.get();
        }
        return roleRepository.save(new Role(name));
    }

}

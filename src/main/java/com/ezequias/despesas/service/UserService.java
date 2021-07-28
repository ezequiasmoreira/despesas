package com.ezequias.despesas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezequias.despesas.domain.User;
import com.ezequias.despesas.domain.Usuario;
import com.ezequias.despesas.dto.UserDTO;
import com.ezequias.despesas.dto.UsuarioDTO;
import com.ezequias.despesas.exception.ObjectNotFoundException;
import com.ezequias.despesas.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(Integer id){
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User salvar(User user) {
		user.setId(null);
		return userRepository.save(user);
	}
	
	public User fromDTO(UserDTO userDTO) {		
		return new User(userDTO);
	}
	
	public User update(User user) {
        Optional<User> updateUser = userRepository.findById(user.getId());
        return updateUser.map(u -> userRepository.save(new User(u.getId(), user.getFirstName(), user.getLastName(), user.getEmail(),
        		u.getPassword(), u.isEnabled())))
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado!"));
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

}

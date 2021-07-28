package com.ezequias.despesas.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ezequias.despesas.domain.Role;
import com.ezequias.despesas.domain.User;
import com.ezequias.despesas.dto.UserDTO;
import com.ezequias.despesas.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/users")
public class UserResource  {
	
	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> users = userService.findAll();
        List<UserDTO> listDTO = users.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
	
	@RequestMapping(value = "/{id}/roles", method = RequestMethod.GET)
    public ResponseEntity<List<Role>> findRoles(@PathVariable Integer id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(user.getRoles());
    }
    
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(new UserDTO(user));
    }
    
    @CrossOrigin
	@Transactional(rollbackOn = Exception.class)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UserDTO> salvar(@RequestBody UserDTO dto) {
		User user = userService.fromDTO(dto);
		return ResponseEntity.ok().body(new UserDTO(userService.salvar(user)));
	}
    
    @CrossOrigin
	@Transactional(rollbackOn = Exception.class)
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<UserDTO> update(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        User user = userService.fromDTO(userDTO);
        user.setId(id);
        return ResponseEntity.ok().body(new UserDTO(userService.update(user)));
    }


    @CrossOrigin
   	@Transactional(rollbackOn = Exception.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

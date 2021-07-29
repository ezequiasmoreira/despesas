package com.ezequias.despesas.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ezequias.despesas.domain.Role;
import com.ezequias.despesas.domain.Usuario;
import com.ezequias.despesas.dto.UsuarioDTO;
import com.ezequias.despesas.service.UsuarioService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource  {
	
	@Autowired
	UsuarioService usuarioService;

	@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        List<Usuario> usuarios = usuarioService.findAll();
        List<UsuarioDTO> listaDTO = usuarios.stream().map(x -> new UsuarioDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listaDTO);
    }
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping(value = "/{id}/roles", method = RequestMethod.GET)
    public ResponseEntity<List<Role>> findRoles(@PathVariable Integer id) {
       Usuario usuario = usuarioService.findById(id);
        return ResponseEntity.ok().body(usuario.getRoles());
    }
    
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Integer id) {
        Usuario usuario = usuarioService.findById(id);
        return ResponseEntity.ok().body(new UsuarioDTO(usuario));
    }
    
    @CrossOrigin
	@Transactional(rollbackOn = Exception.class)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UsuarioDTO> salvar(@RequestBody UsuarioDTO dto) {
		Usuario usuario = usuarioService.fromDTO(dto);
		return ResponseEntity.ok().body(new UsuarioDTO(usuarioService.salvar(usuario)));
	}
    
    @CrossOrigin
	@Transactional(rollbackOn = Exception.class)
    @RequestMapping(method = RequestMethod.PUT)
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<UsuarioDTO> update(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioService.fromDTO(usuarioDTO);
        usuario.setId(id);
        return ResponseEntity.ok().body(new UsuarioDTO(usuarioService.update(usuario)));
    }


    @CrossOrigin
   	@Transactional(rollbackOn = Exception.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
    	usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

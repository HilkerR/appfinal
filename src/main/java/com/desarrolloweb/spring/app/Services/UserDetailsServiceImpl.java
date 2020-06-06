package com.desarrolloweb.spring.app.Services;

import java.util.ArrayList;
import java.util.List;

import com.desarrolloweb.spring.app.dao.UsuarioRepository;
import com.desarrolloweb.spring.app.entities.Role;
import com.desarrolloweb.spring.app.entities.Usuario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    UsuarioRepository repository;

    private Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {

        Usuario usuario = repository.findByNombre(nombre);

        if(usuario == null){
            logger.error("Error en el Login: no existe el usuario '" + nombre + "' en el sistema!");
            throw new UsernameNotFoundException("Username: " + nombre+ " no existe en el sistema!");
        }

        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        Role rol = usuario.getRole();
        roles.add(new SimpleGrantedAuthority(rol.getDescripcion()));
        if(roles.isEmpty()) {
            logger.error("Error en el Login: Usuario '" + nombre + "' no tiene roles asignados!");
            throw new UsernameNotFoundException("Error en el Login: usuario '" + nombre + "' no tiene roles asignados!");
        }

        return new User(usuario.getNombre(), usuario.getContrasena(), roles);
    }
}


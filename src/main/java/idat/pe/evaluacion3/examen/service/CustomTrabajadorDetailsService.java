package idat.pe.evaluacion3.examen.service;

import idat.pe.evaluacion3.examen.entity.Trabajador;
import idat.pe.evaluacion3.examen.repository.TrabajadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomTrabajadorDetailsService implements UserDetailsService {
    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        Trabajador trabajador = trabajadorRepository.findByUsuario(usuario);
        if (trabajador == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(trabajador.getUsuario())
                .password(trabajador.getContrasena())
                .roles(trabajador.getRol().replace("ROLE_", "")) // Solo el rol, sin "ROLE_"
                .build();
    }
}


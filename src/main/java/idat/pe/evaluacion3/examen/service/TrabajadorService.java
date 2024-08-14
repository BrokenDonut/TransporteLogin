package idat.pe.evaluacion3.examen.service;

import idat.pe.evaluacion3.examen.entity.Trabajador;
import idat.pe.evaluacion3.examen.repository.TrabajadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrabajadorService {
    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Trabajador getById(Integer id) {
        return trabajadorRepository.findById(id).orElse(null);
    }

    public List<Trabajador> findAll() {
        return trabajadorRepository.findAll();
    }

    public Trabajador findByUsuario(String usuario){
        return  trabajadorRepository.findByUsuario(usuario);
    }

    public void save(Trabajador trabajador) {
        trabajador.setContrasena(passwordEncoder.encode(trabajador.getContrasena()));
        trabajadorRepository.save(trabajador);
    }

    public void delete(Integer id) {
        trabajadorRepository.deleteById(id);
    }

    // update
    public void update(Integer id, Trabajador trabajador) {
        Trabajador orTrabajador = trabajadorRepository.findById(id).orElseThrow(() -> new RuntimeException("Trabajador no encontrado"));

        if (orTrabajador != null) {
            orTrabajador.setCorreo(trabajador.getCorreo());
            orTrabajador.setTelefono(trabajador.getTelefono());
            orTrabajador.setDepartamento(trabajador.getDepartamento());
            orTrabajador.setRol(trabajador.getRol());
            trabajadorRepository.save(orTrabajador);
        }
    }

}


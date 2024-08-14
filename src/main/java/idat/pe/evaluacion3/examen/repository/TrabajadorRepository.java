package idat.pe.evaluacion3.examen.repository;

import idat.pe.evaluacion3.examen.entity.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TrabajadorRepository extends JpaRepository<Trabajador, Integer> {
    Trabajador findByUsuario(String usuario);

}

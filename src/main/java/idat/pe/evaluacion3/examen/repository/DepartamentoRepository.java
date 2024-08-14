package idat.pe.evaluacion3.examen.repository;

import idat.pe.evaluacion3.examen.entity.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {
    @Query(value = "SELECT * FROM departamento nombre =: departamentoName", nativeQuery = true)
    List<Departamento> listarDepartamento(@Param("departamentoName") String departamentoName);
}


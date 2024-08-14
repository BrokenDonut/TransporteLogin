package idat.pe.evaluacion3.examen.repository;

import idat.pe.evaluacion3.examen.entity.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Integer>{
    @Query(value = "SELECT * FROM tipo_documento WHERE nombre = :nombre", nativeQuery = true)
    List<TipoDocumento> listarPorNombre(@Param("nombre") String nombre);
}

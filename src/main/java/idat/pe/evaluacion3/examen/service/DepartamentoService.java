package idat.pe.evaluacion3.examen.service;

import idat.pe.evaluacion3.examen.entity.Departamento;
import idat.pe.evaluacion3.examen.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartamentoService {
    @Autowired
    private DepartamentoRepository departamentoRepository;

    public List<Departamento> findAll(){
        return departamentoRepository.findAll();
    }

    public Departamento findById(Integer id) {
        return departamentoRepository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        departamentoRepository.deleteById(id);
    }

    public void insert(Departamento departamento) {
        departamentoRepository.save(departamento);
    }

    public void update(Integer id, Departamento departamento) {
        Departamento orDepartamento = findById(id);

        if (orDepartamento != null) {
            orDepartamento.setNombre(departamento.getNombre());
            orDepartamento.setDescripcion(departamento.getDescripcion());

            departamentoRepository.save(departamento);
        }
    }
}

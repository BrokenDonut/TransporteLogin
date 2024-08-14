package idat.pe.evaluacion3.examen.service;

import idat.pe.evaluacion3.examen.entity.TipoDocumento;
import idat.pe.evaluacion3.examen.repository.TipoDocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TipoDocumentoService {
    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    // get all
    public List<TipoDocumento> findAll() {
        return tipoDocumentoRepository.findAll();
    }

    //insert
    public void insert(TipoDocumento tipoDocumento) {
        tipoDocumentoRepository.save(tipoDocumento);
    }
}

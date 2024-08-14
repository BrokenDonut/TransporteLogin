package idat.pe.evaluacion3.examen.controller;

import idat.pe.evaluacion3.examen.entity.Departamento;
import idat.pe.evaluacion3.examen.entity.TipoDocumento;
import idat.pe.evaluacion3.examen.service.DepartamentoService;
import idat.pe.evaluacion3.examen.service.TipoDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
public class TipoDocumentoController {
    @Autowired
    private TipoDocumentoService tipoDocumentoService;
    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping("/admin/tipodocumento/lista")
    public String Index(Model model) {
        List<TipoDocumento>tipoDocumentos = tipoDocumentoService.findAll();
        model.addAttribute("tipoDocumento", tipoDocumentos);
        return "administrador/listaTipoDocumento";
    }

    //create
    @GetMapping("/admin/tipodocumento/register")
    public String mostrarFormularioRegistro(Model model) {
        List<Departamento> departamentos = departamentoService.findAll();
        model.addAttribute("tipoDocumento", new TipoDocumento());
        model.addAttribute("departamentos", departamentos);
        return "administrador/tipoDocumentoRegister";
    }

    @PostMapping("/admin/tipodocumento/register")
    public String registrarTipoDocumento(@ModelAttribute("tipoDocumento") TipoDocumento tipoDocumento) {
        tipoDocumentoService.insert(tipoDocumento);
        return "redirect:/admin/tipodocumento/lista";
    }
}

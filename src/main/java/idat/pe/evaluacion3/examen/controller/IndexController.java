package idat.pe.evaluacion3.examen.controller;

import idat.pe.evaluacion3.examen.entity.Departamento;
import idat.pe.evaluacion3.examen.entity.Trabajador;
import idat.pe.evaluacion3.examen.repository.DepartamentoRepository;
import idat.pe.evaluacion3.examen.service.TrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private TrabajadorService trabajadorService;

    @Autowired
    private DepartamentoRepository departamentoRepository;
    @GetMapping("/Index")
    public String Index(Model model){
        return "Index";
    }

    //lista
    @GetMapping("/admin/trabajador/lista")
    public String listarTrabajadores(Model model) {
        List<Trabajador> trabajadores = trabajadorService.findAll();
        model.addAttribute("trabajadores", trabajadores);
        return "administrador/listaTrabajador";
    }

    //delete
    @GetMapping("/admin/trabajador/delete/{id}")
    public String Delete(Model model, @PathVariable Integer id) {
        Trabajador trabajador = trabajadorService.getById(id);

        model.addAttribute("trabajador", trabajador);

        return "administrador/trabajadorDelete";
    }

    @PostMapping("/admin/trabajador/delete/{id}")
    public String Edit(@PathVariable Integer id) {
        trabajadorService.delete(id);

        return "redirect:/admin/trabajador/lista";
    }
    //edit
    @GetMapping("/admin/trabajador/edit/{id}")
    public String Edit(Model model, @PathVariable Integer id) {
        Trabajador trabajador = trabajadorService.getById(id);
        model.addAttribute("departamentos", departamentoRepository.findAll());
        model.addAttribute("trabajador", trabajador);

        return "administrador/trabajadorActualizar";
    }

    @PostMapping("/admin/trabajador/edit")
    public String Edit(@ModelAttribute("trabajador") Trabajador trabajador) {
        // Verifica si el departamento está presente en el formulario
        if (trabajador.getDepartamento() != null && trabajador.getDepartamento().getId() != null) {
            Departamento departamento = departamentoRepository.findById(trabajador.getDepartamento().getId())
                    .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));
            trabajador.setDepartamento(departamento);
        } else {
            // Manejo de caso en el que no se selecciona un departamento
            // Puedes redirigir al usuario a una página de error o mostrar un mensaje
            return "redirect:/error"; // Ejemplo de redirección en caso de error
        }
        trabajadorService.update(trabajador.getId(), trabajador);
        return "redirect:/admin/trabajador/lista";
    }

}
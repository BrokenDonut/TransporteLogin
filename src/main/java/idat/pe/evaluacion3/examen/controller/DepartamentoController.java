package idat.pe.evaluacion3.examen.controller;

import idat.pe.evaluacion3.examen.entity.Departamento;
import idat.pe.evaluacion3.examen.entity.Trabajador;
import idat.pe.evaluacion3.examen.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DepartamentoController {
    @Autowired
    private DepartamentoService departamentoService;
    @GetMapping("/admin/departamento/lista")
    public String listarDepartamento(Model model) {
        List<Departamento> departamentos = departamentoService.findAll();
        model.addAttribute("departamentos", departamentos);
        return "administrador/listaDepartamento";
    }

    //delete
    @GetMapping("/admin/departamento/delete/{id}")
    public String Delete(Model model, @PathVariable Integer id) {
        Departamento departamento = departamentoService.findById(id);

        model.addAttribute("departamento", departamento);

        return "administrador/departamentoDelete";
    }

    @PostMapping("/admin/departamento/delete/{id}")
    public String Edit(@PathVariable Integer id) {
        departamentoService.delete(id);
        return "redirect:/admin/departamento/lista";
    }

    //create
    @GetMapping("/admin/departamento/register")
    public String showRegistrationForm(Model model) {
        Departamento departamento = new Departamento();
        model.addAttribute("departamento", departamento);

        return "administrador/departamentoRegister";
    }

    @PostMapping("/admin/departamento/register")
    public String registerDepartamento(@ModelAttribute Departamento departamento) {
        departamentoService.insert(departamento);
        return "redirect:/admin/departamento/lista";
    }

    //edit
    @GetMapping("/admin/departamento/edit/{id}")
    public String Edit(Model model, @PathVariable Integer id) {
        Departamento departamento = departamentoService.findById(id);

        model.addAttribute("departamento", departamento);

        return "administrador/departamentoActualizar";
    }

    @PostMapping("/admin/departamento/edit")
    public String Edit(@ModelAttribute("departamento") Departamento departamento) {
        departamentoService.update(departamento.getId(), departamento);

        return "redirect:/admin/departamento/lista";
    }
}

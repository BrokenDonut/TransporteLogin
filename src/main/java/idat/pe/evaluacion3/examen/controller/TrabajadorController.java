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

@Controller
public class TrabajadorController {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private TrabajadorService trabajadorService;

    @GetMapping("/admin/trabajador/register")
    public String showRegistrationForm(Model model) {
        // Recupera la lista de departamentos para mostrar en el formulario
        model.addAttribute("departamentos", departamentoRepository.findAll());
        model.addAttribute("trabajador", new Trabajador());
        return "administrador/trabajadorRegister";
    }

    @PostMapping("/admin/trabajador/register")
    public String registerUser(@ModelAttribute Trabajador trabajador) {
        // Verifica si el rol es null o vacío, si es así, asigna ROLE_USER por defecto
        if (trabajador.getRol() == null || trabajador.getRol().isEmpty()) {
            trabajador.setRol("ROLE_USER");
        }

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

        trabajadorService.save(trabajador);
        return "redirect:/admin/trabajador/lista";
    }



    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/default")
    public String defaultAfterLogin() {
        return "redirect:/user/home";
    }

    @GetMapping("/user/home")
    public String userHome(Model model) {
        model.addAttribute("message", "eres un usuario");
        return "home";
    }

    @GetMapping("/admin/interfaz")
    public String adminHome() {
        return "administrador/interfaz";
    }

}

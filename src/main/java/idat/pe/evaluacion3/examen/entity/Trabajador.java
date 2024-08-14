package idat.pe.evaluacion3.examen.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "trabajador")
public class Trabajador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 45)
    private String nombre;

    @Column(nullable = false, length = 45)
    private String apellido;

    @Column(nullable = false, unique = true)
    private Integer dni;

    @Column(nullable = false, length = 45, unique = true)
    private String correo;

    @Column(nullable = false)
    private LocalDate fNacimiento;  // Usar LocalDateTime para DATETIME en Java

    @Column(nullable = false, unique = true)
    private Integer telefono;

    @Column(nullable = false, length = 45)
    private String rol;

    @Column(nullable = false, length = 45, unique = true)
    private String usuario;

    @Column(nullable = false, length = 60)  // longitud mayor para almacenar contraseñas encriptadas
    private String contrasena;

    @ManyToOne
    @JoinColumn(name = "departamento_id", nullable = true)  // Permitir valores nulos
    private Departamento departamento;
}



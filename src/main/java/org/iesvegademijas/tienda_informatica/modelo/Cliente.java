package org.iesvegademijas.tienda_informatica.modelo;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Min(value=0, message = "{msg.valid.min}")
    private int id;

    @NotNull(message = "{msg.valid.not.null}")
    @NotBlank(message = "{msg.valid.not.blank}")
    @Size(max=30, message = "{msg.valid.max}")
    private String nombre;

    @NotNull(message = "{msg.valid.not.null}")
    @NotBlank(message = "{msg.valid.not.blank}")
    @Size(max=30, message = "{msg.valid.max}")
    private String apellido1;

    /*opcional*/
    private String apellido2;

    @NotNull(message = "{msg.valid.not.null}")
    @NotBlank(message = "{msg.valid.not.blank}")
    @Size(max=50, message = "{msg.valid.max}")
    private String ciudad;

    @Range(min = 100, max = 1000, message = "{msg.valid.range}")
    private int categoria;

    @Email(regexp = "\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b")
    private String email;





}

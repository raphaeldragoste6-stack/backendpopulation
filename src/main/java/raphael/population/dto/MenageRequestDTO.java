package raphael.population.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenageRequestDTO {

    @NotBlank(message = "Le nom du chef de ménage est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caractères")
    private String nomChefMenage;

    @NotBlank(message = "La zone est obligatoire")
    private String zone;

    @NotNull(message = "L'âge moyen est obligatoire")
    @Min(value = 0, message = "L'âge moyen ne peut pas être négatif")
    @Max(value = 120, message = "L'âge moyen doit être réaliste (maximum 120)")
    private Integer ageMoyen;

    @NotNull(message = "Le nombre de membres est obligatoire")
    @Min(value = 1, message = "Un ménage compte au moins 1 personne")
    @Max(value = 30, message = "Le nombre de personnes maximum est 30")
    private Integer nombrePersonnes;

    @NotBlank(message = "Le type de logement est obligatoire")
    @Size(min = 2, max = 50, message = "Le type de logement doit contenir entre 2 et 50 caractères")
    private String typeLogement;
}
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




    //verifie  ke le champ nest pas vide(stricte)   NotBlank (pour le texte)
    @NotBlank(message = "Le nom du chef de ménage est aubligatoire")
//elle limite le nombre de caractères autorisé pour ce texte
    @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100  caractères")
//Elle configure la colonne correspondante dans la table de ta base de données PostgreSQL

    private String nomChefMenage;



    //
    @NotBlank( message = "La zone est aubligatoire")
//empeche d'enregistrer une valeur null directement en BDD(nullable = false)

    private String zone;




    //pour le champ age moyen
    @NotNull( message = "L'age moyen est obligatoire")
    @Min(value = 0, message = "L'age moyen ne peut pas etre negatif")
    @Max(value = 120, message = "L'age moye doit reliste (maximum 120)")

    private Integer ageMoyen;



    //pour le champ nombre personne       ____//verifie  ke le champ nest pas vide(stricte)   NotNull (pour les chiffre)
    @NotNull( message = "Le nombre de membre est obligatoire")
    @Min(value = 1, message = "Un menage compte au moins 1 personne")
    @Max(value = 30, message = "le nombre de personne maximum (30)")

    private Integer nombrePersonnes;


    //pour le champ type logement
    @NotBlank( message = "Le type de logement est obligatoire")
    @Size(min = 2, max = 50, message = "Le type d logement doit contenir entre 2 et 50 caractères")

    private String typeLogement;



    //L'annotation @PositiveOrZero sert à vérifier qu'une valeur numérique est supérieure ou égale à zéro ($\ge 0$).
    @PositiveOrZero ( message = "Le revenu mensuel doit etre superieur ou égal à 0")

    private Double revenuMensuel;








}

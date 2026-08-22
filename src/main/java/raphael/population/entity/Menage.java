package raphael.population.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name = "menages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Menage {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;



//verifie  ke le champ nest pas vide(stricte)   NotBlank (pour le texte)
@NotBlank(message = "Le nom du chef de ménage est aubligatoire")
//elle limite le nombre de caractères autorisé pour ce texte
@Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100  caractères")
//Elle configure la colonne correspondante dans la table de ta base de données PostgreSQL
@Column(name = "nom_chef_menage", nullable = false)
private String nomChefMenage;



//
@NotBlank( message = "La zone est aubligatoire")
//empeche d'enregistrer une valeur null directement en BDD(nullable = false)
@Column(name = "zone", nullable = false)
private String zone;




//pour le champ age moyen
@NotNull( message = "L'age moyen est obligatoire")
@Min(value = 0, message = "L'age moyen ne peut pas etre negatif")
@Max(value = 120, message = "L'age moye doit reliste (maximum 120)")
@Column(name = "age_moyen", nullable = false )
private Integer ageMoyen;



//pour le champ nombre personne       ____//verifie  ke le champ nest pas vide(stricte)   NotNull (pour les chiffre)
@NotNull( message = "Le nombre de membre est obligatoire")
@Min(value = 1, message = "Un menage compte au moins 1 personne")
@Max(value = 30, message = "le nombre de personne maximum (30)")
@Column(name = "nombre_personnes", nullable = false )
private Integer nombrePersonnes;


//pour le champ type logement
@NotBlank( message = "Le type de logement est obligatoire")
@Size(min = 2, max = 50, message = "Le type d logement doit contenir entre 2 et 50 caractères")
@Column(name = "type_logement", nullable = false )
private String typeLogement;



//L'annotation @PositiveOrZero sert à vérifier qu'une valeur numérique est supérieure ou égale à zéro ($\ge 0$).
@PositiveOrZero ( message = "Le revenu mensuel doit etre superieur ou égal à 0")
@Column(name = "revenu_mensuel")
private Double revenuMensuel;



//---Dates AUTOMATIQUES-----

    //creation date et heure
@CreationTimestamp
@Column(name ="created_at", nullable = false , updatable = false)
private LocalDateTime createdAt;   // Ne change JAMAIS après la création


    //Elle sert uniquement à enregistrer automatiquement la date et l'heure de la dernière modification effectuée sur le ménage.
@UpdateTimestamp
@Column(name = "updated_at")
private LocalDateTime updatedAt;   //  Se met à jour à CHAQUE modification



}

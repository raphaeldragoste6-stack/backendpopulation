package raphael.population.service;

import raphael.population.dto.MenageRequestDTO;
import raphael.population.dto.MenageResponseDTO;
import raphael.population.dto.StatistiquesDTO;

import java.util.List;

public interface MenageService {


    MenageResponseDTO creerMenage(MenageRequestDTO requestDTO);
    List<MenageResponseDTO> obtenirTousLesMenages();
    MenageResponseDTO obtenirMenageParId(Long id);
    MenageResponseDTO mettreAJourMenage(Long id, MenageRequestDTO requestDTO);
    void supprimerMenage(Long id);









    // Nouvelle méthode pour les 8 statistiques
    StatistiquesDTO obtenirStatistiques();



}

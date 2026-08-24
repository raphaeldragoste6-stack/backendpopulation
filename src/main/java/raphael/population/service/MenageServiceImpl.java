package raphael.population.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import raphael.population.dto.MenageRequestDTO;
import raphael.population.dto.MenageResponseDTO;
import raphael.population.dto.StatistiquesDTO;
import raphael.population.entity.Menage;
import raphael.population.mapper.MenageMapper;
import raphael.population.repository.MenageRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenageServiceImpl implements MenageService {
    private final MenageRepository menageRepository;
    private final MenageMapper menageMapper;

    @Override
    public MenageResponseDTO creerMenage(MenageRequestDTO requestDTO) {
        Menage menage = menageMapper.toEntity(requestDTO);
        Menage menageSauvegarde = menageRepository.save(menage);
        return menageMapper.toDto(menageSauvegarde);
    }

    @Override
    public List<MenageResponseDTO> obtenirTousLesMenages() {
        return menageRepository.findAll()
                .stream()
                .map(menageMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MenageResponseDTO obtenirMenageParId(Long id) {
        Menage menage = menageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ménage non trouvé avec l'ID : " + id));
        return menageMapper.toDto(menage);
    }

    @Override
    public MenageResponseDTO mettreAJourMenage(Long id, MenageRequestDTO requestDTO) {
        Menage menageExistant = menageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ménage non trouvé avec l'ID : " + id));

        menageExistant.setNomChefMenage(requestDTO.getNomChefMenage());
        menageExistant.setZone(requestDTO.getZone());
        menageExistant.setAgeMoyen(requestDTO.getAgeMoyen());
        menageExistant.setNombrePersonnes(requestDTO.getNombrePersonnes());
        menageExistant.setTypeLogement(requestDTO.getTypeLogement());


        Menage menageMisAJour = menageRepository.save(menageExistant);
        return menageMapper.toDto(menageMisAJour);
    }

    @Override
    public void supprimerMenage(Long id) {
        if (!menageRepository.existsById(id)) {
            throw new RuntimeException("Ménage non trouvé avec l'ID : " + id);
        }
        menageRepository.deleteById(id);
    }


//ajout pour calcul statistique

    @Override
    public StatistiquesDTO obtenirStatistiques() {
        List<Menage> menages = menageRepository.findAll();

        if (menages.isEmpty()) {
            return StatistiquesDTO.builder()
                    .totalMenages(0L)
                    .totalPopulation(0L)
                    .tailleMoyenneMenage(0.0)
                    .ageMoyenGlobal(0.0)
                    .revenuMoyen(0.0)
                    .repartitionParZone(Map.of())
                    .repartitionParTypeLogement(Map.of())
                    .menagesFaibleRevenu(0L)
                    .build();
        }

        long totalMenages = menages.size();
        long totalPopulation = menages.stream().mapToLong(Menage::getNombrePersonnes).sum();
        double tailleMoyenne = (double) totalPopulation / totalMenages;
        double ageMoyenGlobal = menages.stream().mapToInt(Menage::getAgeMoyen).average().orElse(0.0);


        Map<String, Long> repartitionZone = menages.stream()
                .collect(Collectors.groupingBy(Menage::getZone, Collectors.counting()));

        Map<String, Long> repartitionLogement = menages.stream()
                .collect(Collectors.groupingBy(Menage::getTypeLogement, Collectors.counting()));

        // Seuil exemple : ménages avec un revenu inférieur à 100 000 FCFA
        long faibleRevenu = menages.stream()

                .count();

        return StatistiquesDTO.builder()
                .totalMenages(totalMenages)
                .totalPopulation(totalPopulation)
                .tailleMoyenneMenage(Math.round(tailleMoyenne * 100.0) / 100.0)
                .ageMoyenGlobal(Math.round(ageMoyenGlobal * 100.0) / 100.0)

                .repartitionParZone(repartitionZone)
                .repartitionParTypeLogement(repartitionLogement)
                .menagesFaibleRevenu(faibleRevenu)
                .build();
    }




}

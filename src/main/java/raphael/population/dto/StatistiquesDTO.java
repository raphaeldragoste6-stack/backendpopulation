package raphael.population.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class StatistiquesDTO {




    private Long totalMenages;
    private Long totalPopulation;
    private Double tailleMoyenneMenage;
    private Double ageMoyenGlobal;
    private Double revenuMoyen;
    private Map<String, Long> repartitionParZone;
    private Map<String, Long> repartitionParTypeLogement;
    private Long menagesFaibleRevenu;







}

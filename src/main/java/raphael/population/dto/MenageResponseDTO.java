package raphael.population.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenageResponseDTO {

    private Long id;
    private String nomChefMenage;
    private String zone;
    private Integer ageMoyen;
    private Integer nombrePersonnes;
    private String typeLogement;
    private Double revenuMensuel;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;






}

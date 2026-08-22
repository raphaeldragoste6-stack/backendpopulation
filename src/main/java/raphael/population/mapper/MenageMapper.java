package raphael.population.mapper;


import org.springframework.stereotype.Component;
import raphael.population.dto.MenageRequestDTO;
import raphael.population.dto.MenageResponseDTO;
import raphael.population.entity.Menage;

@Component
public class MenageMapper {

// Transformer le RequestDTO reçu du Frontend en Entité JPA

public Menage toEntity(MenageRequestDTO dto){

    if (dto == null) return null;

    return Menage.builder()
            .nomChefMenage(dto.getNomChefMenage())
            .zone(dto.getZone())
            .ageMoyen(dto.getAgeMoyen())
            .nombrePersonnes(dto.getNombrePersonnes())
            .typeLogement(dto.getTypeLogement())
            .revenuMensuel(dto.getRevenuMensuel())
            .build();

}



// Transformer l'Entité JPA en ResponseDTO pour le Frontend

    public MenageResponseDTO toDto(Menage entity){

        if (entity == null) return null;

        return MenageResponseDTO.builder()
                .id(entity.getId())
                .nomChefMenage(entity.getNomChefMenage())
                .zone(entity.getZone())
                .ageMoyen(entity.getAgeMoyen())
                .nombrePersonnes(entity.getNombrePersonnes())
                .typeLogement(entity.getTypeLogement())
                .revenuMensuel(entity.getRevenuMensuel())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();

    }



}

package raphael.population.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raphael.population.dto.MenageRequestDTO;
import raphael.population.dto.MenageResponseDTO;
import raphael.population.dto.StatistiquesDTO;
import raphael.population.service.MenageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menages")

//Autorise le backend à recevoir les reque
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS})



public class MenageController {

    private final MenageService menageService;


    @PostMapping
    public ResponseEntity<MenageResponseDTO> creerMenage(@Valid @RequestBody MenageRequestDTO requestDTO) {
        MenageResponseDTO nouveauMenage = menageService.creerMenage(requestDTO);
        return new ResponseEntity<>(nouveauMenage, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MenageResponseDTO>> obtenirTousLesMenages() {
        List<MenageResponseDTO> menages = menageService.obtenirTousLesMenages();
        return ResponseEntity.ok(menages);
    }



    //ajout de @Get pour calculs statistiques      __placé tjr au dessous de /{/id}
    @GetMapping("/stats")
    public ResponseEntity<StatistiquesDTO> obtenirStatistiques() {
        StatistiquesDTO stats = menageService.obtenirStatistiques();
        return ResponseEntity.ok(stats);
    }










    @GetMapping("/{id}")
    public ResponseEntity<MenageResponseDTO> obtenirMenageParId(@PathVariable Long id) {
        MenageResponseDTO menage = menageService.obtenirMenageParId(id);
        return ResponseEntity.ok(menage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenageResponseDTO> mettreAJourMenage(
            @PathVariable Long id,
            @Valid @RequestBody MenageRequestDTO requestDTO) {
        MenageResponseDTO menageMisAJour = menageService.mettreAJourMenage(id, requestDTO);
        return ResponseEntity.ok(menageMisAJour);
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerMenage(@PathVariable Long id) {
        menageService.supprimerMenage(id);
        return ResponseEntity.noContent().build();
    }









}

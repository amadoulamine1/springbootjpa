package sn.openit.foot.rest;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import sn.openit.foot.Joueur;
import sn.openit.foot.JoueurList;

import java.util.List;

@Tag(name = "Equipe Foot Joueurs API")
@RestController
@RequestMapping("/joueurs")
public class JoueurController {
    @Operation(summary = "Finds joueurs", description = "Finds joueurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Joueurs list",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Joueur.class)))})
    })
    @GetMapping
    public List<Joueur> list() {
        return JoueurList.ALL;
    }


    @Operation(summary = "Finds a joueur", description = "Finds a joueur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Joueur",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Joueur.class))}),
            @ApiResponse(responseCode = "404", description = "Joueur not found")

    })
    @GetMapping("{lastName}")
    public Joueur getByLastName(@PathVariable("lastName") String lastName) {
        return JoueurList.ALL.stream()
                .filter(joueur -> joueur.getLastName().equals(lastName))
                .findFirst()
                .orElseThrow();
    }


    @Operation(summary = "Creates a joueur", description = "Creates a joueur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created joueur",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Joueur.class))})
    })
    @PostMapping
    public Joueur createJoueur(@Valid @RequestBody Joueur joueur) {
        return joueur;
    }

    @Operation(summary = "Updates a joueur", description = "Updates a joueur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated joueur",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = Joueur.class))})
        })
    @PutMapping
    public Joueur updateJoueur(@Valid @RequestBody Joueur joueur) {
        return joueur;
    }

    @Operation(summary = "Deletes a joueur", description = "Deletes a joueur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Joueur has been deleted")
    })
    @DeleteMapping("{lastName}")
    public void deleteJoueurByLastName(@PathVariable("lastName") String lastName) {
    }
}
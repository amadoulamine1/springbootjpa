package sn.openit.foot.rest;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sn.openit.foot.Joueur;
import sn.openit.foot.JoueurToSave;
import sn.openit.foot.service.JoueurService;

import java.util.List;

@Tag(name = "Equipe Foot Joueurs API")
@RestController
@RequestMapping("/joueurs")
public class JoueurController {

    @Autowired
    private JoueurService joueurService;

    @Operation(summary = "Finds joueurs", description = "Finds joueurs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Joueurs list",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Joueur.class)))})
    })
    @GetMapping
    public List<Joueur> list() {
        return joueurService.getAllJoueurs();
    }


    @Operation(summary = "Finds a joueur", description = "Finds a joueur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Joueur",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Joueur.class))
                    }),
            @ApiResponse(responseCode = "404", description = "A joueur with the specified last name was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class))
                    })
    })
    @GetMapping("{lastName}")
    public Joueur getByLastName(@PathVariable("lastName") String lastName) {
        return joueurService.getByLastName(lastName);
    }


    @Operation(summary = "Creates a joueur", description = "Creates a joueur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created joueur",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Joueur.class))}),
            @ApiResponse(responseCode = "400", description = "Joueur with specified last name already exists.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class))
                    })
    })
    @PostMapping
    public Joueur createJoueur(@Valid @RequestBody JoueurToSave joueurToSave) {
        return joueurService.create(joueurToSave);
    }

    @Operation(summary = "Updates a joueur", description = "Updates a joueur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated joueur",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = JoueurToSave.class))}),
            @ApiResponse(responseCode = "404", description = "A joueur with the specified last name was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class))
                    })
    })
    @PutMapping
    public Joueur updateJoueur(@Valid @RequestBody JoueurToSave joueurToSave) {
        return joueurService.update(joueurToSave);
    }


    /*@Operation(summary = "Creates a joueur", description = "Creates a joueur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created joueur",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = JoueurToSave.class))})
    })
    @PostMapping
    public Joueur createJoueur(@RequestBody @Valid JoueurToSave joueurToSave) {
        return joueurService.create(joueurToSave);
    }*/

    @Operation(summary = "Deletes a joueur", description = "Deletes a joueur")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Joueur has been deleted"), @ApiResponse(responseCode = "404", description = "Joueur with specified last name was not found.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))})})
    @DeleteMapping("{lastName}")
    public void deleteJoueurByLastName(@PathVariable("lastName") String lastName) {
        joueurService.delete(lastName);
    }
}
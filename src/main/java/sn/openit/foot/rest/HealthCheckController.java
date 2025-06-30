package sn.openit.foot.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.openit.foot.HealthCheck;
import sn.openit.foot.service.HealthCheckService;

/**
 * Health check controller to verify that the application is running.
 */
@Tag(name = "Health Check API", description = "Endpoints to check the health of the application")
@RestController
public class HealthCheckController {

    @Autowired
    private HealthCheckService healthCheckService;

    /**
     * Endpoint to check the health of the application.
     *
     * @return a simple message indicating that the application is running.
     */
   /** @ Get Mapping ("/health")
    public String healthCheck2() {
        return "Application is running";
    }*/

    @Operation(summary = "Return appplication status", description = "Returns the current status of the application")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Healthcheck status with some details",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = HealthCheck.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "503", description = "Application is not fully functional",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = HealthCheck.class)
                            )
                    })
    })
   @GetMapping("/healthcheck")
    public HealthCheck healthCheck() {
        return healthCheckService.getApplicationStatus();
    }
}
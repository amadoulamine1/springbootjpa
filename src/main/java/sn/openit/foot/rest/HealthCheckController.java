package sn.openit.foot.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.openit.foot.HealthCheck;
import sn.openit.foot.service.HealthCheckService;

/**
 * Health check controller to verify that the application is running.
 */
@RestController
public class HealthCheckController {

    @Autowired
    private HealthCheckService healthCheckService;

    /**
     * Endpoint to check the health of the application.
     *
     * @return a simple message indicating that the application is running.
     */
    @GetMapping("/health")
    public String healthCheck2() {
        return "Application is running";
    }

    @GetMapping("healthcheck")
    public HealthCheck healthCheck() {
        return healthCheckService.getApplicationStatus();
    }
}
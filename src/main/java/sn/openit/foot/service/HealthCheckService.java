package sn.openit.foot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.openit.foot.ApplicationStatus;
import sn.openit.foot.HealthCheck;
import sn.openit.foot.repository.HealthCheckRepository;

@Service
public class HealthCheckService {

    @Autowired
    private HealthCheckRepository healthCheckRepository;


    public HealthCheck getApplicationStatus() {
        Long applicationConnections = healthCheckRepository.countApplicationConnections();
        if (applicationConnections > 0) {
            return new HealthCheck(ApplicationStatus.OK, "Welcome to Dyma Tennis");
        } else {
            return new HealthCheck(ApplicationStatus.KO, "Dyma is not fully fonctional, please try again later");
        }
    }
}

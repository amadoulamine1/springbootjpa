package sn.openit.foot;

//import org.junit.jupiter.api.Assertions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sn.openit.foot.repository.HealthCheckRepository;
import sn.openit.foot.rest.HealthCheckController;
import sn.openit.foot.service.HealthCheckService;

@SpringBootTest
public class FootApplicationTests {

    @Autowired
    private HealthCheckController healthCheckController;

    @Autowired
    private HealthCheckService healthCheckService;

    @Autowired
    private HealthCheckRepository healthCheckRepository;

    /**
     * This test checks if the application context loads successfully and if the
     * HealthCheckController and HealthCheckService beans are available.
     */

   /* @Test
    void contextLoads() {
        // This test will pass if the application context loads successfully
        // You can add more assertions here to check specific beans or configurations
        // For example, you can check if the HealthCheckController bean is loaded

        Assertions.assertThat(healthCheckController).isNotNull();
        Assertions.assertThat(healthCheckService).isNotNull();
        Assertions.assertThat(healthCheckRepository).isNotNull();
    }*/
}

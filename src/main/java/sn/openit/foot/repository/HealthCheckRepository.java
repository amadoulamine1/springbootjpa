package sn.openit.foot.repository;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class HealthCheckRepository {

    @Autowired
    private EntityManager entityManager;

    public Long countApplicationConnections() {
        String applicationsConnectionsQuery = "SELECT COUNT(*) FROM pg_stat_activity WHERE application_name = 'PostgreSQL JDBC Driver'";
       return (Long) entityManager.createNativeQuery(applicationsConnectionsQuery)
                .getSingleResult();
    }
}

package sn.openit.foot;

public class HealthCheck {
    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    private ApplicationStatus status;
    private String message ="String";

    public HealthCheck(ApplicationStatus status, String message) {
        this.status = status;
        this.message = message;
    }

}

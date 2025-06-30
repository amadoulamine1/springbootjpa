package sn.openit.foot;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class Rank {

    @Positive
    private int position;
    @PositiveOrZero
    private int points;

    public Rank(int position, int points) {
        this.position = position;
        this.points = points;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}

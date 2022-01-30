package org.WebUiTests.lesson4;

public class Box {
    private Integer ballsCounter;

    public Box() {
        ballsCounter = 0;
    }

    public Integer getBallsCounter() {
        return ballsCounter;
    }

    public void addBall() {
        ballsCounter++;
    }

    public void removeBall() {
        if (ballsCounter == 0) {
            throw new NullPointerException("Уже 0 мячей!!!");
        }
        ballsCounter--;
    }

    public void shuffleBalls() throws EmptyBoxException {
        if (ballsCounter == 0) {
            throw new EmptyBoxException();
        }
    }
}

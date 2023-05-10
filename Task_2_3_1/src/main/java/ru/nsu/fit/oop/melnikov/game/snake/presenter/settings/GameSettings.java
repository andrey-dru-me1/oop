package ru.nsu.fit.oop.melnikov.game.snake.presenter.settings;

public class GameSettings {

    private int tickDelay;

    public GameSettings() {
        tickDelay = 100;
    }

    public int getTickDelay() {
        return tickDelay;
    }

    public void setTickDelay(int tickDelay) {
        this.tickDelay = tickDelay;
    }
}

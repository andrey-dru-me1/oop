package ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.cell.objects;

import java.util.Optional;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;
import javafx.util.Pair;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.Rect;
import ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.cell.CellObjectDTO;

public class AppleDTO extends CellObjectDTO {

    public AppleDTO(String texturePackPath) {
        super(texturePackPath);
    }

    @Override
    protected String getImageName() {
        return "apple.png";
    }

    @Override
    public Optional<Pair<AnimationTimer, Timeline>> draw(GraphicsContext context, Rect<Double> rect) {
        DoubleProperty y = new SimpleDoubleProperty(-rect.height());

    AnimationTimer timer =
        new AnimationTimer() {
          @Override
          public void handle(long now) {
            drawCanvas(y.get());
          }

          private void drawCanvas(double y) {
            context.drawImage(image, rect.x(), y, rect.width(), rect.height());
          }

          @Override
          public void stop() {
            drawCanvas(rect.y());
            super.stop();
          }
        };

        Timeline timeline =
                new Timeline(
                        new KeyFrame(Duration.millis(0), new KeyValue(y, -rect.height())),
                        new KeyFrame(Duration.millis(200), new KeyValue(y, rect.y())));

        timer.start();
        timeline.play();
        return Optional.of(new Pair<>(timer, timeline));
    }

}

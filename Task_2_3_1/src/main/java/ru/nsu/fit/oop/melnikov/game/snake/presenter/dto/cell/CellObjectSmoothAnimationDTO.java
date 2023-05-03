package ru.nsu.fit.oop.melnikov.game.snake.presenter.dto.cell;

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

public abstract class CellObjectSmoothAnimationDTO extends CellObjectDTO {


    protected CellObjectSmoothAnimationDTO(String texturePackPath) {
        super(texturePackPath);
    }

//    @Override
//    public Optional<Pair<AnimationTimer, Timeline>> draw(GraphicsContext context, Rect<Double> rect) {
//        DoubleProperty alpha = new SimpleDoubleProperty(0.0);
//
//        AnimationTimer timer =
//                new AnimationTimer() {
//                    @Override
//                    public void handle(long now) {
//                        drawCanvas(alpha.get());
//                    }
//
//                    private void drawCanvas(double alpha) {
//                        context.setGlobalAlpha(alpha);
//                        context.drawImage(image, rect.x(), rect.y(), rect.width(), rect.height());
//                    }
//
//                    @Override
//                    public void stop() {
//                        drawCanvas(1.0);
//                        super.stop();
//                    }
//                };
//
//        Timeline timeline =
//                new Timeline(
//                        new KeyFrame(Duration.millis(0), new KeyValue(alpha, 0.0)),
//                        new KeyFrame(Duration.millis(400), new KeyValue(alpha, 1.0)));
//
//        timer.start();
//        timeline.play();
//        return Optional.of(new Pair<>(timer, timeline));
//    }

}

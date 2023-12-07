package pl.psi.gui;

import pl.psi.GameEngine;
import pl.psi.Hero;
import pl.psi.Point;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import pl.psi.creatures.Creature;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Optional;
import pl.psi.gui.Start;

public class MainBattleController implements PropertyChangeListener {
    private final GameEngine gameEngine;
    @FXML
    private GridPane gridMap;
    @FXML
    private Button passButton;
    @FXML
    private Button windowButton;

    public MainBattleController(final Hero aHero1, final Hero aHero2) {
        gameEngine = new GameEngine(aHero1, aHero2);
    }

    @FXML
    private void initialize() {
        refreshGui();
        gameEngine.addObserver(this);
        passButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> gameEngine.pass());
        windowButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> Start.setNewScene());
    }



    private void refreshGui() {
        gridMap.getChildren()
                .clear();
        for (int x = 0; x < 15; x++) {
            for (int y = 0; y < 10; y++) {
                Point currentPoint = new Point(x, y);
                Optional<Creature> creature = gameEngine.getCreature(currentPoint);
                final MapTile mapTile = new MapTile();
                creature.ifPresent(mapTile::setCreature);
                if (gameEngine.isCurrentCreature(currentPoint)) {
                    mapTile.setBackground(Color.GREENYELLOW);
                }
                if (gameEngine.canMove(currentPoint)) {
                    mapTile.setBackground(Color.GREY);
                    mapTile.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                        gameEngine.move(currentPoint);
                    });
                }
                if (gameEngine.canAttack(currentPoint)) {
                    mapTile.setBackground(Color.RED);
                    mapTile.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                        gameEngine.attack(currentPoint);
                    });
                }
                gridMap.add(mapTile, x, y);
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        refreshGui();
    }
}

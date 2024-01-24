package pl.psi.gui;

import javafx.geometry.Insets;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
import javafx.scene.paint.Color;
import pl.psi.creatures.Creature;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import pl.psi.gui.Start;
import pl.psi.spells.Spell;

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
        windowButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> setNewScene());
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
                if( gameEngine.getCurrentSpell()!=null  ) {
                    if(gameEngine.getCreature( currentPoint ).isPresent()){

                        mapTile.setBackground( Color.ORANGE );
                        mapTile.addEventHandler( MouseEvent.MOUSE_CLICKED, ( e ) -> {
                            gameEngine.castSpell( currentPoint );
                            gameEngine.setCurrentSpell(null);
                            refreshGui();

                        } );
                    }
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


    private void setNewScene() {
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.APPLICATION_MODAL);

        List<Spell> spellBook = gameEngine.getSpellBook();
        List<Button> spellButtonList = new ArrayList<>();
        GridPane spellBookGrid = new GridPane();
        spellBookGrid.setMinSize(350, 200);
        spellBookGrid.setPadding(new Insets(58, 10, 10, 51));
        spellBookGrid.setVgap(5);
        spellBookGrid.setHgap(5);
        for (Spell spell : spellBook) {
            Button spellButton = new Button(spell.getName());
            try {
                Image image = new Image("spells/" + spell.getName() + ".png");
                ImageView imageView = new ImageView(image);

                spellButton.setGraphic(imageView);
                spellButton.setContentDisplay(ContentDisplay.TOP);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }
            spellButton.setOnAction(e -> {
                primaryStage.close();
                gameEngine.setCurrentSpell(spell);
                refreshGui();
            });

            spellButtonList.add(spellButton);

        }

        int gridWidth = (int) Math.ceil(Math.sqrt(spellButtonList.size()));
        int counter = 0;

        for (int i = 0; i < gridWidth; i++) {
            for (int k = 0; k < gridWidth; k++) {
                if (counter < spellButtonList.size()) {
                    spellBookGrid.add(spellButtonList.get(counter), k, i);
                    counter++;
                }
            }
        }
        Image backgroundImage = new Image("/spells/book.png");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImageObj = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        Background background = new Background(backgroundImageObj);
        spellBookGrid.setBackground(background);
        Scene scene1 = new Scene(spellBookGrid, 500, 500);

        primaryStage.setScene(scene1);
        primaryStage.showAndWait();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        refreshGui();
    }
}

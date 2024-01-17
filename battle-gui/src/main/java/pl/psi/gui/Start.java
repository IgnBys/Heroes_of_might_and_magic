package pl.psi.gui;

import java.io.IOException;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import pl.psi.GameEngine;
import pl.psi.Hero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import pl.psi.creatures.NecropolisFactory;
import pl.psi.spells.*;

public class Start extends Application {

    public Start() {

    }

    static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        Scene scene, scene1 = null;

        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Start.class.getClassLoader()
                    .getResource("fxml/main-battle.fxml"));
            loader.setController(new MainBattleController(createP1(), createP2()));
            scene = new Scene(loader.load());

            primaryStage.setScene(scene);
            primaryStage.setX(5);
            primaryStage.setY(5);
            primaryStage.show();
        } catch (final IOException aE) {
            aE.printStackTrace();
        }
    }

    private Hero createP2() {
        final Hero ret = new Hero(List.of(
                new NecropolisFactory().create(true, 1, 100)),
                List.of(new Spell.Builder().statistic(SpellStatistic.DISRUPTING_RAY).build(),
                        new Spell.Builder().statistic(SpellStatistic.BLOODLUST).build(),
                        new Spell.Builder().statistic(SpellStatistic.SHIELD).build(),
                        new Spell.Builder().statistic(SpellStatistic.LIGHTNING_BOLT).build(),
                        new Spell.Builder().statistic(SpellStatistic.STONE_SKIN).build()
                ));
        return ret;
    }

    private Hero createP1() {
        final Hero ret = new Hero(List.of(new NecropolisFactory().create(false, 1, 5)),
                List.of(new Spell.Builder().statistic(SpellStatistic.SHIELD).build(),
                        new Spell.Builder().statistic(SpellStatistic.LIGHTNING_BOLT).build(),
                        new Spell.Builder().statistic(SpellStatistic.STONE_SKIN).build()));
        return ret;
    }

}

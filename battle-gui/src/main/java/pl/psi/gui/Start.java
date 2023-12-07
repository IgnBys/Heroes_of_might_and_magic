package pl.psi.gui;

import java.io.IOException;
import java.util.List;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import pl.psi.Hero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import pl.psi.creatures.NecropolisFactory;

public class Start extends Application
{

    public Start()
    {

    }

    static void main( final String[] args )
    {
        launch( args );
    }

    @Override
    public void start( final Stage primaryStage )
    {
        Scene scene, scene1 = null;

        try
        {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation( Start.class.getClassLoader()
                .getResource( "fxml/main-battle.fxml" ) );
            loader.setController( new MainBattleController( createP1(), createP2() ) );
            scene = new Scene( loader.load() );

            primaryStage.setScene( scene );
            primaryStage.setX( 5 );
            primaryStage.setY( 5 );
            primaryStage.show();
        }
        catch( final IOException aE )
        {
            aE.printStackTrace();
        }
    }

    public static void setNewScene(){
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation( Start.class.getClassLoader()
                .getResource( "fxml/SpellWindow.fxml" ) );

        try {
//            Stage primaryStage = new Stage();
//            Scene scene1 = null;
//            Label label = new Label("Welcome to scene1");
//            Button button1 = new Button("Back to scene");
//            button1.setOnAction(e -> primaryStage.close());
//            VBox layout = new VBox(20);
//            layout.getChildren().addAll(label, button1);
//            AnchorPane anchorPane = loader.load();
//            anchorPane.setStyle("-fx-background-image: url('https://upload.wikimedia.org/wikibooks/ru/thumb/7/73/Heroes-of-Might-Magic-III-spell-book.jpg/300px-Heroes-of-Might-Magic-III-spell-book.jpg');");
//            scene1 = new Scene(layout, 500, 500);
//            primaryStage.setScene(scene1);
//            primaryStage.show();
            AnchorPane anchorPane = loader.load();
            anchorPane.setStyle("-fx-background-image: url('https://upload.wikimedia.org/wikibooks/ru/thumb/7/73/Heroes-of-Might-Magic-III-spell-book.jpg/300px-Heroes-of-Might-Magic-III-spell-book.jpg');");
            Scene scene1 = new Scene(anchorPane, 500, 500);
            Stage primaryStage = new Stage();
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            Button button1 = (Button) anchorPane.lookup("#button1");
            button1.setOnAction(e ->primaryStage.close());
            primaryStage.setScene(scene1);
            primaryStage.showAndWait();
        }   catch (IOException e) {
            e.printStackTrace();
        }



    }
    private Hero createP2()
    {
        final Hero ret = new Hero( List.of( new NecropolisFactory().create( true, 1, 100 ) ) );
        return ret;
    }

    private Hero createP1()
    {
        final Hero ret = new Hero( List.of( new NecropolisFactory().create( false, 1, 5 ) ) );
        return ret;
    }

}

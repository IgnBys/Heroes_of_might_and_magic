package pl.psi.gui;

import java.io.IOException;
import java.util.List;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
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
            GridPane gridPane = loader.load();
            Stage primaryStage = new Stage();
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            Button button1 = (Button) gridPane.lookup("#button1");
            Button button2 = (Button) gridPane.lookup("#button2");
            Button button3 = (Button) gridPane.lookup("#button3");
            Button button4 = (Button) gridPane.lookup("#button4");
//            GridPane.setRowIndex(button1, 0);
//            GridPane.setColumnIndex(button1, 0);
//            GridPane.setRowIndex(button2, 0);
//            GridPane.setColumnIndex(button2, 1);
            button1.setOnAction(e ->primaryStage.close());
            button2.setOnAction(e ->primaryStage.close());
            button3.setOnAction(e ->primaryStage.close());
            button4.setOnAction(e ->primaryStage.close());
            Scene scene1 = new Scene(gridPane, 500, 500);

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

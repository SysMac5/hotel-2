package hi.throunhugbunadar.frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        //LoginView c1 = loader.getController();

        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("search.fxml"));
        Parent root2 = loader2.load();
        SearchView c2 = loader2.getController();

        //stillaController
        //c1.setTenging()



        nyrGluggi(primaryStage, root);
    }

    /**
     * Birtir nýja senu sem er í root í glugganum s með titlinum "Innskráning"
     * @param s glugginn
     * @param root senan (viðmótstréð)
     */
    private void nyrGluggi(Stage s, Parent root) {
        s.setTitle("Innskráning");
        Scene s1 = new Scene(root, 400, 200);
        s.setScene(s1);
        s.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

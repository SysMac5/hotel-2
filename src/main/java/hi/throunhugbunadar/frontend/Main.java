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
        Parent root1 = loader.load();
        LoginView v1 = loader.getController();

        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("search.fxml"));
        Parent root2 = loader2.load();
        SearchView v2 = loader2.getController();

        stillaView(v1, v2);

        nyrGluggi(primaryStage, root1);

        new Scene(root2, 600, 320);
    }

    /**
     * Tengir view viðmótstrjánna saman.
     * @param v1 view fyrir innskráningu
     * @param v2 view fyrir leitarviðmót
     */
    private void stillaView(LoginView v1, SearchView v2) {
        v1.setTenging(v2);
        v2.setTenging(v1);
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

import controller.MainMenuController;
import controller.NewOrderController;
import controller.ViewManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    /**
     * TODO: Is the Title of the Stage correct? - patsy
     * TODO: Might be a better way to decouple ViewManager and Controller classes - patsy
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        MainMenuController mmc = new MainMenuController();
        NewOrderController noc = new NewOrderController();

        ViewManager vm = new ViewManager(mmc);
        vm.addController(noc);

        mmc.setViewManager(vm);

        primaryStage.setTitle("Tony Joe's POS System");
        primaryStage.setScene(vm.getScene());
        primaryStage.show();
    }
}
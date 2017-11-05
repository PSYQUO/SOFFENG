import controller.MainMenuController;
import controller.NewOrderController;
import controller.ViewManager;
import javafx.application.Application;
import javafx.stage.Stage;
import model.DBConnection;

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
        // Setup DBConnection
        DBConnection dbc = DBConnection.getInstance();
        dbc.setConnection("tjbbqdb", "root", "1234");

        // Setup Controllers and ViewManager
        MainMenuController mmc = new MainMenuController();
        NewOrderController noc = new NewOrderController();

        ViewManager vm = new ViewManager(mmc);
        vm.addController(noc);

        mmc.setViewManager(vm);
        noc.setViewManager(vm);

        primaryStage.setTitle("Tony Joe's POS System");
        primaryStage.setScene(vm.getScene());
        primaryStage.show();
    }

    @Override
    public void stop()
    {
        DBConnection dbc = DBConnection.getInstance();
        dbc.closeConnection();
    }
}
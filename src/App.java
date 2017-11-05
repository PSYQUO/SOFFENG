import controller.*;
import javafx.application.Application;
import javafx.stage.Stage;
import model.DBConnection;
import model.Transaction;
import model.TransactionBuilder;

public class App extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    /**
     * TODO: Is the Title of the Stage correct? - patsy
                I think it's good - gian
     * TODO: Might be a better way to decouple ViewManager and Controller classes - patsy
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // Setup DBConnection
        DBConnection dbc = DBConnection.getInstance();
        dbc.setConnection("tjbbqdb", "root", "password");

        // Setup Controllers and ViewManager
        MainMenuController mmc = new MainMenuController();
        NewOrderController noc = new NewOrderController();
        InventoryController ic = new InventoryController();
        SettingsController sc = new SettingsController();

        ViewManager vm = new ViewManager(mmc);

        vm.addController(noc);
        noc.setViewManager(vm);

        vm.addController(ic);
        ic.setViewManager(vm);

        vm.addController(sc);
        sc.setViewManager(vm);

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
import controller.*;
import controller.ViewManager.ViewManager;
import javafx.application.Application;
import javafx.stage.Stage;
import model.DBConnection;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

public class App extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    /**
     * TODO: Might be a better way to decouple ViewManager and Controller classes - patsy
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        // Setup DBConnection
        setupDatabaseConfig();

        // Setup Controllers and ViewManager
        MainMenuController mmc = new MainMenuController();
        NewOrderController noc = new NewOrderController();
        InventoryController ic = new InventoryController();
        SettingsController sc = new SettingsController();
        FilesController fc = new FilesController();
        AnalyticsController ac = new AnalyticsController();

        ViewManager vm = new ViewManager(mmc);

        vm.addController(noc);
        noc.setViewManager(vm);

        vm.addController(ic);
        ic.setViewManager(vm);

        vm.addController(sc);
        sc.setViewManager(vm);

        vm.addController(fc);
        fc.setViewManager(vm);

        vm.addController(ac);
        ac.setViewManager(vm);

        primaryStage.setTitle("Tony Joe's POS System");
        primaryStage.setScene(vm.getScene());
        primaryStage.show();
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(800);
        primaryStage.setFullScreen(true);
    }

    private void setupDatabaseConfig()
    {
        String line, database = "", username = "", password = "";

        try
        {
            File file = new File(App.class.getResource("dbconfig.ini").toURI());
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine()) != null)
            {
                if(line.charAt(0) != '#')
                {
                    String[] ini = line.split("=");
                    switch(ini[0].trim())
                    {
                        case "database":
                            database = ini[1].trim();
                            break;
                        case "username":
                            username = ini[1].trim();
                            break;
                        case "password":
                            password = ini[1].trim();
                            break;
                    }
                }
            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        catch(URISyntaxException e)
        {
            e.printStackTrace();
        }

        if(!database.equals("") && !username.equals("") && !password.equals(""))
        {
            DBConnection dbc = DBConnection.getInstance();
            dbc.setConnection(database, username, password);
        }
        else
            System.err.println("Database connection was not set. Check dbconfig.ini at the default package.");
    }

    @Override
    public void stop()
    {
        DBConnection dbc = DBConnection.getInstance();
        dbc.closeConnection();
    }
}
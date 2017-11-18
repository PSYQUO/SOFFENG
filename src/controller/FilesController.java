package controller;

import controller.ViewManager.ViewManagerException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class FilesController extends Controller
{
    @FXML
    private Button buttonBack;

    public FilesController() throws IOException
    {
        initialize(this, "/view/files", "/view/files");
    }

    @Override
    public void load() throws ViewManagerException
    {
        if(checkInitialLoad(getClass().getSimpleName()))
        {
            buttonBack.addEventHandler(ActionEvent.ACTION, e ->
            {
                viewManager.switchViews("MainMenuController");
                clear();
            });
        }
    }

    @Override
    public void clear()
    {

    }
}

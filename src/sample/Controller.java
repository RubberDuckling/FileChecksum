package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements javafx.fxml.Initializable {
    public Button openButton;
    public Label fileLabel;
    public Text checksumText;
    public TextField comparedTextField;
    public Button compareButton;
    public ComboBox chooseHashFunction;
    public Text ifEqualText;
    private Desktop desktop = Desktop.getDesktop();
    private FileChooser fileChooser = new FileChooser();





    public void openFile(ActionEvent actionEvent) {

                    File file = fileChooser.showOpenDialog(new Stage());
                    if(file !=null){
                        fileLabel.setText(file.getName());
                        try {
                            switch (chooseHashFunction.getSelectionModel().getSelectedItem().toString()){
                                case "md5":checksumText.setText(Checksum.getMD5Checksum(file));
                                    break;
                                case "sha-256":checksumText.setText(Checksum.getSHA256Checksum(file));
                                    break;
                                    default:break;

                            }


                            //sha256Text.setText(Checksum.getSHA256Checksum(file));
                        }
                        catch (Exception e){
                            Logger.getLogger(
                                    Controller.class.getName()).log(Level.SEVERE,null,e);

                        }
                        //openFile(file);
                    }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.chooseHashFunction.setItems(FXCollections.observableArrayList("md5", "sha-256"));
    }

    public void compareHash(ActionEvent actionEvent) {
        if(checksumText.getText().trim().equals(comparedTextField.getText().trim()))
            ifEqualText.setText("==");
        else
            ifEqualText.setText("!=");


    }



    /*
    private void openFile(File file) {
        try{
            desktop.open(file);
        } catch (IOException e) {
            Logger.getLogger(
                    Main.class.getName()).log(Level.SEVERE,null,e);
        }
    }
    */


}

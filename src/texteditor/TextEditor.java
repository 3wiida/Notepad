package texteditor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.IndexRange;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author 3wiida
 */
public class TextEditor extends Application {
    //hello 3wiida
    @Override
    public void start(Stage primaryStage) {
        
        TextEditorUI root = new TextEditorUI();
        
        root.newMenuItem.addEventHandler(ActionEvent.ACTION, onNewClicked(root));
        root.openMenuItem.addEventHandler(ActionEvent.ACTION, onOpenClicked(root));
        root.saveMenuItem.addEventHandler(ActionEvent.ACTION, onSaveClicked(root));
        root.exitMenuItem.addEventHandler(ActionEvent.ACTION, onExitClicked());
        
        
        root.copyMenuItem.addEventHandler(ActionEvent.ACTION, onCopyClicked(root));
        root.cutMenuItem.addEventHandler(ActionEvent.ACTION, onCutClicked(root));
        root.pasteMenuItem.addEventHandler(ActionEvent.ACTION, onPasteClicked(root));
        root.deleteMenuItem.addEventHandler(ActionEvent.ACTION,onDeleteClicked(root));
        root.selectAllMenuItem.addEventHandler(ActionEvent.ACTION, onSelectAllClicked(root));
        
        root.aboutMenuItem.addEventHandler(ActionEvent.ACTION,onAboutClicked());
        
        Scene scene = new Scene(root, 1000, 900);
        primaryStage.setTitle("Text Editor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private File openFileChooser(){
        FileChooser chooser = new FileChooser();
        return chooser.showOpenDialog(null);
    }
    
    private File saveFileChooser(){
        FileChooser chooser = new FileChooser();
        return chooser.showSaveDialog(null);
    }

    private String readFileWithLowLevelStream(File file){
        try {
            FileInputStream fis = new FileInputStream(file);
            int size = fis.available();
            byte[] bytes = new byte[size];
            fis.read(bytes);
            fis.close();
            return new String(bytes);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private String readFileWithHighLevelStream(File file){
        try {
            FileInputStream fis = new FileInputStream(file);
            DataInputStream dis = new DataInputStream(fis);
            String input = dis.readUTF();
            dis.close();
            fis.close();
            return input;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private void saveFileWithLowLevelStream(File file, String text){
        try {
            FileOutputStream fos = new FileOutputStream(file);
            byte[] bytes = text.getBytes();
            fos.write(bytes);
            fos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void saveFileWithHighLevelStream(File file, String text){
        try {
            FileOutputStream fos = new FileOutputStream(file);
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeUTF(text);
            dos.close();
            fos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private EventHandler onNewClicked(TextEditorUI root){
        return new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                root.textArea.setText("");
            }
        };
    }
    
    private EventHandler onOpenClicked(TextEditorUI root){
        return new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                File file = openFileChooser();
                if(file != null){
                    //String text = readFileWithLowLevelStream(file);
                    String text = readFileWithHighLevelStream(file);
                    if(text != null){
                        root.textArea.setText(text);
                    }
                }
            }
        };
    }
    
    private EventHandler<ActionEvent> onSaveClicked(TextEditorUI root) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File file = saveFileChooser();
                if (file != null) {
                    String text = root.textArea.getText();
                    saveFileWithHighLevelStream(file, text);
                    //saveFileWithLowLevelStream(file, text);
                }
            }
        };
    }
    
    private EventHandler<ActionEvent> onExitClicked(){
        return new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        };
    }
    
    private EventHandler<ActionEvent> onCopyClicked(TextEditorUI root){
        return new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                root.textArea.copy();
            }
        };
    }
    
    private EventHandler<ActionEvent> onCutClicked(TextEditorUI root){
        return new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                root.textArea.cut();
            }
        };
    }
    
    private EventHandler<ActionEvent> onPasteClicked(TextEditorUI root){
        return new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                root.textArea.paste();
            }
        };
    }
    
    private EventHandler<ActionEvent> onDeleteClicked(TextEditorUI root){
        return new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                IndexRange range = root.textArea.getSelection();
                root.textArea.deleteText(range);
            }  
        };
    }
    
    private EventHandler<ActionEvent> onSelectAllClicked(TextEditorUI root){
        return new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                root.textArea.selectAll();
            }
        };
    }
    
    private EventHandler<ActionEvent> onAboutClicked(){
        return new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                //getAboutDialog().show();
                showAboutDialog();
            }  
        };
    }
    
    private Stage getAboutDialog(){
        Stage aboutDialog = new Stage();
        aboutDialog.setTitle("About Text Editor");
        
        FlowPane content = new FlowPane(Orientation.VERTICAL);
        content.setVgap(10);
        Text brief = new Text("This a simple notepad, write your thoughts");
        Text version = new Text("Version 1.0");
        Button okBtn = new Button("Ok");
        okBtn.setPrefWidth(100);
        okBtn.setDefaultButton(true);
        okBtn.setOnAction(
                new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event) {
                        aboutDialog.close();
                    }
                }
        );
        
        content.getChildren().add(brief);
        content.getChildren().add(version);
        content.getChildren().add(okBtn);
        aboutDialog.setScene(new Scene(content,350,100));
        return aboutDialog;
    }
    
    private void showAboutDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About Notepad");
        alert.setHeaderText("Text Editor using javaFX");
        alert.setContentText("Simple Notepad application built with JavaFX.\n version 1.0");
        alert.showAndWait();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}

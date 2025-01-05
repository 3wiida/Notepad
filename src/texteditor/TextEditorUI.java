package texteditor;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;

public class TextEditorUI extends BorderPane {

    protected final MenuBar menuBar;
    protected final Menu fileMenu;
    protected final MenuItem newMenuItem;
    protected final MenuItem openMenuItem;
    protected final MenuItem saveMenuItem;
    protected final MenuItem exitMenuItem;
    protected final Menu editMenu;
    protected final MenuItem cutMenuItem;
    protected final MenuItem copyMenuItem;
    protected final MenuItem pasteMenuItem;
    protected final MenuItem deleteMenuItem;
    protected final MenuItem selectAllMenuItem;
    protected final Menu helpMenu;
    protected final MenuItem aboutMenuItem;
    protected final TextArea textArea;

    public TextEditorUI() {

        menuBar = new MenuBar();
        fileMenu = new Menu();
        newMenuItem = new MenuItem();
        openMenuItem = new MenuItem();
        saveMenuItem = new MenuItem();
        exitMenuItem = new MenuItem();
        editMenu = new Menu();
        cutMenuItem = new MenuItem();
        copyMenuItem = new MenuItem();
        pasteMenuItem = new MenuItem();
        deleteMenuItem = new MenuItem();
        selectAllMenuItem = new MenuItem();
        helpMenu = new Menu();
        aboutMenuItem = new MenuItem();
        textArea = new TextArea();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        BorderPane.setAlignment(menuBar, javafx.geometry.Pos.CENTER);

        fileMenu.setMnemonicParsing(false);
        fileMenu.setText("File");

        newMenuItem.setMnemonicParsing(false);
        newMenuItem.setText("new");
        newMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));

        openMenuItem.setMnemonicParsing(false);
        openMenuItem.setText("open");
        openMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));

        saveMenuItem.setMnemonicParsing(false);
        saveMenuItem.setText("save");
        saveMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));

        exitMenuItem.setMnemonicParsing(false);
        exitMenuItem.setText("Exit");
        exitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));

        editMenu.setMnemonicParsing(false);
        editMenu.setText("Edit");

        cutMenuItem.setMnemonicParsing(false);
        cutMenuItem.setText("cut");

        copyMenuItem.setMnemonicParsing(false);
        copyMenuItem.setText("copy");

        pasteMenuItem.setMnemonicParsing(false);
        pasteMenuItem.setText("paste");

        deleteMenuItem.setMnemonicParsing(false);
        deleteMenuItem.setText("delete");

        selectAllMenuItem.setMnemonicParsing(false);
        selectAllMenuItem.setText("select all");

        helpMenu.setMnemonicParsing(false);
        helpMenu.setText("Help");

        aboutMenuItem.setMnemonicParsing(false);
        aboutMenuItem.setText("About");
        setTop(menuBar);

        BorderPane.setAlignment(textArea, javafx.geometry.Pos.CENTER);
        textArea.setPrefHeight(200.0);
        textArea.setPrefWidth(200.0);
        setCenter(textArea);

        fileMenu.getItems().add(newMenuItem);
        fileMenu.getItems().add(openMenuItem);
        fileMenu.getItems().add(saveMenuItem);
        fileMenu.getItems().add(exitMenuItem);
        menuBar.getMenus().add(fileMenu);
        editMenu.getItems().add(cutMenuItem);
        editMenu.getItems().add(copyMenuItem);
        editMenu.getItems().add(pasteMenuItem);
        editMenu.getItems().add(deleteMenuItem);
        editMenu.getItems().add(selectAllMenuItem);
        menuBar.getMenus().add(editMenu);
        helpMenu.getItems().add(aboutMenuItem);
        menuBar.getMenus().add(helpMenu);

    }
}

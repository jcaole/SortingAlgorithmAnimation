package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.animation.SequentialTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;

public class MainWindow extends BorderPane {

	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 400;
	public static final int XGAP = 10;
	public static final int BUTTONROW_BOUNDARY = 100;

	public static int NUMBER_OF_BARS=30;

	private static AbstractSort abstractSort;

	private Pane display;
	//private HBox display;
	private HBox buttons;

	private Button startButton;
	private Button randomButton;
	private Button pauseButton;
	private Button unpauseButton;
	private Button replayButton;
	private Button stopButton;
	private ChoiceBox<AbstractSort> choiceBox;
	List<Integer> sizeList;

	private Label status;

	private Bar[] bars;
	private VBox rootMain;

	private MenuBar menuBar;
	private Menu fileMenu;
	private Menu helpMenu;
	private MenuItem exitItem;
	private MenuItem instructionsItem;
	List<AbstractSort> abstractSortList;
	SequentialTransition animation = new SequentialTransition();
	private ColorPicker colorPicker;
	private Color color = Color.BLACK;
	

	public MainWindow() {
		loadSettings();
		createMenu();
		createRandomizeBtn();
		createStartBtn();
		createPauseBtn();
		createUnpauseBtn();
		createReplayBtn();
		createStopBtn();
		createAlgoBox();
		createState();
		createColorPicker();
		rootMain = new VBox(buttons,status,display);
		rootMain.setAlignment(Pos.TOP_CENTER);
		rootMain.setFillWidth(false);
		rootMain.setSpacing(15);
		this.setCenter(rootMain);
	}
	
	private void loadSettings() {
		this.display = new Pane();
		this.display.setRotate(180);
		this.buttons = new HBox();
		this.buttons.setAlignment(Pos.CENTER);
		this.buttons.setSpacing(10);
	}
	

	private void createMenu() {
		menuBar = new MenuBar();
		fileMenu = new Menu("File");
		helpMenu = new Menu("Help");
		instructionsItem = new MenuItem("Instruction");
		exitItem = new MenuItem("Exit");
		helpMenu.getItems().addAll(instructionsItem);
		fileMenu.getItems().addAll(exitItem);
		menuBar.getMenus().addAll(fileMenu, helpMenu);
		menuBar.prefWidthProperty().bind(this.widthProperty());
		this.setTop(menuBar);
		menuEvents();
	}

	private void menuEvents() {
		EventHandler<ActionEvent> action = theHandler();
		exitItem.setOnAction(action);
		instructionsItem.setOnAction(action);
	}

	private EventHandler<ActionEvent> theHandler() {
		return new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				MenuItem mItem = (MenuItem) event.getSource();
				String item = mItem.getText();
				if (item.equalsIgnoreCase("Exit")) {
					Platform.exit();
				}
				if (item.equalsIgnoreCase("Instruction")) {
					createInstructionsDialog();
				}
			}

			private void createInstructionsDialog() {
				Alert informationAlert = new Alert(AlertType.INFORMATION);
				informationAlert.setWidth(500);
				informationAlert.setTitle("Instructions");
				informationAlert.setHeaderText(null);
				informationAlert.setContentText("\t1. Click the randomize array button. \n"
						+ "\t2. Click the type of sorting algorithm to be used \n "
						+ "\t3. Click Start to run the animation");
				informationAlert.showAndWait();

			}
		};
	}
	private void createColorPicker()
	{
		this.colorPicker = new ColorPicker(Color.BLACK);
		this.colorPicker.setTooltip(new Tooltip("Bars color"));
		buttons.getChildren().add(colorPicker);
		ColorPickerEvent();
	}
	
	private void ColorPickerEvent() {
		colorPicker.setOnAction(event -> {
			color = colorPicker.getValue();
		});
	}

	private void createRandomizeBtn() {
		this.randomButton = new Button("Randomize");
		this.randomButton.setTooltip(new Tooltip("Randomize bars"));
		buttons.getChildren().add(randomButton);
		RandomBtnEvent();
	}

	private void RandomBtnEvent() {
		randomButton.setOnAction(event -> {
			replayButton.setDisable(true);
			choiceBox.setDisable(false);
			display.getChildren().clear();
			startButton.setDisable(false);
			bars = RandomBars.randomBars(NUMBER_OF_BARS,color);

			display.getChildren().addAll(Arrays.asList(bars));
		});

	}

	private void StartAnimation() {
		abstractSort = choiceBox.getSelectionModel().getSelectedItem();
		if(colorPicker.getValue() != Color.BLACK) {
			//if user choosed color change color, not default
		abstractSort.SELECT_COLOR = colorPicker.getValue();
		}
		SequentialTransition sq = new SequentialTransition();
		this.animation = sq;
		sq.getChildren().addAll(abstractSort.startSort(bars));
		sq.setOnFinished(e -> {
			randomButton.setDisable(false);
			startButton.setDisable(true);
			pauseButton.setDisable(true);
			replayButton.setDisable(false);
			stopButton.setDisable(true);
			status.setText("Current State: " + animation.getStatus().toString());
		});
		sq.play();
		status.setText("Current State: " + animation.getStatus().toString());

	}

	private void createStartBtn() {
		this.startButton = new Button("Start");
		buttons.getChildren().add(startButton);
		StartBtnEvent();
	}

	private void StartBtnEvent() {
		startButton.setOnAction(event -> {
			startButton.setDisable(true);
			randomButton.setDisable(true);
			pauseButton.setDisable(false);
			unpauseButton.setDisable(true);
			replayButton.setDisable(true);
			stopButton.setDisable(false);
			choiceBox.setDisable(true);
			StartAnimation();

		});

	}

	private void createPauseBtn() {
		this.pauseButton = new Button("Pause");
		this.pauseButton.setDisable(true);
		buttons.getChildren().add(pauseButton);
		PauseBtnEvent();

	}

	private void PauseBtnEvent() {
		pauseButton.setOnAction(event -> {
			pauseButton.setDisable(true);
			unpauseButton.setDisable(false);
			replayButton.setDisable(true);
			choiceBox.setDisable(true);
			animation.pause();
			status.setText("Current State: " + animation.getStatus().toString());
		});
	}

	private void createUnpauseBtn() {
		this.unpauseButton = new Button("Unpause");
		this.unpauseButton.setDisable(true);
		buttons.getChildren().add(unpauseButton);
		UnpauseBtnEvent();
	}

	private void UnpauseBtnEvent() {
		unpauseButton.setOnAction(event -> {
			unpauseButton.setDisable(true);
			pauseButton.setDisable(false);
			replayButton.setDisable(false);
			choiceBox.setDisable(true);
			animation.play();
			status.setText("Current State: " + animation.getStatus().toString());
		});
	}

	private void createReplayBtn() {
		this.replayButton = new Button("Replay");
		this.replayButton.setDisable(true);
		buttons.getChildren().add(replayButton);
		ReplayBtnEvent();
	}

	private void ReplayBtnEvent() {
		replayButton.setOnAction(event -> {
			replayButton.setDisable(false);
			pauseButton.setDisable(false);
			startButton.setDisable(true);
			randomButton.setDisable(true);
			choiceBox.setDisable(true);
			animation.play();
			status.setText("Current State: " + animation.getStatus().toString());
		});
	}
	
	private void createStopBtn() {
		stopButton = new Button("Stop");
		this.stopButton.setDisable(true);
		buttons.getChildren().add(stopButton);
		stopButtonEvent();
		
	}

	private void stopButtonEvent() {
		stopButton.setOnAction(event -> {
			replayButton.setDisable(true);
			pauseButton.setDisable(true);
			startButton.setDisable(true);
			randomButton.setDisable(false);
			choiceBox.setDisable(true);
			stopButton.setDisable(true);
			unpauseButton.setDisable(true);
			animation.stop();
			status.setText("Current State: " + animation.getStatus().toString());
		});
		
	}

	private void createAlgoBox() {
		this.choiceBox = new ChoiceBox<>();
		this.choiceBox.setTooltip(new Tooltip("Sorting Algorithms"));
		this.bars = RandomBars.randomBars(NUMBER_OF_BARS,color);
		buttons.getChildren().add(choiceBox);
		initializeList();
	}

	private void initializeList() {
		abstractSortList = new ArrayList<>();
		abstractSortList.add(new MergeSort());
		abstractSortList.add(new QuickSort());
		abstractSortList.add(new BubbleSort());
		abstractSortList.add(new HeapSort());
		abstractSortList.add(new SelectionSort());
		abstractSortList.add(new InsertionSort());
		abstractSortList.add(new CombSort());
		display.getChildren().addAll(Arrays.asList(bars));
		choiceBox.setItems(FXCollections.observableArrayList(abstractSortList));

		choiceBox.getSelectionModel().select(5);

		choiceBox.setConverter(new StringConverter<AbstractSort>() {
			@Override
			public String toString(AbstractSort abstractSort) {
				if (abstractSort == null) {
						return"";
				} else {
					startButton.setDisable(false);
					return abstractSort.getClass().getSimpleName();
				}
			}

			@Override
			public AbstractSort fromString(String s) {
				return null;
			}
		});
	}
	private void createState() {
		status = new Label("Current State: " + animation.getStatus().toString());
	}
}
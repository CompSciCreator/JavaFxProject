/*This code is for the JavaFX controller that visualizes and sorts arrays. 
It includes methods for generating, sorting, and updating the UI components. 
The main sorting algorithm implemented is Bubble Sort. 
*/


import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ComboBox;
import javafx.application.Platform;

import javafx.scene.layout.AnchorPane;
import javafx.scene.chart.XYChart;
import javafx.fxml.FXML;
import javafx.scene.Parent; 
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class MainController  {
    //@FXML is being injected by the FXML Loader
    @FXML private Button BarChart; // represents a button that will generate a Bar chart
    @FXML private Button btnSort;
    @FXML private Button BarGraphButton;
    @FXML private Slider sizeSlider; 
    @FXML private Button btnGenerate;// reference to a button object
    

    public BarChart BCArray; 
    public AnchorPane pane;
    public Slider speedSlider;
    public ComboBox<String> algorithmList; //will be used for sorting.
    public Label complexityLabel;
    public Label speedLabel;
    

    private static final int MAX_RANDOM_NUMBER = 496;
    private static final int MIN_RANDOM_NUMBER = 5;
    private int array[];
    private int sliderValue, delayMultiplier = 8;
    private String algorithm="Bubble Sort";
    private int delay=30;
    private boolean bubbleSorting,move;
    private Random rand =new Random();
    public Scene scene;
    public Parent root;

    public void arrayGenerate() { //This method generates an array of random numbers, sorts it, and handles duplicates.

        sliderValue=(int) sizeSlider.getValue();
        array = new int[sliderValue];
        //array generation logic
        for(int i=0; i<array.length; i++){
            array[i] = rand.nextInt(MAX_RANDOM_NUMBER - MIN_RANDOM_NUMBER +1) + MIN_RANDOM_NUMBER;
        }
        // Sorting array
        Arrays.sort(array);
        // Checking for duplicates
        for(int i=0; i<array.length; i++) {
            for(int j=0; j<array.length-1;j++) {
                if(array[j]==array[j+1]) {  //if duplicates aside, then increment the number(like 169 169 will become 169 170)
                    array[j+1]=array[j+1]+1;
                }         
            }

        }
        shuffleArray();
        updateChart(array);
    }

    
    private void shuffleArray() { //This method shuffles the generated array.

        //Shuffling array logic 
        for(int i=0; i<array.length; i++) {
            for(int k=0; k<array.length;k++){
                for (int j = 0; j <array.length-1; j++) {
                    if(rand.nextBoolean()){
                        int t=array[j];
                        array[j]=array[j+1];
                        array[j+1]=t;
                    }
                }
                
            }
        }
        updateChart(array);
    }

    // Simulated Timer delay manager, for creating and sorting array
    private void setDelay() {
        if(array.length ==0 || array.length <=15) delay=40* delayMultiplier;
        else if(array.length <=30) delay = 18* delayMultiplier;
        else if(array.length <=40) delay = 13* delayMultiplier;
        else if(array.length <=60) delay = 10* delayMultiplier;
        else if(array.length <=75) delay = 8*  delayMultiplier;
        else delay = 5* delayMultiplier;
        speedLabel.setText("Simulated Sort Time: "+delay+" ms");
        
    }

    // Bubble Sorting Algorithm with a simulated timer 
    private int i=0, j=0, count=0; //use int I and J for sorting
    public void bubbleSort() {
        disableAll();
        setDelay();
        bubbleSorting =true;

        Timer outerTimer =new Timer();
        outerTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                count=0;
                Timer innerTimer =new Timer();
                innerTimer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        move=false;
                        if (array[j]>array[j+1]){
                            int t=array[j];
                            array[j]=array[j+1];
                            array[j]=array[j+1];
                            array[j+1]=t;
                            move=true;
                        }else count++;

                        Platform.runLater(() -> updateChart(array));

                        if (count==array.length-1) {
                            innerTimer.cancel();
                            outerTimer.cancel();
                            enableAll();
                            bubbleSorting = false;
                            j=0;
                            i=0;
                        }
                        j++;
                        if (j==array.length-1) {
                            innerTimer.cancel();
                            j=0;
                            i=0;
                        }
                    }
                }, 0, delay);
                i++;
                if (i==array.length-1) {
                    outerTimer.cancel();
                    i=0;
                    j=0;
                    enableAll();
                    bubbleSorting = false;
                }
            }
        },0,array.length *delay+10);       
    }

    private void enableAll() { //enabaling UI 
        btnSort.setDisable(false);
        btnGenerate.setDisable(false);
        sizeSlider.setDisable(false);
        speedSlider.setDisable(false);
        algorithmList.setDisable(false);
    }

    private void disableAll() { //disabling UI
        btnSort.setDisable(true);
        btnGenerate.setDisable(true);
        sizeSlider.setDisable(true);
        speedSlider.setDisable(true);
        algorithmList.setDisable(true);
    }


    private Node n;
    public void updateChart(int[] Tarray) {  // Updating the BarChart with the given array data
        BCArray.getData().clear();
        BCArray.layout();
        XYChart.Series series = new XYChart.Series();
        for (int n:Tarray) {
            series.getData().add(new XYChart.Data<>(String.valueOf(n),n));
        }
        series.setName("Numbers");
        BCArray.getData().setAll(series);
        BCArray.setLegendVisible(false);

        if (bubbleSorting) { //implementing bubblesort style within the method data
            BCArray.setTitle("Data VisualizerFX");
            BCArray.lookupAll(".defualt-color0.chart-bar")
                .forEach(n -> n.setStyle("-fx-bar-fill: #2c3e50;")); //style for all bars
            BCArray.lookup(".chart-plot-background").setStyle("-fx-background-color: #1a1a1a;"); //chart bar graph
            BCArray.setStyle("-fx-background-color: #212121;");
            pane.setStyle("-fx-background-color: #212121");
            n = BCArray.lookup(".data"+j+".chart-bar");
            if(move){
                n.setStyle("-fx-bar-fill: #e74c3c");
            }else
                n.setStyle("-fx-bar-fill: #2ecc71;");

            n = BCArray.lookup(".data"+(j+1)+".chart-bar");
            n.setStyle("-fx-bar-fill: #2ecc71;");
        }
        else{
            BCArray.setTitle("Random Array of "+array.length+" elements");
            BCArray.lookup(".chart-plot-background").setStyle("-fx-background-color: #1a1a1a;"); //chart bg 
            BCArray.setStyle("-fx-background-color: #2c3e50;");
            pane.setStyle("-fx-background-color: #2c3e50;");
        }
        System.out.println("from update");
    }

    @FXML
    public void initialize() { // Initializing the controller, setting listeners, and button styling

        arrayGenerate();
        setDelay();
        sizeSlider.valueProperty().addListener(

                (options, oldValue, newValue) ->
                {
                    if (newValue.equals(oldValue))
                        return;
                    sliderValue=newValue.intValue();
                    arrayGenerate();
                    setDelay();
                }
        );
        sizeSlider.setShowTickLabels(true); //slider pe numbers
        speedSlider.valueProperty().addListener(
                (options, oldValue, newValue) ->
                {
                    if (newValue.equals(oldValue))
                        return;
                    delayMultiplier =newValue.intValue(); //1 to 10
                    delayMultiplier =11- delayMultiplier; //inverting
                    System.out.println(delayMultiplier);
                    setDelay();
                }
        );

        algorithmList.getItems().add("Bubble Sort");
        algorithmList.getSelectionModel().select(0);

        algorithmList.getSelectionModel().selectedItemProperty().addListener(
                (options, oldValue, newValue) ->
                {
                    if (newValue.equals(oldValue))
                        return;
                    algorithm= newValue;
                    switch (algorithm) {
                        case "Bubble Sort":
                            complexityLabel.setText("Complexity of Bubble Sort: O(n^2)");
                            break;

                    }
                    arrayGenerate();
                });
            //button style
            btnGenerate.setOnMouseEntered(e -> { btnGenerate.setStyle("-fx-background-color : #ffe6e6" ); });
            btnGenerate.setOnMouseExited(e -> { btnGenerate.setStyle("-fx-background-color : #ffcccc"); });
    }

    public void sort() {
        switch (algorithm) {
            case "Bubble Sort":
            bubbleSort();
            break;
        }
    }

    
}


/*
 * areas to improve code: 
 * 
 * Encapsulation: Consider encapsulating related functionality into separate classes or methods. 
 * This can make the code more modular and easier to understand.
 * 
 * Code Organization: Organize code into logical sections with comments, improve readability and maintainability.
 * 
 * Understand and expand knowledge of FXML Logic and implementation: ensure that the FXML components are properly loaded.
 * 
 * 
 */




    











    


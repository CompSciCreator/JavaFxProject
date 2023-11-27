# JavaFX Sorting Visualization Project

This JavaFX project aims to visualize the sorting process using a bubble sort algorithm. The application generates a random array of numbers, sorts them using the bubble sort algorithm, and provides a visual representation of the sorting process.

## Getting Started

To run the project, execute the `App.java` file, which serves as the entry point. This will launch the JavaFX application and display the sorting visualization window.

```java
public class App extends Application {
    // ... (code snippet)
    public static void main(String[] args) {
        Application.launch(args);
    }
}
```

## Features

- **Sorting Algorithm:** The project currently implements the bubble sort algorithm for sorting the generated array.

- **User Interface:** The user interface is built using JavaFX, providing a visually appealing representation of the sorting process.

- **Real-Time Visualization:** The application dynamically updates the chart to illustrate each step of the sorting algorithm.

## Usage

- **Sorting:** Click the "Sort" button to initiate the sorting process. The visualization will demonstrate the steps taken during the bubble sort algorithm.

- **Array Generation:** The "Generate" button creates a new random array for sorting.

- **Customization:** Adjust the array size, sorting speed, and choose different sorting algorithms through the UI components.

## Code Overview

### App.java

The `App.java` file contains the main class extending `Application` and is responsible for launching the JavaFX application. It also handles window drag functionality for the undecorated stage.

### MainController.java

The `MainController.java` file is the controller class associated with the FXML file (`sample.fxml`). It manages the logic for array generation, sorting, and updating the chart in real-time. Some areas for improvement include encapsulation, code organization, and further understanding of FXML logic.

### Areas for Improvement

- **Encapsulation:** Consider encapsulating related functionality into separate classes or methods for improved modularity.

- **Code Organization:** Organize code into logical sections with comments to enhance readability and maintainability.

- **FXML Logic:** Ensure proper loading and handling of FXML components for a better understanding of JavaFX.

## References

These sources were the building blocks of my code for this project. If you need help understanding any logic:

https://docs.oracle.com/javafx/2/charts/jfxpub-charts.htm
https://docs.oracle.com/javase/8/docs/api/java/util/Timer.html
https://www.javaguides.net/2018/09/bubble-sort-algorithm-in-java.html
https://www.tutorialspoint.com/generate-a-random-array-of-integers-in-java
https://www.digitalocean.com/community/tutorials/shuffle-array-java
https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/SortEvent.html

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

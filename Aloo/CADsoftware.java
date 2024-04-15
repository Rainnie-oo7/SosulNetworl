// Eclipse findet org. nicht
//Eclispe Projekt richtig angefangen
//????
import org.jzy3d.chart.Chart;
import org.jzy3d.chart.controllers.mouse.camera.AWTCameraMouseController;
import org.jzy3d.colors.Color;
import org.jzy3d.javafx.JavaFXChartFactory;
import org.jzy3d.javafx.chart.JavaFXChartComponentFactory;
import org.jzy3d.maths.Coord3d;
import org.jzy3d.plot3d.builder.Builder;
import org.jzy3d.plot3d.builder.Mapper;
import org.jzy3d.plot3d.primitives.LineStrip;
import org.jzy3d.plot3d.primitives.Scatter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CADSoftware extends Application {
    private Chart chart;
    private Scatter scatter;
    private LineStrip[] edges;

    private int selectedEdgeIndex = -1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        JavaFXChartComponentFactory.bindDefault();
        chart = new JavaFXChartFactory().newChart();

        initUI(primaryStage);

        primaryStage.show();
    }

    private void initUI(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        // UI elements
        ComboBox<String> shapeComboBox = new ComboBox<>();
        shapeComboBox.getItems().addAll("Cube");  // Add more shapes if needed
        shapeComboBox.setValue("Cube");

        TextField xField = new TextField();
        TextField yField = new TextField();
        TextField zField = new TextField();

        Label xLabel = new Label("X:");
        Label yLabel = new Label("Y:");
        Label zLabel = new Label("Z:");

        Button addButton = new Button("Add Shape");
        Button selectButton = new Button("Select Edge");
        Button moveButton = new Button("Move Edge");

        // Add Shape button event handler
        addButton.setOnAction(event -> {
            String shapeType = shapeComboBox.getValue();
            double x = Double.parseDouble(xField.getText());
            double y = Double.parseDouble(yField.getText());
            double z = Double.parseDouble(zField.getText());

            if (shapeType.equals("Cube")) {
                addCube(x, y, z);
            }
        });

        // Select Edge button event handler
        selectButton.setOnAction(event -> {
            selectedEdgeIndex = scatter.getHighlighted()[0];
            highlightSelectedEdge();
        });

        // Move Edge button event handler
        moveButton.setOnAction(event -> moveEdge());

        // Add UI elements to grid
        grid.add(shapeComboBox, 0, 0);
        grid.add(addButton, 1, 0);
        grid.add(xLabel, 0, 1);
        grid.add(xField, 1, 1);
        grid.add(yLabel, 0, 2);
        grid.add(yField, 1, 2);
        grid.add(zLabel, 0, 3);
        grid.add(zField, 1, 3);
        grid.add(selectButton, 0, 4);
        grid.add(moveButton, 1, 4);

        // Create scene
        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setTitle("Simple CAD Software");
        primaryStage.setScene(scene);

        // Create 3D chart
        chart.addController(new AWTCameraMouseController(chart));
        primaryStage.setScene(new Scene(JavaFXChartComponentFactory.bind(chart)));
    }

    private void addCube(double x, double y, double z) {
        Mapper mapper = (x_, y_) -> z;
        Builder builder = new Builder(mapper, 0, x, 0, y);
        scatter = new Scatter(builder);
        edges = new LineStrip[12];

        Coord3d[][] cubeVertices = new Coord3d[2][2];
        cubeVertices[0][0] = new Coord3d(0, 0, 0);
        cubeVertices[1][0] = new Coord3d(x, 0, 0);
        cubeVertices[1][1] = new Coord3d(x, y, 0);
        cubeVertices[0][1] = new Coord3d(0, y, 0);

        for (int i = 0; i < 2; i++) {
            edges[i] = new LineStrip(cubeVertices[i]);
            edges[i + 2] = new LineStrip(cubeVertices[i], Color.BLUE);
            edges[i + 4] = new LineStrip(cubeVertices[0][i], cubeVertices[1][i]);
        }

        chart.getScene().getGraph().add(scatter);
        for (LineStrip edge : edges) {
            chart.getScene().getGraph().add(edge);
        }

        chart.getView().setSquared(true);
    }

    private void highlightSelectedEdge() {
        for (LineStrip edge : edges) {
            edge.setColor(Color.BLUE);
        }

        if (selectedEdgeIndex != -1) {
            edges[selectedEdgeIndex].setColor(Color.RED);
        }

        chart.render();
    }

    private void moveEdge() {
        if (selectedEdgeIndex != -1) {
            double xMove = Double.parseDouble(xField.getText());
            double yMove = Double.parseDouble(yField.getText());
            double zMove = Double.parseDouble(zField.getText());

            Coord3d[] edgeVertices = edges[selectedEdgeIndex].getVertices();
            for (Coord3d vertex : edgeVertices) {
                vertex.x += xMove;
                vertex.y += yMove;
                vertex.z += zMove;
            }

            edges[selectedEdgeIndex].setData(edgeVertices);
            highlightSelectedEdge();
        }
    }
}

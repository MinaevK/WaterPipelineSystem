import Entity.Pipeline;
import Entity.Point;
import Utility.CSVConnector;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String pathRead = "./Resources/input.csv";
        String pathWrite = "./Resources/output.csv";
        String pathPoints = "./Resources/points.csv";
        boolean append = true;

        Point p1 = new Point(1);
        Point p2 = new Point(2);
        Point p3 = new Point(3);
        Point p4 = new Point(4);
        Point p5 = new Point(5);
        Point p6 = new Point(6);
        Point p7 = new Point(7);

        Point pointA = new Point(6);
        Point pointB = new Point(7);

        Pipeline pipeline1 = new Pipeline(p1, p2, 10);
        Pipeline pipeline2 = new Pipeline(p2, p3, 20);
        Pipeline pipeline3 = new Pipeline(p3, p4, 30);
        Pipeline pipeline4 = new Pipeline(p3, p5, 15);
        Pipeline pipeline5 = new Pipeline(p6, p7, 20);
        //
        Pipeline pipeline6 = new Pipeline(p1, p4, 20);
        Pipeline pipeline7 = new Pipeline(p5, p7, 40);
        Pipeline pipeline8 = new Pipeline(p7, p4, 80);

        List<Pipeline> pipelineList = new ArrayList<>();
        pipelineList.add(pipeline1);
        pipelineList.add(pipeline2);
        pipelineList.add(pipeline3);
        pipelineList.add(pipeline4);
        pipelineList.add(pipeline5);
/*        pipelineList.add(pipeline6);
        pipelineList.add(pipeline7);
        pipelineList.add(pipeline8);*/

     List<Pipeline> pipelinesFromCSV = CSVConnector.ReadPiplinesDataCSV(pathRead);
     Point[][] points = CSVConnector.ReadPointsDataCSV(pathPoints);
        for (int i = 0; i < points.length; i++) {
            CSVConnector.WriteInCSV(pathWrite, Pipeline.findRouteInSystem(pipelinesFromCSV, points[i][0], points[i][1]));
        }
    }
}

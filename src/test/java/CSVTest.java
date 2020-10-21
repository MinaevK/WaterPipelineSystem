import Entity.Pipeline;
import Entity.Point;
import Utility.CSVConnector;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVTest {

    private Point p1 = new Point(1);
    private Point p2 = new Point(2);
    private Point p3 = new Point(3);
    private Point p4 = new Point(4);
    private Point p5 = new Point(5);
    private Point p6 = new Point(6);
    private Point p7 = new Point(7);


    private Pipeline pipeline1 = new Pipeline(p1, p2, 10);
    private Pipeline pipeline2 = new Pipeline(p2, p3, 20);
    private Pipeline pipeline3 = new Pipeline(p3, p4, 30);
    private Pipeline pipeline4 = new Pipeline(p3, p5, 15);
    private Pipeline pipeline5 = new Pipeline(p6, p7, 20);


    @Test
    void readPiplinesDataCSV() {
        List<Pipeline> pipelinesFromCSV = CSVConnector.ReadPiplinesDataCSV("./Resources/input.csv");

        //first pipeline
        assertEquals(pipeline1.getStartPoint().getId(), pipelinesFromCSV.get(0).getStartPoint().getId());
        assertEquals(pipeline1.getEndPoint().getId(), pipelinesFromCSV.get(0).getEndPoint().getId());
        assertEquals(pipeline1.getLength(), pipelinesFromCSV.get(0).getLength());
        //last pipeline
        assertEquals(pipeline5.getStartPoint().getId(), pipelinesFromCSV.get(4).getStartPoint().getId());
        assertEquals(pipeline5.getEndPoint().getId(), pipelinesFromCSV.get(4).getEndPoint().getId());
        assertEquals(pipeline5.getLength(), pipelinesFromCSV.get(4).getLength());


    }

    @Test
    void readPointsDataCSV() {
        Point[][] importedPoints = CSVConnector.ReadPointsDataCSV("./Resources/points.csv");

        assertEquals(p2.getId(), importedPoints[0][0].getId());
        assertEquals(p5.getId(), importedPoints[0][1].getId());

        assertEquals(p2.getId(), importedPoints[1][0].getId());
        assertEquals(p6.getId(), importedPoints[1][1].getId());

        assertEquals(p6.getId(), importedPoints[2][0].getId());
        assertEquals(p7.getId(), importedPoints[2][1].getId());
    }
}
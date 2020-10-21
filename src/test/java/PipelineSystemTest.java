import Entity.Pipeline;
import Entity.Point;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PipelineSystemTest {

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
    private Pipeline pipeline6 = new Pipeline(p1, p4, 13);
    private Pipeline pipeline7 = new Pipeline(p5, p7, 40);
    private Pipeline pipeline8 = new Pipeline(p7, p4, 80);

    @org.junit.jupiter.api.Test
    void findRouteInSystemTestBasicConditions() {
        List<Pipeline> pipelineList = new ArrayList<>();
        pipelineList.add(pipeline1);
        pipelineList.add(pipeline2);
        pipelineList.add(pipeline3);
        pipelineList.add(pipeline4);
        pipelineList.add(pipeline5);

        Point pointA = new Point(2);
        Point pointB = new Point(5);
        assertEquals("true;35", Pipeline.findRouteInSystem(pipelineList, pointA, pointB));

        pointA = new Point(2);
        pointB = new Point(6);
        assertEquals("false", Pipeline.findRouteInSystem(pipelineList, pointA, pointB));

        pointA = new Point(6);
        pointB = new Point(7);
        assertEquals("true;20", Pipeline.findRouteInSystem(pipelineList, pointA, pointB));

    }

    @org.junit.jupiter.api.Test
    void findRouteInSystemTestAdditionalConditions() {
        List<Pipeline> pipelineList = new ArrayList<>();
        pipelineList.add(pipeline1);
        pipelineList.add(pipeline2);
        pipelineList.add(pipeline3);
        pipelineList.add(pipeline4);
        pipelineList.add(pipeline5);
        pipelineList.add(pipeline6);
        pipelineList.add(pipeline7);
        pipelineList.add(pipeline8);

        Point pointA = new Point(1);
        Point pointB = new Point(4);
        assertEquals("true;13", Pipeline.findRouteInSystem(pipelineList, pointA, pointB));
    }
}
package Entity;

import java.util.*;
import java.util.stream.Collectors;

public class Pipeline {
    private Point startPoint;
    private Point endPoint;
    private int length;

    private static List<Integer> results = new ArrayList();
    private static int localLength = 0;

    public Pipeline(Point startPoint, Point endPoint, int length) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.length = length;
    }

    @Override
    public String toString() {
        return startPoint.getId() + ";" + endPoint.getId() + ";" + length;
    }

    //returns a list of pipelines that start with certain point
    private static List<Pipeline> findByIndex(List<Pipeline> pipelines, Point pointX) {
        return pipelines.stream()
                .filter(pipeline -> pipeline.startPoint.getId() == pointX.getId())
                .collect(Collectors.toList());
    }

    //returns information about the possibility of sought route
    public static String findRouteInSystem(List<Pipeline> pipelines, Point pointA, Point pointB) {

        for (Pipeline pipelineA : findByIndex(pipelines, pointA)) {
            routeSearch(pipelines, pipelineA, pointB);
        }

        int res = results.stream()
                .mapToInt(integer -> integer)
                .min()
                .orElse(-1);
        results.clear();

        if (res != -1)
            return "true;" + res;
        else
            return "false";
    }


    //find all possible routs
    private static void routeSearch(List<Pipeline> pipelines, Pipeline currentPipeline, Point pointB) {
        localLength += currentPipeline.length;
        if (currentPipeline.endPoint.getId() == pointB.getId()) {
            results.add(localLength);
            localLength -= currentPipeline.length;
            return;
        }
        List<Pipeline> pipelineList = findByIndex(pipelines, currentPipeline.endPoint);
        if (!pipelineList.isEmpty()) {
            for (Pipeline pipeline : pipelineList) {
                routeSearch(pipelines, pipeline, pointB);
            }
        }
        localLength -= currentPipeline.length;
        return;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public int getLength() {
        return length;
    }
}

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

    public static List<Pipeline> findByIndex(List<Pipeline> pipelines, Point pointX){
        List<Pipeline> pipelinesList =  pipelines.stream()
                        .filter(pipeline -> pipeline.startPoint.getId() == pointX.getId())
                        .collect(Collectors.toList());
        return pipelinesList;
    }

    public static boolean routeInSystem(List<Pipeline> pipelines, Point pointA, Point pointB){

        for (Pipeline pipelineA: findByIndex(pipelines, pointA)) {
           recursiveSearch(pipelines, pipelineA, pointB);
        }

        Integer res = results.stream()
                        .mapToInt(integer -> integer)
                        .min()
                        .orElse(-1);
        if(res != -1)
            System.out.println("true;" + res);
        else
            System.out.println("false");
        return true;
    }


    public static void recursiveSearch(List<Pipeline> pipelines, Pipeline currentPipeline, Point pointB){
        localLength += currentPipeline.length;
        if(currentPipeline.endPoint.getId() == pointB.getId()){
            results.add(localLength);
            localLength -= currentPipeline.length;
            return;
        }
        List<Pipeline> pipelineList = findByIndex(pipelines, currentPipeline.endPoint);
        if (!pipelineList.isEmpty() ){
            for (Pipeline pipeline: pipelineList) {
                recursiveSearch(pipelines, pipeline, pointB);
            }
        }
        localLength -= currentPipeline.length;
        return;
    }
}

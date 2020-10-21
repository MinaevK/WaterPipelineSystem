package Utility;

import Entity.Pipeline;
import Entity.Point;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVConnector {
    public static List<Pipeline> ReadPiplinesDataCSV(String path) {
        String line = "";
        List<Pipeline> pipelines = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            while ((line = bufferedReader.readLine()) != null) {
                String[] lines = line.split(";");
                pipelines.add(new Pipeline(new Point(Integer.parseInt(lines[0])), new Point(Integer.parseInt(lines[1])), Integer.parseInt(lines[2])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pipelines;
    }

    public static Point[][] ReadPointsDataCSV(String path) {
        String line = "";
        Point[][] points;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            int fileLength = (int) Files.lines(Paths.get(path)).count();
            points = new Point[fileLength][2];
            int i = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] lines = line.split(";");
                points[i][0] = new Point(Integer.parseInt(lines[0]));
                points[i][1] = new Point(Integer.parseInt(lines[1]));
                i++;
            }
        } catch (IOException e) {
            points = null;
            e.printStackTrace();
        }

        return points;
    }

    public static void WriteInCSV(String path, String text) {
        try {
            FileWriter fileWriter = new FileWriter(path, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            printWriter.println(text);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

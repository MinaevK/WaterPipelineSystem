package Utility;

import Entity.Pipeline;
import Entity.Point;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVConnector {
    public static List<Pipeline> ReadCSV(String path){
        String line = "";
        List<Pipeline> pipelines = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            while ((line = bufferedReader.readLine()) != null){
                String[] lines = line.split(";");
                pipelines.add(new Pipeline(new Point(Integer.parseInt(lines[0])), new Point(Integer.parseInt(lines[1])), Integer.parseInt(lines[2])));
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        return pipelines;
    }
    public static void WriteInCSV(String path, String text, boolean append){
        try {
            FileWriter fileWriter = new FileWriter(path, append);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            printWriter.println(text);
            printWriter.close();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

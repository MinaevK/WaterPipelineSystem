import DAO.DAO;
import Entity.Pipeline;
import Entity.Point;
import Utility.CSVConnector;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        //get properties
        Properties properties = null;
        try {
            properties = new Properties();
            FileInputStream inputStream = new FileInputStream("program.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //csv files paths
        String pathRead = properties.getProperty("pathRead");
        String pathWrite = properties.getProperty("pathWrite");
        String pathPoints = properties.getProperty("pathPoints");

        //user/console interaction
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        boolean input = true;
        try {
            while (input) {
                line = reader.readLine();
                switch (line) {
                    case ("1"):
                        showInput(pathRead);
                        break;
                    case ("2"):
                        showPoints(pathPoints);
                        break;
                    case ("3"):
                        solveAndShow(pathRead, pathPoints, pathWrite);
                        break;
                    case ("4"):
                        saveToDB(pathRead, pathPoints);
                        break;
                    case ("help"):
                        System.out.println("Commands:");
                        System.out.println("1 - show pipelines");
                        System.out.println("2 - show points");
                        System.out.println("3 - solve task and show result");
                        System.out.println("4 - save data to database");
                        System.out.println("0 - to exit");
                        break;
                    case ("0"):
                        input = false;
                        break;
                    default:
                        System.out.println("Unknown input. Enter help, to get commands");
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showInput(String pathRead) {
        List<Pipeline> pipelinesFromCSV = CSVConnector.ReadPiplinesDataCSV(pathRead);
        for (Pipeline p : pipelinesFromCSV) {
            System.out.println(p);
        }
    }

    public static void showPoints(String pathPoints) {
        Point[][] points = CSVConnector.ReadPointsDataCSV(pathPoints);
        for (int i = 0; i < points.length; i++) {
            System.out.println(points[i][0].getId() + ";" + points[i][1].getId());
        }
    }

    public static void solveAndShow(String pathRead, String pathPoints, String pathWrite) {
        List<Pipeline> pipelinesFromCSV = CSVConnector.ReadPiplinesDataCSV(pathRead);
        Point[][] points = CSVConnector.ReadPointsDataCSV(pathPoints);
        //solving task for all points
        for (int i = 0; i < points.length; i++) {
            String currentAnswer = Pipeline.findRouteInSystem(pipelinesFromCSV, points[i][0], points[i][1]);
            CSVConnector.WriteInCSV(pathWrite, currentAnswer);
            System.out.println(currentAnswer);
        }
    }

    public static void saveToDB(String pathRead, String pathPoints) {
        //DAO initialize
        DAO dao = new DAO();
        //Get data from csv file
        List<Pipeline> pipelinesFromCSV = CSVConnector.ReadPiplinesDataCSV(pathRead);
        Point[][] points = CSVConnector.ReadPointsDataCSV(pathPoints);

        //saving to DB
        for (Pipeline currentPipline : pipelinesFromCSV)
            dao.saveInputData(currentPipline);
        for (Point[] currentPoint : points)
            dao.savePointsData(currentPoint[0], currentPoint[1]);
    }
}

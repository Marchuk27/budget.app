package utils;

import dto.EventDto;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import static sample.StartConfigurator.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Utils for serialize and deserialize data related with operation events
 * 12.03.2020
 */
public class EventHistoryUtils {

    private static final String FRANKLIN_BDAY = "17.01.1806";

    public static void saveEventsHistoryToTxt(String monthName, String date, String time, int labelId,
                                              int sum) throws IOException, ClassNotFoundException {
        EventDto event = new EventDto(date, time, labelId, sum);
        List<EventDto> eventList = loadEventsHistoryFromTxt(monthName);
        if (eventList.size() > 10) {
            eventList.remove(10);
        }
        checkListForTempEvent(eventList);
        eventList.add(0,event);

        FileOutputStream fileOS = new FileOutputStream(determineStartPath() + monthName + "_history.txt");
        ObjectOutputStream objectOS = new ObjectOutputStream(fileOS);
        objectOS.writeObject(eventList);
        objectOS.flush();
        fileOS.close();
    }

    public static List<EventDto> loadEventsHistoryFromTxt(String monthName) throws IOException, ClassNotFoundException {
        File historyFile = new File(determineStartPath() + monthName + "_history.txt");
        if (!historyFile.exists()) {
            createHistoryFile(historyFile);
        }
        FileInputStream fileIS = new FileInputStream(historyFile);
        ObjectInputStream objectIS = new ObjectInputStream(fileIS);
        return (ArrayList<EventDto>) objectIS.readObject();
    }

    public static void createEventsHistoryTable(GridPane table, String monthName) throws IOException, ClassNotFoundException {
        initEventHistoryTable(table);
        List<EventDto> eventsHistory = loadEventsHistoryFromTxt(monthName);
        Label sumLabel;
        for (EventDto event : eventsHistory) {
            if (event.getCategoryLabelId() <= 9) {
                sumLabel = new Label(String.valueOf(event.getOperationSum() * (-1)));
                sumLabel.setStyle("-fx-text-fill: #ff332e");
            } else {
                sumLabel = new Label("+" + (event.getOperationSum()));
                sumLabel.setStyle("-fx-text-fill: green");
            }

            Label dateLabel = new Label(event.getDateOfEvent() + "\n   " + event.getTimeOfEvent());
            Label categoryLabel = new Label(String.valueOf(event.getCategoryLabelId()));

            int eventIndex = eventsHistory.indexOf(event) + 1;
            table.add(dateLabel, 0 ,eventIndex);
            table.add(categoryLabel, 1 ,eventIndex);
            table.add(sumLabel, 2,eventIndex);
        }
    }

    private static void createHistoryFile(File eventHistory) throws IOException {
        List<EventDto> tempList;
        boolean isFileCreated = eventHistory.createNewFile();
        if (isFileCreated) {
            tempList = new ArrayList<>();
            tempList.add(new EventDto(FRANKLIN_BDAY, "04:20", 1, 1));

            FileOutputStream fileOS = new FileOutputStream(eventHistory);
            ObjectOutputStream objectOS = new ObjectOutputStream(fileOS);
            objectOS.writeObject(tempList);
            fileOS.close();
            objectOS.close();
        }
    }

    private static void checkListForTempEvent(List<EventDto> list) {
        if (list.size() == 1 && list.get(0).getDateOfEvent().equalsIgnoreCase(FRANKLIN_BDAY)) {
            list.remove(0);
        }
    }

    private static void initEventHistoryTable(GridPane table) {
        table.add(new Label("Дата"), 0, 0);
        table.add(new Label("Категория"), 1, 0);
        table.add(new Label("Сумма"), 2, 0);
    }
}

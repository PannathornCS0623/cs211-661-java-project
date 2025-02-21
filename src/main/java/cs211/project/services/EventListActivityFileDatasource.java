package cs211.project.services;

import cs211.project.models.EventActivity;
import cs211.project.models.collections.EventActivityList;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public class EventListActivityFileDatasource implements Datasource<EventActivityList>{
    private String directoryName;
    private String fileName;

    public EventListActivityFileDatasource(String directoryName, String fileName) {
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }
    private void checkFileIsExisted() {
        File file = new File(directoryName);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filePath = directoryName + File.separator + fileName;
        file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public EventActivityList readData() {
        EventActivityList activityEvents = new EventActivityList();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        InputStreamReader inputStreamReader = new InputStreamReader(
                fileInputStream,
                StandardCharsets.UTF_8
        );
        BufferedReader buffer = new BufferedReader(inputStreamReader);

        String line = "";
        try {
            // ใช้ while loop เพื่ออ่านข้อมูลรอบละบรรทัด
            while ( (line = buffer.readLine()) != null ){
                // ถ้าเป็นบรรทัดว่าง ให้ข้าม
                if (line.equals("")) continue;

                // แยกสตริงด้วย ,
                String[] data = line.split(",");

                // อ่านข้อมูลตาม index แล้วจัดการประเภทของข้อมูลให้เหมาะสม
                String eventName = data[0].trim();
                String name = data[1].trim();
                String info = data[2].trim().replace("|", "\n");
                String getDateStart = data[3].trim();
                LocalDate dateStart = LocalDate.parse(getDateStart);
                String getDateEnd = data[4].trim();
                LocalDate dateEnd = LocalDate.parse(getDateEnd);
                String getTimeStart = data[5].trim();
                LocalTime timeStart = LocalTime.parse(getTimeStart);
                String getTimeEnd = data[6].trim();
                LocalTime timeEnd = LocalTime.parse(getTimeEnd);

                // เพิ่มข้อมูลลงใน list
                activityEvents.addNewActivityEvent(eventName, name, info, dateStart, dateEnd, timeStart, timeEnd);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return activityEvents;
    }



    @Override
    public void writeData(EventActivityList data) {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        // เตรียม object ที่ใช้ในการเขียนไฟล์
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                fileOutputStream,
                StandardCharsets.UTF_8
        );
        BufferedWriter buffer = new BufferedWriter(outputStreamWriter);

        try {
            for (EventActivity eventActivity : data.getEventActivities()) {
                String line = eventActivity.getEventName() + "," + eventActivity.getNameEventActivity() + "," + Arrays.toString(eventActivity.getInfoEventActivity().split("\n")).replace(", ", "|").replace("[", "").replace("]", "") + "," + eventActivity.getDateStartEventActivity() + "," + eventActivity.getDateEndEventActivity() + "," + eventActivity.getTimeStartEventActivity() + "," + eventActivity.getTimeEndEventActivity();
                buffer.append(line);
                buffer.append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                buffer.flush();
                buffer.close();
            }
            catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }
}

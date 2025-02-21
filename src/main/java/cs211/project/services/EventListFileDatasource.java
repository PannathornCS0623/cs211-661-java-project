package cs211.project.services;

import cs211.project.models.Event;
import cs211.project.models.collections.EventList;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public class EventListFileDatasource implements Datasource<EventList>{
    private String directoryName;
    private String fileName;

    public EventListFileDatasource(String directoryName, String fileName) {
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
    public EventList readData() {
        EventList events = new EventList();
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
                String username = data[0].trim();
                String name = data[1].trim();
                int joinMember = Integer.parseInt(data[2].trim());
                int member = Integer.parseInt(data[3].trim());
                String info = data[4].trim().replace("|","\n");
                String getDateStartEvent = data[5].trim();
                LocalDate dateStartEvent = LocalDate.parse(getDateStartEvent);
                String getDateEndEvent = data[6].trim();
                LocalDate dateEndEvent = LocalDate.parse(getDateEndEvent);
                String getTimeStartEvent = data[7].trim();
                LocalTime timeStartEvent = LocalTime.parse(getTimeStartEvent);
                String getTimeEndEvent = data[8].trim();
                LocalTime timeEndEvent = LocalTime.parse(getTimeEndEvent);
                String getDateStartJoin = data[9].trim();
                LocalDate dateStartJoin = LocalDate.parse(getDateStartJoin);
                String getDateEndJoin = data[10].trim();
                LocalDate dateEndJoin = LocalDate.parse(getDateEndJoin);
                String getTimeStartJoin = data[11].trim();
                LocalTime timeStartJoin = LocalTime.parse(getTimeStartJoin);
                String getTimeEndJoin = data[12].trim();
                LocalTime timeEndJoin = LocalTime.parse(getTimeEndJoin);
                String imagePath = data[13].trim();
                String statusJoin = data[14].trim();

                // เพิ่มข้อมูลลงใน list
                events.addNewEvent(username, name, joinMember, member, info,
                                   dateStartEvent, dateEndEvent, timeStartEvent, timeEndEvent,
                                   dateStartJoin, dateEndJoin, timeStartJoin, timeEndJoin, imagePath, statusJoin);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return events;
    }



    @Override
    public void writeData(EventList data) {
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
            for (Event event : data.getEvents()) {

                String line = event.getUsername() + "," + event.getNameEvent() + "," + event.getJoinMemberEvent() + "," + event.getMemberEvent() + "," + Arrays.toString(event.getInfoEvent().split("\n")).replace(", ", "|").replace("[", "").replace("]", "") + "," +
                              event.getDateStartEvent() + "," + event.getDateEndEvent() + "," + event.getTimeStartEvent() + "," + event.getTimeEndEvent() + "," +
                              event.getDateStartJoin() + "," + event.getDateEndJoin() + "," + event.getTimeStartJoin() + "," + event.getTimeEndJoin() + "," + event.getPath() + "," + event.getStatus();
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

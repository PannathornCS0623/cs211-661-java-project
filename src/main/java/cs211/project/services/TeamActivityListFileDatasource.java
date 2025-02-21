package cs211.project.services;

import cs211.project.models.TeamActivity;
import cs211.project.models.collections.TeamActivityList;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class TeamActivityListFileDatasource implements Datasource<TeamActivityList> {
    private String directoryName;
    private String fileName;

    public TeamActivityListFileDatasource(String directoryName, String fileName) {
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }

    // ตรวจสอบว่ามีไฟล์ให้อ่านหรือไม่ ถ้าไม่มีให้สร้างไฟล์เปล่า
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
    public TeamActivityList readData() {
        TeamActivityList teamActivities = new TeamActivityList();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        // เตรียม object ที่ใช้ในการอ่านไฟล์
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
                String nameEvent = data[0].trim();
                String nameTeam = data[1].trim();
                String nameTeamActivity = data[2].trim();
                String infoTeamActivity = data[3].trim().replace("|", "\n");
                String teamActivityStatus = data[4].trim();


                // เพิ่มข้อมูลลงใน list
                teamActivities.addNewTeamActivity(nameEvent,nameTeam,nameTeamActivity,infoTeamActivity,teamActivityStatus);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return teamActivities;
    }


    @Override
    public void writeData(TeamActivityList data) {
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
            // สร้าง csv ของ Student และเขียนลงในไฟล์ทีละบรรทัด
            for (TeamActivity teamActivity: data.getTeamActivities()) {
                String line = teamActivity.getNameEvent() + "," + teamActivity.getNameTeam() + "," + teamActivity.getNameTeamActivity() + "," + Arrays.toString(teamActivity.getInfoTeamActivity().split("\n")).replace(", ", "|").replace("[", "").replace("]", "") + "," + teamActivity.getTeamActivityStatus();
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
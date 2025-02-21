package cs211.project.services;

import cs211.project.models.Team;
import cs211.project.models.collections.TeamList;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;

public class TeamListFileDatasource implements Datasource<TeamList> {
    private String directoryName;
    private String fileName;

    public TeamListFileDatasource(String directoryName, String fileName) {
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
    public TeamList readData() {
        TeamList teams = new TeamList();
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
                int joinMemberTeam = Integer.parseInt(data[2]);
                int memberTeam = Integer.parseInt(data[3]);
                String getDateStartTeam = data[4].trim();
                LocalDate dateStartTeam = LocalDate.parse(getDateStartTeam);
                String getDateEndTeam = data[5].trim();
                LocalDate dateEndTeam = LocalDate.parse(getDateEndTeam);
                String getTimeStartTeam= data[6].trim();
                LocalTime timeStartTeam = LocalTime.parse(getTimeStartTeam);
                String getTimeEndTeam = data[7].trim();
                LocalTime timeEndTeam = LocalTime.parse(getTimeEndTeam);

                // เพิ่มข้อมูลลงใน list
                teams.addNewTeam(nameEvent, nameTeam, joinMemberTeam,memberTeam,dateStartTeam,dateEndTeam,timeStartTeam,timeEndTeam);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return teams;
    }


    @Override
    public void writeData(TeamList data) {
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
            for (Team team : data.getTeams()) {
                String line = team.getNameEvent() + "," + team.getNameTeam() + ","+team.getJoinMemberTeam()+ "," + team.getMemberTeam() + "," + team.getDateStartTeam()+"," + team.getDateEndTeam() +"," + team.getTimeStartTeam() +"," + team.getTimeEndTeam() ;
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
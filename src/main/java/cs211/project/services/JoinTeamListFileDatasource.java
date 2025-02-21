package cs211.project.services;


import cs211.project.models.collections.JoinTeamList;
import cs211.project.models.participants.JoinTeam;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class JoinTeamListFileDatasource implements Datasource<JoinTeamList>{
    private String directoryName;
    private String fileName;

    public JoinTeamListFileDatasource(String directoryName, String fileName) {
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
    public JoinTeamList readData() {
        JoinTeamList joinTeams = new JoinTeamList();
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
                String nameEvent = data[0].trim();
                String nameTeam = data[1].trim();
                String username = data[2].trim();
                String teamStatus = data[3].trim();

                // เพิ่มข้อมูลลงใน list
                joinTeams.addNewJoinTeam(nameEvent, nameTeam, username, teamStatus);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return joinTeams;
    }



    @Override
    public void writeData(JoinTeamList data) {
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
            for (JoinTeam joinTeam : data.getJoinTeams()) {
                String line = joinTeam.getNameEvent() + "," + joinTeam.getNameTeam() + "," + joinTeam.getUsername() + "," + joinTeam.getTeamStatus();
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

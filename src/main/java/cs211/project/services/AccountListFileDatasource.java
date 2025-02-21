package cs211.project.services;

import cs211.project.models.Account;
import cs211.project.models.collections.AccountList;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;

public class AccountListFileDatasource implements Datasource<AccountList> {
    private String directoryName;
    private String fileName;

    public AccountListFileDatasource(String directoryName, String fileName) {
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
    public void writeData(AccountList data) {
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
                StandardCharsets.UTF_8 // อ่านภาษาไทย
        );
        BufferedWriter buffer = new BufferedWriter(outputStreamWriter);

        try {
            // สร้าง csv ของ Student และเขียนลงในไฟล์ทีละบรรทัด
            for (Account account : data.getUsers()) {
                String line = account.getName() + "," + account.getUsername() + "," + account.getPassword() + "," + account.getRole() + "," +
                              account.getBio() + "," + account.getPath() + "," + account.getDate() + "," + account.getTime();
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

    @Override
    public AccountList readData() {
        AccountList users = new AccountList();
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
            while ((line = buffer.readLine()) != null) { // java ทำได้
                // ถ้าเป็นบรรทัดว่าง ให้ข้าม
                if (line.equals("")) continue;

                // แยกสตริงด้วย ,
                String[] data = line.split(",");

                // อ่านข้อมูลตาม index แล้วจัดการประเภทของข้อมูลให้เหมาะสม
                String name = data[0].trim();
                String username = data[1].trim();
                String password = data[2].trim();
                String role = data[3].trim();
                String bio = data[4].trim();
                String imagePath = data[5].trim();
                String getDate = data[6].trim();
                LocalDate date = LocalDate.parse(getDate);
                String getTime = data[7].trim();
                LocalTime time = LocalTime.parse(getTime);

                // เพิ่มข้อมูลลงใน list
                users.addNewUserFromFile(name, username, password, role, bio, imagePath, date, time);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

}
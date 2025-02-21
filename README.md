# CS211-661-Project
> Project ภาคต้น 2566

## Project detail
https://saacsos.notion.site/Project-CS211-2566-1c8e195c0ebd4071aea8f733692e3989?pvs=4

---

> วิธีการติดตั้งหรือรันโปรแกรม
1. เปิด command prompt ขึ้นและรันคำสั่ง git clone https://github.com/CS211-661/cs211-661-project-java-jaba.git ในโฟลเดอร์ที่ต้องการติดตั้ง
2. โปรแกรมนี้เป็นโปรแกรมที่รันได้ใน Window-Version
3. ภายในโฟลเดอร์ทั้งหมดจะมีโฟลเดอร์ชื่อ java-jaba Program ที่มีไว้ให้ผู้ใช้ได้เข้าโปรแกรม สามารถนำโฟลเดอร์นี้ไปวางไว้ส่วนไหนก็ได้ของคอมพิวเตอร์
4. เมื่อเข้าโฟลเดอร์ java-jaba Program ให้กด Double-click ที่โปรแกรม The Creator (หาไม่ต้องการให้มีข้อมูลเบื่องต้น ในกรณีต้องการทดสอบตั้งแต่เริ่มเอง สามารถลบโฟลเดอร์ชื่อ data ในโฟลเดอร์ jaba-java Program ได้) โดยคู่มือการใช้งานจะอยู่ในตัวโปรแกรมหน้าแรกที่ปุ่ม manual


> ตัวอย่างข้อมูลผู้ใช้ระบบ (username, password)
##### (Admin) Username : Admin, Password : admin123
##### (Creator) Username : Gameza22, Password : 22
##### (Creator) Username : Beagle5, Password : 5
##### (Creator) Username : Casper0, Password : 0
##### (User) Username : Sibie08, Password : 08
##### (User) Username : Rainbow7, Password : 7
##### (User) Username : johny123, Password : 123
##### (User) Username : teenoi999, Password : 999
##### (User) Username : puthi777, Password : 777

> การวางโครงสร้างไฟล์ (บอกว่า folder ใน repository แต่ละ folder เก็บอะไร)
1. cs211-661-project-java-jaba/data/
   1. account.csv : เก็บข้อมูลของผู้ใช้ทุกคน รวมถึงข้อมูลของผู้ดูแลระบบด้วยเช่นกัน
   2. comment.csv : เก็บข้อมูลทั้งหมดของการสื่อสารภายในกิจกรรมของทีมผู้ร่วมจัดอีเวนต
   3. event.csv : เก็บข้อมูลทั้งหมดของทุกอีเวนต์
   4. event-activity.csv : เก็บข้อมูลทั้งหมดของทุกกิจกรรมภายในอีเวนต์
   5. team.csv : เก็บข้อมูลทั้งหมดของทุกทีมผู้ร่วมจัดอีเวนต์
   6. team-activity.csv : เก็บข้อมูลทั้งหมดของทุกกิจกรรมภายในทีมผู้ร่วมจัดอีเวนต์


2. cs211-661-project-java-jaba/data/images/
   1. accounts : เก็บข้อมูลรูปภาพของผู้ใช้ทุกคน รวมถึงข้อมูลของผู้ดูแลระบบด้วยเช่นกัน
   2. events : เก็บข้อมูลรูปภาพทั้งหมดของทุกอีเวนต์


3. cs211-661-project-java-jaba/data/participants/
   1. join-event.csv : เก็บข้อมูลทั้งหมดของการเข้าร่วมอีเวนต์
   2. join-team.csv : เก็บข้อมูลทั้งหมดของการเข้าร่วมทีมผู้จัดอีเวนต์


4. cs211-661-project-java-jaba/src/main/java/cs211/project/controllers/
   1. โค้ดทั้งหมดที่กำหนดการทำงานที่ส่งค่าไปแสดงผลที่ View


5. cs211-661-project-java-jaba/src/main/java/cs211/project/cs211611project/
   1. ส่วนของการเริ่มต้นการทำงานโปรแกรม


6. cs211-661-project-java-jaba/src/main/java/cs211/project/models/
   1. ส่วนการทำงานที่สร้างออบเจ็กต์และฟังก์ชันการแก้ไขหรือคำนวณข้อมูลของคลาส


7. cs211-661-project-java-jaba/src/main/java/cs211/project/models/collections/
   1. ส่วนจัดการข้อมูลของออบเจ็กต์จากโมเดล

    
8. cs211-661-project-java-jaba/src/main/java/cs211/project/models/participants/
   1. ส่วนการทำงานที่สร้างออบเจ็กต์และฟังก์ชันการแก้ไขหรือคำนวณข้อมูลของคลาส ในส่วนของการเข้าร่วมต่างๆ


9. cs211-661-project-java-jaba/src/main/java/cs211/project/services/
   1. ส่วนการทำงานข้อมูลของระบบพื้นหลังต่างๆ ที่ช่วยในส่วนของ controllers


10. cs211-661-project-java-jaba/src/main/resources/cs211/project/views/
    1. ส่วนของ FXML ที่จัดการหน้าตาของแอปพลิเคชัน


11. cs211-661-project-java-jaba/src/main/resources/cs211/project/views/members/
    1. ข้อมูลรูปภาพของผู้พัฒนาแอปพลิเคชัน


12. cs211-661-project-java-jaba/src/main/resources/cs211/project/views/Style/
    1. ข้อมูลการตกแต่งหน้าตาของแอปพลิเคชัน


13. cs211-661-project-java-jaba/src/main/resources/default-image/
    1. ข้อมูลรูปภาพ default ของผู้ใช้และอีเวนต์


> สรุปสิ่งที่พัฒนาในแต่ละครั้งที่ส่งความก้าวหน้าของระบบ และครั้งที่ส่งโครงงานที่สมบูรณ์ โดยสรุปเป็นรายบุคคล

###### (6510451026 - นาย อควอรัตน์ ธิติวุฒิกร, ซัน)
###### (6510450771 - นางสาว ภัฎฎารินธ์ ไฝ่ทอง, บุ๊ค)
###### (6510450623 - นาย ปัณณธร โพธิ์เงิน, เกมส์)
###### (6510450470 - นางสาว ธัญชนก หอมหวล, ปันปัน)


1. ความก้าวหน้าของระบบครั้งที่ 1
    1. 6510451026
       1. สร้างหน้า SignIn, Register, EditEvent, EditEventActivity, EditTeam (รวม TeamActivity)
       2. ปุ่ม (มีแค่การข้ามหน้าเท่านั้น)
    2. 6510450771
       1. สร้าง CreateEventController / Event / EventList
       2. สร้าง CreateEventOwnerController
    3. 6510450623
       1. สร้างหน้า EventList(ปัจจุบันเป็นHomeEvent), History, ProfileUser(ปัจจุบันเป็นProfileAccount), EditProfileUser(ปัจจุบันเป็นEditProfileAccount), EventInfo(ปัจจุบันเป็นJoinEvent)
       2. ปุ่มของหน้าข้างต้น (มีแค่การข้ามหน้าเท่านั้น)
    4. 6510450470
       1. สร้างหน้า TeamList(ปัจจุบันเป็นEditTeam) และ JoinUserTeam(ปัจจุบันเป็นJoinTeamActivity)
       2. สร้างปุ่มการข้ามหน้า และการส่ง comment

2. ความก้าวหน้าของระบบครั้งที 2
    1. 6510451026 
       1. สร้าง Model Account กำหนด Attributes, Constructors, Methods, Getters 
       2. สร้าง Collections AccountList มีการทำงาน การ addNew และฟังก์ชันอื่นๆ เบื้องต้น
       3. สร้าง SignInController อ่านข้อมูลและมีการทำงานของการล็อกอินเบื้องต้น
       4. สร้าง RegisterController อ่านข้อมูลและมีการทำงานของการสมัครใหม่เบื้องต้น
       5. สร้าง CreateEventListController (CreateEventOwnerController) อ่านข้อมูลและมีการทำงานแสดง EventList และกดเลือกแล้วส่งข้อมูลข้ามหน้า
       6. สร้าง EditEventActivityController อ่านข้อมูลและมีการทำงานแสดง EventActivityList เพิ่มกิจกรรมอีเวนต์และกดเลือกเพื่อลบข้อมูล
       7. สร้าง ProfileUserController (ProfileAccountController) อ่านข้อมูลและแสดงข้อมูลของผู้ใช้
       8. สร้าง EditProfileUserController (EditProfileAccountController) อ่านข้อมูลและแก้ไขข้อมูลเบื้องต้น
   2. 6510450771
   
      สร้าง models>>Event สร้าง Field constructor method หลักๆ ที่ต้องมีใน Event model
      สร้าง collections>>EventList สร้าง method หลักๆ คือ addNewEvent() findEventByNameEvent() editEvent()
      เชื่อม fxml + controller + model เชื่อมทุกอย่างเข้าด้วยกันโดยมีการสร้าง Datasource เพื่อเชื่อมกับ  csv file / เชื่อมปุ่มใน controller ให้มีการทำงาน
      ส่งข้อมูลข้ามหน้า และบันทึกข้อมูลได้
       1. CreateEventController + create-event.fxml + Event + EventList + EventListFileDatasource + event.csv
       2. CreateEventOwnerController + create-event-owner.fxml + Event + EventList + EventListFileDatasource + event.csv
       3. EditEventActivityController + edit-event-activity.fxml + EventActivity+ EventActivityList + EventListActivityFileDatasource +
          event-activity.csv
    3. 6510450623
       1. สร้าง Model Team ที่มีการทำหนด Attributes, Constructors, Methods, Getters, Setters
       2. สร้าง Collection TeamList มีการทำงาน addNew และฟังก์ชันอื่นๆ เบื่องต้น
       3. สร้าง Model TeamActivity ที่มีการกำหนด Attributes, Constructors, Methods, Getters
       4. สร้าง Collection TeamActivityList มีการทำงาน addNew และฟังก์ชันอื่นๆ เบื่องต้น
       5. สร้าง ListTeamController(ปัจจุบันเป็นEditTeam) ฟังก์ชันอ่านข้อมูลและแสดง
       6. สร้าง EditTeamController ฟังก์ชันอ่านข้อมูลและแสดง
       7. สร้าง EditTeamActivityController ฟังก์ชันอ่านข้อมูลและแสดง, ปุ่ม add เพิ่มทีมใหม่
    4. 6510450470
       1. สร้าง Model Comment ที่มีการกำหนด Attributes, Constructors, Method, Getters
       2. สร้าง Collections CommentList และฟังก์ชันเบื้องต้น
       3. สร้าง JoinUserTeamController(ปัจจุบันเป็นJoinTeamActivity) มี initialize อ่านข้อมูลและแสดงข้อมูลในลิสต์ผ่านฟังกชั่นการ show

3. ความก้าวหน้าของระบบครั้งที 3
    1. 6510451026
       1. ปรับปรุงการทำงานระบบ LogIn และการ Register ด้วยการใช้ Bcrypt (hash password) 
       2. สร้าง RegisterImageController การทำงานรูปภาพของผู้ใช้เมื่อสมัคร
       3. ปรับปรุง HomeEventController มีการแสดง EventList และกดเลือกเพื่อแสดงข้อมูล
       4. ปรับปรุง JoinEventController มีการแสดงข้อมูลของอีเวนต์และการเข้าร่วมอีเวนต์
       5. ปรับปรุง JoinEventActivity มีการแสดง EventActivityList
       6. ปรับปรุง AdminController มีการแสดง UserList
       7. ปรับปรุง EditProfileAccount การแก้ไขข้อมูลและแสดงข้อมูล (ในหน้า ProfileAccount)
    2. 6510450771
       1. tableview showUserList + banUser >> เพิ่ม status ของ account จาก true -> false เพิ่ม condition ในหน้า JoinEventActivityController
          ไม่ให้  user ที่ถูก ban มองเห็น listView
       2. tableview showTeamUserList
    3. 6510450623
       1. ปรับปรุง EditTeamActivityController เพิ่มปุ่ม delete ลบกิจกรรมทีมผู้จัดอีเวนต์
       2. ปรับปรุง JoinTeamController มีการเช้าทีมที่สอดคล้องกับผู้ใช้
       3. ปรับปรุง ProfileAccountController เพิ่มปุ่ม Edit Image ไว้เลือกรูปโปรไฟล์ของผู้ใช้จากเครื่องผู้ใช้
    4. 6510450470
       1. ปรับปรุง EditTeamActivityController มีการแก้ไขทีมผู้จัดอีเวนต์และกิจกรรมของทีมผู้จัดอีเวนต์ให้สอดคล้องกับผู้ใช้, ฟังก์ชันระงับสิทธื์ทีมผู้จัดอีเวนต์
       2. ปรับปรุง EditTeamController มีการเข้ากิจกรรมทีมที่สอดคล้องกับทีมผู้จัดอีเวนต์, ฟังก์ชันการสร้างทีมผู้จัดอีเวนต์
       3. ปรับปรุง Collections CommentList มีฟังก์ชันการเพิ่ม comment 
       4. ปรับปรุง JoinTeamActivityController มีการเข้ากิจกรรมของทีมผู้จัดอีเวนต์ที่สอดคล้องกับผู้ใช้, มีการเพิ่มและแก้ฟังก์ชันการแสดงความคิดเห็นอย่างสมบูรณ์

4. โครงงานที่สมบูรณ์
    1. 6510451026
       1. ปรับปรุง AdminController มีการแสดงข้อมูลของผู้ใช้และเรียงตามวันเวลาที่เข้าใช้ล่าสุด
       2. ปรับปรุง HomeEventController มีการพิมพ์ค้นหาอีเวนต์ด้วยบางส่วนของชื่ออีเวนต์และเพิ่มเติมการแสดงข้อมูลอีเวนต์ที่เลือก
       3. ปรับปรุง JoinEventActivityController เพิ่มเติมการแสดงข้อมูล
       4. ปรับปรุง JoinEventController พัฒนาระบบการเข้าร่วมอีเวนต์และการแสดงข้อมูล (เรื่องวันเวลา)
       5. สร้าง ManualController ข้อมูลคำแนะนำการใช้งานโปรแกรม
       6. ปรับปรุง RegisterController พัฒนาระบบการสมัครให้มีความปลอดภัยและใช้งานง่ายขึ้น
       7. ปรับปรุง SignInController พัฒนาระบบการล็อกอินให้มีความปลอดภัยและใช้งานง่ายขึ้น
       8. เพิ่มเติมการทำงานในส่วนของ Collections, Models (วัน, เวลา, filter, method(s))
    2. 6510450771
       1. เพิ่มการใส่รูปในหน้า homeEvent/joinEvent/CreateEvent
       2. เพิ่มการ delete ActivityList ของ User/Team
       3. เพิ่ม finish Activity Team จัดการการ showActivity ในหน้า activityTeam
       4. ตกแต่ง Program ให้สวยงาม
       5. จัดการ/เพิ่ม ใน csv file
    3. 6510450623
       1. เขียนและปรับปรุง UML class diagram
       2. ออกแบบ และตกแต่งโปรแกรม โดยใช้ css
       3. ปรับปรุง HistoryController มีการแยกประเภทและแสดงอีเวนต์อย่างถูกต้องสมบูรณ์
       4. เพิ่มและจัดการข้อมูลไฟล์ .csv
    4. 6510450470
       1. ปรับปรุง JoinTeamController แก้ไขเวลาให้ถูกต้อง และส่งข้อมูลตารางกิจกรรมแสดงให้ชัดเจน 
       2. สร้างหน้า MemberController
       3. เขียนและปรับปรุง UML class diagram
       4. ออกแบบ และตกแต่งโปรแกรม โดยใช้ css
       5. ช่วยเพิ่มข้อมูลไฟล์ csv บางส่วน



#   g o l d - f i s h  
 #   c s 2 1 1 - 6 6 1 - j a v a - p r o j e c t  
 
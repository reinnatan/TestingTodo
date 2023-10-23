# TestingTodo
 - untuk dapat mejalankannya terlebih dahulu melakukan setup devices yang mau dijalankan, dan melakukan configurasi di desire capabilities di file Android Driver manager dengan key ```udid```
 - untuk mengentahui nama devices dapat menggunkan perintah ```adb devices```, apabila device anda telah terdaftar anda dapat melakukan di file desire capabilities dengan key uuid, seperti yang dijalankan diatas
 - sebelum menjalankan test case dapat menjalankan appium server terlebih dahulu dengan remote path ```/wd/hub```
 - untuk menjalankan test case dapat melakukan perintah ```mvn clean test -DsuiteXmlFile=testng.xml```
 - sedangkan untuk reporting hasil testcase dapat dilihat di directory ```test-ouput``` di file ```index.html``` atau file ```emaillable-report.html```

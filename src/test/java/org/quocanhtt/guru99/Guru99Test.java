/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quocanhtt.guru99;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import static org.openqa.selenium.By.id;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author at050
 */
public class Guru99Test {

    private static WebDriver myBrowser; // biến trỏ vào 1 tab trình duyệt

    @BeforeAll
    //hàm này là static nên chỉ chạy 1 lần duy nhất
    //nó sẽ chạy đầu tiên trước tất cả các hàm mà có @Test (hàm test chính của mình)
    //nó thường dùng để khởi động các biến object, cbi data
    //sẵn dùng cho các hàm @Test sau này
    public static void setUpClass() {
        //ta sẽ new trình duyệt ở đây
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        myBrowser = new ChromeDriver();
        myBrowser.manage().window().maximize();
    }

    @Test
    //code test các hàm, các chức năng của app nằm ở đây
    //gây ra rác trong DB. HDD/SSD do quá trình test app thì có thay đổi (thêm bớt data)
    // -- TEST CASE -- functional testing, ko care viet code the nao
    // chỉ quan tâm app chạy và trã về giá trị kì vọng trên web
    //đưa vào account hợp lệ, nhấn login, thì phải trả vè dashboard
    //có câu chào Manger ID: <user ID>
    //JUnit lúc này đang đóng vai trò so sánh kì vọng và trả về app/page
    public void checkLoginGivenValidAccountShowWelcomeMsg() throws InterruptedException {
        myBrowser.get("https://www.demo.guru99.com/V4/"); //mở trang web

        //tìm các control/tag: ô nhập userid, password, nhấn nút login
        //để tìm đc thẻ trong RAM của trình duyệt
        //              nằm phía source của trang
        //              của cây DOM - Document object Model
        //              cây html
        //các nhà thiết kế trình duyệt đã cbi sẵn 1 nhóm các loại
        //câu query để query 1 tag
        //SQL------------query data đang trong database
        //selector query -- query các tag nằm phía sau trình duyệt
        //có nhìu loai câu query để tìm đc 1 tag 
        //query theo: name, id, class, đặc tính css của tag (css selector)
        //      Xpath query - câu text viết theo cú pháp có sẵn
        //      Để duyệt cây DOM
        //Tool/EXT selector hub mình cài trong chrome giúp mình generate/
        //các cau query trên tiện dùng!! nhanh hơn tiện hơn
        //so vs F12 mặc định
        //Tìm ô nhập userId
        
        String username = "mngr451891";
        String password = "yrAtamA";

        WebElement txtUsername = myBrowser.findElement(By.cssSelector("input[name='uid']"));
        txtUsername.sendKeys(username);

        WebElement txtPassword = myBrowser.findElement(By.cssSelector("input[name='password']"));
        txtPassword.sendKeys(password);

        WebElement btnLogin = myBrowser.findElement(By.cssSelector("input[value='LOGIN']"));
        btnLogin.click();

        //Login thành công, thì phải chuyển trang, có đc message
        //manger id: <user ID>
        
        //khi chuyển trang, tuỳ mạng tại thời điểm chuyển trang
        //mà trang có thể về browser đủ nhanh hay chậm
        //nhanh thì ko sao
        //chậm thì lện sau tìm thẻ ở trang mới sẻ ko tìm thấy do chưa kịp tải trang về
        //báo exception lên là: NoSuchElementExeption
        //để đảm bảo luôn tìm thấy tag ở trang mới, ta cần wait 1 xíu trc khi tìm tag ở trang mới
        Thread.sleep(3000);// đồng bộ trang, đợi browser có trang mới
        
        WebElement lblWellcomeMsg = myBrowser.findElement(By.cssSelector("tr[class='heading3'] td"));
        //in lời chào nếu login thành công! 
        // ta lấy cái value trong cái cell của table lấy text của thẻ td
        //hàm getText của 1 web element
        
        //System.out.println("Wellcome msg: " + lblWellcomeMsg.getText());
        
        //bạn vừa viết 1 con bot CRAWLER để đi cào data của trang web

        //So sánh xanh đỏ
        assertEquals("Manger Id : " + username, lblWellcomeMsg.getText());
        Thread.sleep(3000);
    }

    @AfterAll
    //hàm này thì ngược lại so vs @BeforeAll
    //nó chỉ chạy 1 lần duy nhất, chạy sau cùng tất cả
    //nó thươc2 dùng để dọn dẹp các biến object, đóng các kết nối
    //clear các data được chèn vào trong @TEst 
    public static void tearDownClass() {
        //đóng trình duyệt ở đây !!
        myBrowser.quit();
    }

}

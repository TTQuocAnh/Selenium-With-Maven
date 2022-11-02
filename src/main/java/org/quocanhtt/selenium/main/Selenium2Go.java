/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quocanhtt.selenium.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 *
 * @author at050
 */
public class Selenium2Go {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Please wait 3s for lauching the browser form terminal...");
        Thread.sleep(3000);
        playWithGoogle();

    }
    // ---------
    // điều khiển trình duyệt bằng code 
    //test web app bằng code 
    // ---------

    public static void playWithGoogle() throws InterruptedException {

        // 1. Khai báo biến object để trỏ vào tab của trình duyệt sẽ đc new() 
        // nếu trỏ vào đc 1 cái tab >> ta sẽ điều khiển đc trình duyệt thông qua gọi method.
        WebDriver myBrowser; // new sau

        // 2. báo vs máy ảo Java có 1 file driver.exe 
        // cái file driver này chứa các class và nó sẽ giúp tương tác vs các tab trình duyệt 
        // Diver là phần trung gian giúp kết nối 2 hệ thống khác nhau.
        // Giống driver card đồ hoạ giúp kết nối OS và phần cứng - graphic card.
        // Driver.exe ~~ Driver.class ~~ driver.dll
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        // lệnh này giống như lệnh class.forname(...) JDBC của PRJ

        //ta làm thêm vài trò cấu hình trước khi trình duyệt xuất hiện ra màn hình
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--lang=ja-JP");
        opt.addArguments("--incognito");

        //trình duyệt ơi xuất hiện, new 1 object trong HEAP RAM
        myBrowser = new ChromeDriver(opt);

        //mặc định trình duyệt d0c new() sẽ 1/2 màn hình 
        //click góc phải để full màn hình 
        //thực chất click full màn hình chính là gọi method của object trình duyệt mybrowser
        myBrowser.manage().window().maximize();
        myBrowser.get("https://google.com");
        
        //định vị searchbox để nhập keyword
        //mybrowser hiện đang nắm giữ toàn bộ các tag của trang web
        //các trình duyệt khi đc thiết kế ngta đã tính sẵn các cách thức 
        //để locate /xác định 1 tag bất kì
        //gọi là kỹ thuật định vị thẻ ~~ câu query
        //selector ra các tag
        //có các query sau: theo id, cssselector, Xpath,...
        //Xpath là mạnh nhất, lun tìm đc 1 tag bất kì trong các tag lồng nhau
        
        //MyBrowser sẽ nắm giữ các tag của  trang web đc tải về 
        //nó cũng hỗ trợ các câu query  để tìm tag
        //nó cũng hỗ trợ can thiệp vào hoạt động của từng tag
        //click trên tag, input data vào tag
        //MỖI TAG ĐC XEM LÀ 1 OBJECT THUỘC CLASS CỦA WEBELEMENT 

        WebElement lblSearch = myBrowser.findElement(By.name("q"));
        lblSearch.sendKeys("Ca sĩ mặt nạ");
        lblSearch.submit();

        //ép CPU ngưng đọng trong 1 khoảng tg
        Thread.sleep(10000); // 1000~~1s

        //dọn dẹp trình duyệt/tắt trình duyệt tử tế để ko bị rác trong ram myBrowser.quit()
        myBrowser.quit();

        //JUnit/Unit Test: setup                 &          Teardown
        //                  new trinh duyệt             tắt trình duyệt
    }
}

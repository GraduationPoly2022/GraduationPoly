package com.shop.controller;

import com.shop.dto.MailDto;
import com.shop.dto.ResponseMessage;
import com.shop.enumEntity.StatusMessage;
import com.shop.helper.HandleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cookie")
public class CookieController {
    @Autowired
    private HandleCookie handleCookie;

    //Tạo Cookie
    @GetMapping("/")
    public ResponseEntity<ResponseMessage> setCookie() {
        List<MailDto> st = new ArrayList<>();
        MailDto mailDto = new MailDto();
        mailDto.setToMail("ngfbgnnmj");
        mailDto.setContentHtml("bfbfvsv");
        st.add(mailDto);

        MailDto mv = new MailDto();
        mv.setToMail("bfg");
        mv.setContentHtml("khalam");
        st.add(mv);

        MailDto cm = new MailDto();
        cm.setToMail("lam");
        cm.setContentHtml("kivb");
        st.add(cm);

        MailDto mb = new MailDto();
        mb.setToMail("long");
        mb.setContentHtml("okib");
        st.add(mb);
        this.handleCookie.add("testCookie", st);

        return ResponseEntity.ok(new ResponseMessage(StatusMessage.OK, "Tao Cookie thanh cong", null));
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseMessage> getCookie() {
        List<MailDto> st;
        st = this.handleCookie.get("testCookie", MailDto.class);
        return ResponseEntity.ok(new ResponseMessage(StatusMessage.OK, "Get Cookie thanh cong", st));
    }


//    //Xóa Cookie
//    @GetMapping("/delete-cookie")
//    public ResponseEntity deleteCookie(){
//        //Tạo cookie
//        ResponseCookie resCookie = ResponseCookie.from("user-id", null)
//                .build();
//        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,resCookie.toString()).build();
//    }
//
//    //Đọc Cookie
//    @GetMapping("/read-cookie")
//    public String readCookie(@CookieValue(name = "user-id", defaultValue = "default-user-id") String cookieName){
//        return String.format("Giá trị của Cookie với tên user-id là: %s", cookieName);
//    }
}

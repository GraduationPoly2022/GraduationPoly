package com.shop.controller;
import com.google.common.collect.Lists;
import com.shop.dto.MailDto;
import com.shop.dto.ResponseMessage;
import com.shop.enumEntity.StatusMessage;
import com.shop.helper.HandleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cookie")
public class CookieController {
    @Autowired
    private HandleCookie handleCookie;
    //Tạo Cookie
    @GetMapping("/")
    public ResponseEntity<ResponseMessage> setCookie(){
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
        this.handleCookie.add("okccv", st);

        return ResponseEntity.ok(new ResponseMessage(StatusMessage.OK,"Create add Cookie thanh cong", null));
    }
//    @GetMapping("/get")
//    public <T> ResponseEntity<ResponseMessage> getCookie(Class<T> aClass){
//        List<MailDto> st = new ArrayList<>();
//        MailDto mailDto = new MailDto();
//        mailDto.getToMail();
//        mailDto.getContentHtml();
//        this.handleCookie.get("oke", aClass);
//        return ResponseEntity.ok(new ResponseMessage(StatusMessage.OK,"get Cookie thanh cong", null));
//    }



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

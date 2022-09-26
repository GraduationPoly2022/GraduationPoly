package com.shop.services;

import com.shop.dto.MailDto;

import javax.mail.MessagingException;

public interface IMailService {

    void sendCodeConfirm(String toMail, String name, String codeConfirmation);

    void sendMailOrder(MailDto sendMailOrderDto) throws MessagingException;
}

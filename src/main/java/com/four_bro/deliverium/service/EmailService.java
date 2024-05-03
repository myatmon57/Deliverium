package com.four_bro.deliverium.service;

import com.four_bro.deliverium.model.EmailModel;

public interface EmailService {
  String sendSimpleMail(EmailModel details);
  String sendMailWithAttachment(EmailModel details);
}

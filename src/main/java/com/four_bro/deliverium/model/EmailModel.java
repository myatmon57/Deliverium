package com.four_bro.deliverium.model;

import lombok.Data;

// Annotations
@Data
public class EmailModel {

  private String recipient;
  private String msgBody;
  private String subject;
  private String attachment;
}

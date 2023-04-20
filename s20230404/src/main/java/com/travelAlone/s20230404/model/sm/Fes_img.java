package com.travelAlone.s20230404.model.sm;

import java.time.LocalDateTime;

import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

   @Getter
   @Setter
   @ToString
   public class Fes_img {
      private int img_id;
      private int festival_id;
      private String img_context;
      private String img_original_file;
      private String img_stored_file;
      private String common_imgesType;
      private LocalDateTime  create_date;
      private LocalDateTime  modified_date;

//IMG_ID					NUMBER					이미지 ID
//FESTIVAL_ID				VARCHAR2(50)			축제 ID
//IMG_CONTEXT				VARCHAR2(4000)			이미지 경로
//IMG_ORIGINAL_FILE			VARCHAR2(4000)			원본 파일 이름
//IMG_STORED_FILE			VARCHAR2(4000)			지정된 파일 이름
//COMMON_IMAGESTYPE			VARCHAR2(20)			이미지 타입
//CREATE_DATE				DATE					생성일
//MODIFIED_DATE				DATE					수정일
   
   }

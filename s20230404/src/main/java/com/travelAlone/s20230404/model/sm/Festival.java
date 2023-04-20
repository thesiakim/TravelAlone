package com.travelAlone.s20230404.model.sm;

import java.time.LocalDateTime;

import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

   @Getter
   @Setter
   @ToString
   public class Festival {
      private int festival_id;
      private String f_url;
      private LocalDateTime  create_date;
      private LocalDateTime  modified_date;
      
//FESTIVAL_ID				NUMBER					축제 ID
//F_URL						VARCHAR2(255)			축제 URL
//CREATE_DATE				DATE					생성일
//MODIFIED_DATE				DATE					수정일      
   }

   function delConfirm(board_id) {
       if (confirm("정말로 삭제하시겠습니까?")) {
             // 확인 버튼을 누른 경우 처리할 내용 작성
             var selBoard_id = board_id;
             
             $.ajax(
                   {
                      // controller 실행되기 전 setting
                      url : "deleteBoard",
                      type : 'POST',
                      data : {board_id : selBoard_id },
                      dataType : 'text',
                      
                      // data의 dataType은 'text'
                      success : function(data){
                         alert("삭제되었습니다");
                         console.log("삭제됨");
                          
                         if(parseInt(data) > 0){
                         location.href = "/listAllBoard";
                         }
                      }
                   }
             );          
         } else {
           // 취소 버튼을 누른 경우 처리할 내용 작성
           console.log("취소됨");
         }
     
      }
       
      function delReConfirm(b_re_step, b_re_level) {
         if (confirm("정말로 삭제하시겠습니까?")) {
             // 확인 버튼을 누른 경우 처리할 내용 작성
             
             var selB_ref = $('#b_ref').val();
             var selB_re_step = b_re_step
             var selB_re_level = b_re_level
             var selB_common_board = $('#b_common_board').val();
          
             $.ajax(
                 {
                     // controller 실행되기 전 setting
                     url : "deleteBoardRe",
                     type : 'POST',
                     data : {b_ref     : selB_ref, 
                            b_re_step : selB_re_step, 
                            b_re_level: selB_re_level 
                            },
                     dataType : 'text',
                    // data의 dataType은 'text'
                     success : function(data){
                        alert("삭제되었습니다");
                        location.href = ("/detailBoard?board_id="+selB_ref+"&b_common_board="+selB_common_board);
                   	 }
                 }
              );
         } else {
             // 취소 버튼을 누른 경우 처리할 내용 작성
             console.log("취소됨");
         }
     }
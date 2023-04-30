   function delConfirm() {
      alert("delConfirm start...");
      if (confirm("정말로 삭제하시겠습니까?")) {
         // 확인 버튼을 누른 경우 처리할 내용 작성
         var selBoard_id = $("#board_id").val();
         $.ajax({
            // controller 실행되기 전 setting
            url : "deleteBoard",
            data : {board_id : selBoard_id },
            dataType : 'text',
            // data의 dataType은 'text'
            success : function(data){
            alert("삭제되었습니다");
            console.log("삭제됨");
            if(parseInt(data) > 0){
               location.href = "/listAllBoard"
            }}
         });
      } else {
         // 취소 버튼을 누른 경우 처리할 내용 작성
         console.log("취소됨");
      }
   }

      function showRadio(button) {
          var form = document.getElementById("reportForm");
   
          if (form.style.display == "none") { 
            form.style.display = "block";
         } else {
            form.style.display = "none"; 
         }
      }
      document.getElementById('reportSubBtn').addEventListener('click', function(event) {
      event.preventDefault(); // 신고 버튼의 기본 동작을 막음
   var reportForm = document.getElementById('reportForm');
   var url = reportForm.getAttribute('action') + '?' + new URLSearchParams(new FormData(reportForm)).toString();
   // 확인, 취소 버튼 추가
   var confirmBox = document.createElement("div");
   confirmBox.style.cssText = "position:fixed; top:0; left:0; width:100%; height:100%; background-color: rgba(0,0,0,0.5); display:flex; justify-content:center; align-items:center;";
        
    var confirmMessage = document.createElement("div");
    confirmMessage.style.cssText = "background-color:#fff; padding:20px; border-radius:10px; text-align:center;";
    
    var confirmText = document.createElement("div");
    confirmText.innerText = '"${content.m_nickname}" 님을  정말로 신고 하시겠습니까?';
    confirmMessage.appendChild(confirmText);
    
    var confirmBtnContainer = document.createElement("div");
    confirmBtnContainer.style.cssText = "display:flex; justify-content:center; margin-top:10px;";
    
    var confirmBtn = document.createElement("button");
    confirmBtn.innerText = "확인";
    confirmBtn.style.cssText = "background-color:#205E61; color:white; border:none; padding:10px 20px; font-weight:bolder; font-size: 15px; border-radius:5px; margin-right:10px;";
      
    confirmBtn.addEventListener("mouseover", function() {
        confirmBtn.style.backgroundColor = "white";
        confirmBtn.style.color = "#205E61";
        confirmBtn.style.transition = "ease 0.5s";
        
    });
        
    confirmBtn.addEventListener("mouseout", function() {
        confirmBtn.style.backgroundColor = "#205E61";
        confirmBtn.style.color = "white";
   });
        
   confirmBtn.addEventListener("click", function() {
      var selectedRadio = document.querySelector('input[name="w_common_warning"]:checked');
      if (selectedRadio !== null) {
         reportForm.submit();
      }
      document.body.removeChild(confirmBox);
   });
   confirmBtnContainer.appendChild(confirmBtn);
        
   var cancelBtn = document.createElement("button");
   cancelBtn.innerText = "취소";
   cancelBtn.style.cssText = "background-color:#205E61;; color:white; border:none; padding:10px 20px; font-weight:bolder; font-size: 15px; border-radius:5px;";
         
   cancelBtn.addEventListener("mouseover", function() {
      cancelBtn.style.backgroundColor = "white";
      cancelBtn.style.color = "#205E61";
      cancelBtn.style.transition = "ease 0.5s";
            
   });
        
   cancelBtn.addEventListener("mouseout", function() {
      cancelBtn.style.backgroundColor = "#205E61";
      cancelBtn.style.color = "white";
   });
        
   cancelBtn.addEventListener("click", function() {
      document.body.removeChild(confirmBox);
   });
   confirmBtnContainer.appendChild(cancelBtn);
        
   confirmMessage.appendChild(confirmBtnContainer);
   confirmBox.appendChild(confirmMessage);
   document.body.appendChild(confirmBox);
});
 

function showRadio(button) {
   var form = document.getElementById("reportForm");
   if (form.style.display == "none") { 
      form.style.display = "block";
   } else {
      form.style.display = "none"; 
   }
}

function submitForm() {
    alert("정말로 신고 하시겠습니까?");
    var selBoard_id = $("#board_id").val();
    $.ajax({
        // controller 실행되기 전 setting
        url : "reportMember",
        data : {board_id : selBoard_id },
        dataType : 'text',
        // data의 dataType은 'text'
    });
}
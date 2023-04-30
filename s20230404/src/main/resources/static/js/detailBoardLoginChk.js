console.log(frm.member_id.value)
function chkId() {
   if (!frm.member_id.value || frm.member_id.value == '') {
      alert("로그인 후 사용 가능합니다.");
      location.href="login";
      return false;
   } else {
      console.log("로그인 확인 성공");
      return true;
   }
} 
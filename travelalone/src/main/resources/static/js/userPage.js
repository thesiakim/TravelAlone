	function imgOnclickCheck() {
        var checked = $('#imgOnlyCheckbox').is(':checked');
		if(checked) {
			 $(".img_stored_file_no").css("display", "none");
		} else {
			$(".img_stored_file_no").css("display", "");
		}
	}
	
	function getOrderList() { location.href= "/listBoard"; }
	
	
	function openUserPage(member_id) {
    	var editUrl = "/userpage?id="+member_id;
		var editWindow = window.open(editUrl, "edit_window", "width=550,height=650");
  	}
	
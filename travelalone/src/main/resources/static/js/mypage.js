	
	function openEditWindow() {
		// 수정창을 띄울 URL 설정
		var editUrl = "/mypage/member-info";
		// 미니창으로 수정창을 띄우기
		var editWindow = window.open(editUrl, "edit_window", "width=500,height=450");
	}
	
	function openWithdrawalWindow() {
		// 수정창을 띄울 URL 설정
		var editUrl = "/mypage/withdrawal";
		// 미니창으로 수정창을 띄우기
		var editWindow = window.open(editUrl, "edit_window", "width=500,height=400");
	}

	function openProfileWindow() {
		var editUrl = "/mypage/profile";
		// 미니창으로 수정창을 띄우기
		var editWindow = window.open(editUrl, "edit_window", "width=400,height=500");

	};

	function deleteProfile() {
		if(confirm("기본 이미지로 설정하시겠습니까?")){
			$.ajax({
				type: "POST",
				url: "/api/v1/mypage/profile/normal",
				dataType: "text",
				contentType: "application/json; charset=utf-8",
			})
				.done(function (responseText) {
					alert(responseText+"! 수정되었습니다.");
					window.location.reload();
				})
				.fail(function (error) {
					alert(JSON.stringify(error));
				});
			}
		};
		
	function openTagWindow() {
		var editUrl = "/mypage/tag";
		// 미니창으로 수정창을 띄우기
		var editWindow = window.open(editUrl, "edit_window", "width=400,height=500");

	};

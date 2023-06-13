	
	
	function saveButton() {
		var form = $('#form')[0];
		var formData = new FormData(form);
		if (document.getElementById('file').files.length>0){
			formData.append("file", document.getElementById('file').files[0]);
		}
		
		$.ajax({
			type: 'POST',
			url: '/api/v1/mypage/profile',
			processData: false,
			contentType: false,
			data: formData,
		})
		.done(function (responseText) {
			alert(responseText+"! 수정되었습니다.");
			window.close(); // 현재 창 닫기
			window.opener.location.reload(); // 부모 창 새로 고치기
		})
		.fail(function (error) {
			alert(JSON.stringify(error));
		});
	};

	function refresh() {
		var reader = new FileReader();
		reader.onload = function (e) {
			$('.profile-pic').attr('src', e.target.result);
		}
		reader.readAsDataURL(event.target.files[0]);
	}
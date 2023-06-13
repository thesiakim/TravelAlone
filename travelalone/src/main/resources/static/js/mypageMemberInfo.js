	
	function saveButton() {
		var data = {
			m_email: $("#m_email").val(),
			m_nickname: $("#m_nickname").val(),
			m_name: $("#m_name").val(),
			m_phone: $("#m_phone").val()
		};

		$.ajax({
			type: "POST",
			url: "/api/v1/mypage/info",
			dataType: "text",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(data),
		})
		.done(function () {
			alert("수정되었습니다.");
			window.close(); // 현재 창 닫기
			window.opener.location.reload(); // 부모 창 새로 고치기
		})
		.fail(function (error) {
			alert(JSON.stringify(error));
		});

	};
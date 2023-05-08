	function withdrawal(){
        var url = "/api/v1/mypage/withdrawal";
        var data = {
            memberEmail: $('#memberEmail').val(),
            password : $('#password').val()
        };

        $.ajax({
            type:"DELETE",
            url: url,
            dataType:"text",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data)
        })
            .done(function (responseText){
                alert(responseText+"님 정상적으로 탈퇴되었습니다. 다음에 또 뵈었으면 좋겠습니다. 감사합니다.");
                window.close();
                window.opener.location.href = "/login";
            })
            .fail(function (error) {
                alert(JSON.stringify(error));
            });
	}
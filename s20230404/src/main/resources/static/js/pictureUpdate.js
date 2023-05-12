var main = {
    init : function () {
        var _this = this;
        $('#btn-update').on('click', function () {
            _this.BoardUpdate();
        });

    },
    BoardUpdate : function () {
        var dataB = {
           board_id : $('#board_id').val(),
            member_id : $('#user_id').val(),
            b_title: $('#title').val(),
            b_content: $('#content').val(),
            b_common_board : $('#common_board').val()
        };
        
        var boardId = $('#board_id').val();
        var bCommonBoard = $('#common_board').val();
      
      // updateBoardForm에서 id="ImgMulForm" form태그의 타입인 multipart 형식을 formData에 적용 -> 사진 파일을 받기 위해
        var imgMulForm = $('#ImgMulForm')[0];
        var formData = new FormData(imgMulForm);

        var totalimgFiles = document.getElementById('imgFiles').files.length;
        for (var index = 0; index < totalimgFiles; index++) {
           
           // 이미지 파일들을 multipart 타입으로 formData에 넣기
           // "imgFile"은 key : document.getElementById('imgFiles').Files[index]는 value
            formData.append("imgFile", document.getElementById('imgFiles').files[index]);
        }
        
        // dataB에 있는 필드값들을 string에서 JSON 타입으로 변경 후 formData에 넣기
        // 'boardData'는 key값 : new Blob([JSON.stringify(dataB)]는 value값
        formData.append('boardData', new Blob([JSON.stringify(dataB)], {type: "application/json"}));

        $.ajax({
            type: 'POST',
            url: 'updateBoard',
            // multipart/form-data 타입의 byte코드들이 processData를 통해 json이나 String으로 변환되지 않기 위해 사용
            processData: false,
            // multipart/form-data 타입으로 보낼 수 있게 함
            contentType: false,
            data: formData,

        })
            .done(function () {
                alert("글이 등록되었습니다.");
                window.location.href = "/detailBoard?board_id="+boardId+"&b_common_board="+bCommonBoard;
            })
            .fail(function (error) {
                alert(JSON.stringify(error));
            });
    },

}
main.init();
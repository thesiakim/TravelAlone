var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.BoardSave();
        });

    },

    BoardSave : function () {
        var data = {
            b_title: $('#title').val(),
            b_content: $('#content').val(),
            b_common_board : $('#common_board').val()
        };

        var form = $('#form')[0];
        var formData = new FormData(form);

        var totalfiles = document.getElementById('files').files.length;
        for (var index = 0; index < totalfiles; index++) {
            formData.append("file", document.getElementById('files').files[index]);
        }
        formData.append('key', new Blob([JSON.stringify(data)], {type: "application/json"}));

        $.ajax({
            type: 'POST',
            url: 'writeBoard',
            processData: false,
            contentType: false,
            data: formData,

        })
            .done(function () {
                alert("글이 등록되었습니다.");
                window.location.href = "/listAllBoard";
            })
            .fail(function (error) {
                alert(JSON.stringify(error));
            });
    },

}

main.init();

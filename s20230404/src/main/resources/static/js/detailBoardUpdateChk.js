	function BoardReUpdate(indexStatus) {
    	if (confirm("수정하시겠습니까?")) {
        	var selBoard_id = $("#board_id" + indexStatus).val();
        	var selB_ref = 0;
	        var selB_common_board = "";
	        var selB_content = $("#b_content" + indexStatus).val();
	        console.log("board_id -> " + selBoard_id);
	        console.log("b_content -> " + selB_content);

	        const elements = document.querySelector('#bruForm').elements;
	        console.log("bruForm Start... ");
	
	        for (const item of elements) {
            	switch (item.name) {
                	case 'b_ref':
	                    selB_ref = item.value;
	                    console.log("b_ref -> " + selB_ref);
	                    break;
	                case 'b_common_board':
	                    selB_common_board = item.value;
	                    console.log("b_common_board -> " + selB_common_board);
	                    break;
	                default:
	                    break;
				}
			}
			
			$.ajax({
				url: "UpdateBoardRe",
	            type: 'POST',
	            data: {
	            	board_id: selBoard_id,
	                b_content: selB_content
	            },
				dataType: 'text',
	
				success: function(data) {
					if (parseInt(data) > 0) {
						location.href = ("/detailBoard?board_id=" + selB_ref + "&b_common_board=" + selB_common_board);
					}
				}
			});
		} else {
			console.log("취소");
		}
	}
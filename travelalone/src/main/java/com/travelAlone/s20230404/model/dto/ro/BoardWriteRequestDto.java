package com.travelAlone.s20230404.model.dto.ro;

import com.travelAlone.s20230404.model.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class BoardWriteRequestDto {

    private long member_id;
    private String b_title;
    private String b_content;
    private String b_common_board;

    @Builder
    public BoardWriteRequestDto(String b_title, String b_content, String b_common_board) {
        this.b_title = b_title;
        this.b_content = b_content;
        this.b_common_board = b_common_board;
    }

    public void addMemberId(long memberId) {
        this.member_id = memberId;
    }

    public Board toBoard(){
        return new Board(this.member_id,this.b_title, this.b_content, this.b_common_board);
    }
}

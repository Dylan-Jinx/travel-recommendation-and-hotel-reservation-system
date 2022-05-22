package se.xmut.trahrs.domain.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeDto {

    @ApiModelProperty("公告标题")
    private String noticeTitle;

    @ApiModelProperty("公告内容")
    private String content;

    @ApiModelProperty("公告发布人")
    private String noticeBy;

    @ApiModelProperty("公告类型")
    private Integer noticeType;

    @ApiModelProperty("公告排序")
    private Integer sort;

    @ApiModelProperty("公告状态")
    private Integer noticeStatus;
}

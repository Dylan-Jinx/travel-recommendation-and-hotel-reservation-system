package se.xmut.trahrs.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CarouselDto {


    @ApiModelProperty("图片路径")
    private String imgUrl;


}

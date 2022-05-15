package se.xmut.trahrs.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CarouselDto {

    @ApiModelProperty("排序")
    private Integer sort;
    
}

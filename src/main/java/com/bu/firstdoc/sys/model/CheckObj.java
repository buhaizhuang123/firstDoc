package com.bu.firstdoc.sys.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author haizhuangbu
 * @date 11:37 上午 2022/1/25
 * @mark CheckObj
 */
@Data
@Validated
@Accessors(chain = true)
public class CheckObj {


    @NotNull(message = "参数不能为空")
    private String id;

}

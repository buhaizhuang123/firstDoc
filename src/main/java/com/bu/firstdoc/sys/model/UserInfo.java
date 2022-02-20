package com.bu.firstdoc.sys.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author haizhuangbu
 * @date 9:26 上午 2022/1/13
 * 用户信息表
 */
@Data
@Accessors(chain = true)
public class UserInfo implements Serializable {

    @NotNull(message = "id 不能为空")
    private String id;

    @NotNull(message = "userId 不嫩为空")
    private String userId;

    @NotNull(message = "userName 不能为空")
    private String userName;

    private String userAddress;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date userBirDate;

    private Integer userAge;

    private String userInfoData;

}

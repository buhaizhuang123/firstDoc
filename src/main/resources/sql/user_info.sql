create table USER_INFO
(
    id             varchar(36) comment '主键',
    USER_ID        varchar(36) comment '用户id',
    USER_NAME      varchar(36) comment '用户名称',
    USER_ADDRESS   varchar(255) comment '用户地址',
    USER_BIR_DATE  date comment '出生日期',
    USER_AGE       int comment '用户年龄',
    USER_INFO_DATA json comment '用户信息',
    primary key (id),
    index name_index(USER_NAME)
) comment '用户信息表';
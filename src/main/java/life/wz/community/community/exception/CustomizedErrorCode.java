package life.wz.community.community.exception;

public enum CustomizedErrorCode implements ICustomizedErrorCode {

    QUESTION_NOT_FOUND(2001,"你找的问题不存在，更换重试？"),
    TARGET_PARAM_NOT_FOUND(2002,"未选择任何问题或评论进行恢复"),
    NO_LOGIN(2003,"未登录，请重试"),
    SYS_ERROR(2004,"服务器忙，稍后再试"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUNT(2006,"你操作的评论不存在"),
    CONTENT_IS_EMPTY(2007,"输入的内容不能为空"),
    READ_NOTIFICATION_FAIL(2008,"兄弟，你读别人信息？"),
    NOTIFICATION_NOT_FOUND(2009,"消息不见了"),
    ;

    @Override
    public String getMessage() {
        return message;
    }

   private Integer code;
    private String message;

    @Override
    public Integer getCode() {
        return code;
    }

    CustomizedErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

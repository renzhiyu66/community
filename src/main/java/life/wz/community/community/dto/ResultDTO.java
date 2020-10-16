package life.wz.community.community.dto;

import life.wz.community.community.exception.CustomizeException;
import life.wz.community.community.exception.CustomizedErrorCode;
import lombok.Data;

@Data
public class ResultDTO<T> {
    private Integer code;
    private String message;
    private T data;

    public static ResultDTO errorOf(Integer code,String message){
        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO errorOf(CustomizedErrorCode errorcode) {
        return errorOf(errorcode.getCode(),errorcode.getMessage());
    }
    public static ResultDTO errorOf(CustomizeException e) {
        return errorOf(e.getCode(),e.getMessage());
    }
    public static ResultDTO okOf(){
        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }
    public static <T> ResultDTO okOf(Object t){
        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        resultDTO.setData(t);
        return resultDTO;
    }

}

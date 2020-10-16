package life.wz.community.community.controller;

import life.wz.community.community.Service.CommentService;
import life.wz.community.community.dto.CommentCreateDTO;
import life.wz.community.community.dto.CommentDTO;
import life.wz.community.community.dto.ResultDTO;
import life.wz.community.community.enums.CommentTypeEnum;
import life.wz.community.community.exception.CustomizedErrorCode;
import life.wz.community.community.model.Comment;
import life.wz.community.community.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        if(user==null){
            return ResultDTO.errorOf(CustomizedErrorCode.NO_LOGIN);

        }

        if(commentCreateDTO==null|| StringUtils.isBlank(commentCreateDTO.getContent())){
            return ResultDTO.errorOf(CustomizedErrorCode.CONTENT_IS_EMPTY);
        }

        Comment comment=new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        comment.setCommentCount(0);
        commentService.insert(comment,user);

        return ResultDTO.okOf();

    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name="id")Long id ){
        List<CommentDTO> commentDTOS=commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }
}

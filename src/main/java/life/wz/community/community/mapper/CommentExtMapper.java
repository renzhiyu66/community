package life.wz.community.community.mapper;

import life.wz.community.community.model.Comment;

public interface CommentExtMapper {
   int incCommentCount(Comment comment);
}
package life.wz.community.community.Service;

import life.wz.community.community.dto.NotificationDTO;
import life.wz.community.community.dto.PaginationDTO;
import life.wz.community.community.enums.NotificationStatusEnum;
import life.wz.community.community.enums.NotificationTypeEnum;
import life.wz.community.community.exception.CustomizeException;
import life.wz.community.community.exception.CustomizedErrorCode;
import life.wz.community.community.mapper.NotificationMapper;
import life.wz.community.community.mapper.UserMapper;
import life.wz.community.community.model.Notification;
import life.wz.community.community.model.NotificationExample;
import life.wz.community.community.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private UserMapper userMapper;


    public PaginationDTO list(Long userId, Integer page, Integer size) {
        PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO<>();

        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(userId);
        Integer totalCount = (int) notificationMapper.countByExample(notificationExample);
        paginationDTO.setPagination(totalCount, page, size);
        page = paginationDTO.getPage();
        Integer offset = size * (page - 1);
        if (offset <0) {
            offset = 0;
        }
        NotificationExample example = new NotificationExample();
        example.createCriteria()
                .andReceiverEqualTo(userId);
        example.setOrderByClause("gmt_create desc");
        List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));

        if(notifications.size()==0){
            return paginationDTO;
        }

//        Set<Long> disUserIds=notifications.stream().map(notify->notify.getNotifier()).collect(Collectors.toSet());
//        List<Long> userIds=new ArrayList<>(disUserIds);
//        UserExample userExample=new UserExample();
//        userExample.createCriteria().andIdIn(userIds);
//        List<User> users=userMapper.selectByExample(userExample);
//        Map<Long,User> userMap=users.stream().collect(Collectors.toMap(u->u.getId(), u->u));

        List<NotificationDTO> notificationDTOS = new ArrayList<>();
        for(Notification notification:notifications){
            NotificationDTO notificationDTO=new NotificationDTO();
            BeanUtils.copyProperties(notification,notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));

            notificationDTOS.add(notificationDTO);
        }

        paginationDTO.setData(notificationDTOS);


        return paginationDTO;
    }

    public Long unreadCount(Long id) {
        NotificationExample notificationExample=new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(id)
        .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        return notificationMapper.countByExample(notificationExample);
    }

    public NotificationDTO read(Long id, User user) {
        Notification notification= notificationMapper.selectByPrimaryKey(id);

        if(notification==null){
            throw new CustomizeException(CustomizedErrorCode.NOTIFICATION_NOT_FOUND);
        }

        if(!Objects.equals( notification.getReceiver(),user.getId())){
            throw new CustomizeException(CustomizedErrorCode.READ_NOTIFICATION_FAIL);
        }

        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);

        NotificationDTO notificationDTO=new NotificationDTO();
        BeanUtils.copyProperties(notification,notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));

        return notificationDTO;

    }
}

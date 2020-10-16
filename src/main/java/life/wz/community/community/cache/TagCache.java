package life.wz.community.community.cache;


import life.wz.community.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagCache {
    public static List<TagDTO> get(){
        List<TagDTO> tagDTOS=new ArrayList<>();
        TagDTO program =new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("js","php","css","html","java","node", "python","html5"));
        tagDTOS.add(program);

        TagDTO framwork=new TagDTO();
        framwork.setCategoryName("平台框架");
        framwork.setTags(Arrays.asList("laraver","spring","express","yii","django"));
        tagDTOS.add(framwork);

        TagDTO server=new TagDTO();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("linux","nginx","docker","apache","ubuntu"));
        tagDTOS.add(server);

        TagDTO db=new TagDTO();
        db.setCategoryName("数据库");
        db.setTags(Arrays.asList("mysql","redis","mongodb","sql","oracle","nosql memcached"));
        tagDTOS.add(db);

        TagDTO tool=new TagDTO();
        tool.setCategoryName("开发工具");
        tool.setTags(Arrays.asList("git","github","visual-studio-code","vim","sublime-text","xcode intellij-idea"));
        tagDTOS.add(tool);


        return tagDTOS;
    }
    public static String filterInvalid(String tags){
        String[] split = StringUtils.split(tags,",");
        List<TagDTO> tagDTOS=get();
        List<String> tagList=tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid=Arrays.stream(split).filter(t->!tagList.contains(t)).collect(Collectors.joining(","));

        return invalid;
    }

}

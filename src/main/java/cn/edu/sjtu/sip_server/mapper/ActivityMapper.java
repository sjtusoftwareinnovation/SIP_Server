package cn.edu.sjtu.sip_server.mapper;


import cn.edu.sjtu.sip_server.entity.Activity;
import cn.edu.sjtu.sip_server.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface ActivityMapper extends BaseMapper<Activity> {
    List<Activity> findActivityList(Page page, @Param("status") Integer status,
                                    @Param("typeId") Integer typeId, @Param("levelId") Integer levelId, @Param("categoryId") Integer categoryId, @Param("name") String name, @Param("orderBy") String orderBy);

    List<Activity> findPublished(Page page, @Param("userId") Integer userId);

    List<Activity> findJoined(Page page, @Param("userId") Integer userId);

    List<User> findRegistration(@Param("activityId") Integer activityId);
}

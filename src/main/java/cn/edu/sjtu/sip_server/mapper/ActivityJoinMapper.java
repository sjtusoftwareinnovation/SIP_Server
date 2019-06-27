package cn.edu.sjtu.sip_server.mapper;

import cn.edu.sjtu.sip_server.entity.ActivityJoin;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

@Mapper
@Service
public interface ActivityJoinMapper extends BaseMapper<ActivityJoin> {
}

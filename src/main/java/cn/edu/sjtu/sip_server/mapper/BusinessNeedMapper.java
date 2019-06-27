package cn.edu.sjtu.sip_server.mapper;

import cn.edu.sjtu.sip_server.entity.BusinessNeed;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface BusinessNeedMapper extends BaseMapper<BusinessNeed> {
    List<BusinessNeed> findBusinessList(Page page, @Param("ew") Wrapper wrapper);

    BusinessNeed findDetailById(int id);

    void updateNotBegin();

    void updateOngoing();

    void updatePageview(@Param("increment") int increment, @Param("id") int id);
}

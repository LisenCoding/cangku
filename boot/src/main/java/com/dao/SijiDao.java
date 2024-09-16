package com.dao;

import com.entity.SijiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.SijiView;

/**
 * 司机 Dao 接口
 *
 * @author 
 */
public interface SijiDao extends BaseMapper<SijiEntity> {

   List<SijiView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}

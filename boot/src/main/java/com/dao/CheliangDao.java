package com.dao;

import com.entity.CheliangEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.CheliangView;

/**
 * 车辆 Dao 接口
 *
 * @author 
 */
public interface CheliangDao extends BaseMapper<CheliangEntity> {

   List<CheliangView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}

package com.dao;

import com.entity.GonggaoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.GonggaoView;

/**
 * 公告 Dao 接口
 *
 * @author 
 */
public interface GonggaoDao extends BaseMapper<GonggaoEntity> {

   List<GonggaoView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}

package com.dao;

import com.entity.HuowuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.HuowuView;

/**
 * 货物 Dao 接口
 *
 * @author 
 */
public interface HuowuDao extends BaseMapper<HuowuEntity> {

   List<HuowuView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}

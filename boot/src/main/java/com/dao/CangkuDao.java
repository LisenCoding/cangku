package com.dao;

import com.entity.CangkuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.CangkuView;

/**
 * 仓库 Dao 接口
 *
 * @author 
 */
public interface CangkuDao extends BaseMapper<CangkuEntity> {

   List<CangkuView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}

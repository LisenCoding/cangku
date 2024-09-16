package com.dao;

import com.entity.ShangpinCollectionEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ShangpinCollectionView;

/**
 * 商品收藏 Dao 接口
 *
 * @author 
 */
public interface ShangpinCollectionDao extends BaseMapper<ShangpinCollectionEntity> {

   List<ShangpinCollectionView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}

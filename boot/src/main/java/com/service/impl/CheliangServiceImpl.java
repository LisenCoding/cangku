package com.service.impl;

import com.utils.StringUtil;
import com.service.DictionaryService;
import com.utils.ClazzDiff;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.util.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import com.dao.CheliangDao;
import com.entity.CheliangEntity;
import com.service.CheliangService;
import com.entity.view.CheliangView;

/**
 * 车辆 服务实现类
 */
@Service("cheliangService")
@Transactional
public class CheliangServiceImpl extends ServiceImpl<CheliangDao, CheliangEntity> implements CheliangService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        Page<CheliangView> page =new Query<CheliangView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}

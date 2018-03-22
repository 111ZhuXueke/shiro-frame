package org.rj.frame.shiro.service.service.impl;

import com.rui.web.common.service.impl.BaseServiceImpl;
import org.rj.frame.shiro.service.domain.admin.ModuleDomain;
import org.rj.frame.shiro.service.mapper.IModuleMapper;
import org.rj.frame.shiro.service.service.IModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : zhuxueke
 * @since : 2018-01-10 15:49
 **/
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class ModuleServiceImpl extends BaseServiceImpl<ModuleDomain> implements IModuleService {
    @Autowired
    private IModuleMapper moduleMapper;

    @Override
    public Long getMaxId() {
        return null;
    }
}

package org.rj.frame.shiro.service.mapper;

import com.rui.web.common.persistence.Mapper;
import org.rj.frame.shiro.service.domain.admin.PermissionAssignDomain;
import org.rj.frame.shiro.service.domain.admin.PermissionDomain;

/**
 * @author : zhuxueke
 * @since : 2018-01-10 15:47
 **/
public interface IPermissionMapper extends Mapper<PermissionDomain> {

    /**
     * mapper 文件需要写 sql
     * @author : zhuxueke
     * @since : 2018/3/21 16:15
     */
    @Override
    Long getMaxId();
}

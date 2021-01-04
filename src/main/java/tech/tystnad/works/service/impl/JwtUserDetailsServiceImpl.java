package tech.tystnad.works.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.tystnad.works.core.exception.BusinessException;
import tech.tystnad.works.factory.JwtUserFactory;
import tech.tystnad.works.model.SysUser;
import tech.tystnad.works.repository.domain.*;
import tech.tystnad.works.repository.mapper.RoleAuthorityRelationshipDOMapper;
import tech.tystnad.works.repository.mapper.SysAuthorityDOMapper;
import tech.tystnad.works.repository.mapper.SysUserDOMapper;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(JwtUserDetailsServiceImpl.class);

    @Resource
    private SysUserDOMapper sysUserDOMapper;

    @Resource
    private RoleAuthorityRelationshipDOMapper roleAuthorityRelationshipDOMapper;

    @Resource
    private SysAuthorityDOMapper sysAuthorityDOMapper;

    /**
     * 查找角色的权限关联
     *
     * @param roleId 角色ID
     * @return 角色权限的简称列表
     */
    private List<String> getRoles(final Long roleId) {
        List<String> roles = new LinkedList<>();
        RoleAuthorityRelationshipDOExample authorityDOExample = new RoleAuthorityRelationshipDOExample();
        authorityDOExample.createCriteria().andRoleIdEqualTo(roleId);
        List<RoleAuthorityRelationshipDO> roleAuthorityRelationshipList = roleAuthorityRelationshipDOMapper.selectByExample(authorityDOExample);
        if (roleAuthorityRelationshipList == null || roleAuthorityRelationshipList.isEmpty()) {
            logger.warn("{}角色的权限为空", roleId);
        } else {
            Map<Integer, String> map = new HashMap<>();
            List<SysAuthorityDO> sysAuthorityList = sysAuthorityDOMapper.selectByExample(new SysAuthorityDOExample());
            sysAuthorityList.forEach(e -> map.put(e.getAuthId(), e.getAuthName()));
            roleAuthorityRelationshipList.forEach(e -> {
                if (map.containsKey(e.getAuthId())) {
                    roles.add(map.get(e.getAuthId()));
                } else {
                    logger.warn("{}角色权限缺失{}", roleId, e.getAuthId());
                }
            });
        }
        return roles;
    }

    /**
     * 获取系统用户
     * @param username 用户名,也可以是邮箱地址
     * @return 用户信息
     * @throws BusinessException 可能会出现重复的用户名
     */
    private SysUser getSysUser(String username) throws BusinessException {
        SysUser user = new SysUser();
        SysUserDOExample sysUserDOExample = new SysUserDOExample();
        // 用户名或者邮箱
        sysUserDOExample.createCriteria().andDeletedEqualTo(Boolean.FALSE).andUserNameEqualTo(username);
        sysUserDOExample.or().andDeletedEqualTo(Boolean.FALSE).andEmailEqualTo(username);
        List<SysUserDO> sysUserList = sysUserDOMapper.selectByExample(sysUserDOExample);
        if (sysUserList == null || sysUserList.isEmpty()) {
            return null;
        }
        if (sysUserList.size() > 1) {
            throw new BusinessException("存在重复用户名");
        }
        SysUserDO sysUser = sysUserList.get(0);
        List<String> roles = getRoles(sysUser.getRoleId());
        user.setUserId(sysUser.getUserId());
        user.setTopId(sysUser.getTopId());
        user.setOrgId(sysUser.getOrgId());
        user.setUserName(sysUser.getUserName());
        user.setUserCipher(sysUser.getUserCipher());
        user.setEnabled(sysUser.getEnabled());
        // 只有用户修改密码后此属性才会更新,首次新增用户时取新增时间作为初始时间
        user.setLastPasswordResetTime(sysUser.getPasswordResetTime());
        user.setEmail(sysUser.getEmail());
        user.setRoles(roles);
        return user;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("查询用户名 {}", username);
        SysUser user = null;
        try {
            user = getSysUser(username);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}

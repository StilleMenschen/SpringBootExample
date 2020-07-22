package tech.tystnad.works.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.tystnad.works.core.BusinessException;
import tech.tystnad.works.factory.JwtUserFactory;
import tech.tystnad.works.model.User;
import tech.tystnad.works.repository.UserRepository;
import tech.tystnad.works.repository.domain.*;
import tech.tystnad.works.repository.mapper.RoleAuthorityRelationshipDOMapper;
import tech.tystnad.works.repository.mapper.SysAuthorityDOMapper;
import tech.tystnad.works.repository.mapper.SysOrgUserDOMapper;
import tech.tystnad.works.repository.mapper.SysUserDOMapper;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service("userDetailsService")
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private UserRepository userRepository;

    @Resource
    private SysUserDOMapper sysUserDOMapper;

    @Resource
    private SysOrgUserDOMapper sysOrgUserDOMapper;

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
            Map<Short, String> map = new HashMap<>();
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

    private User getSysUser(String username) throws BusinessException {
        User user = new User();
        SysUserDOExample sysUserDOExample = new SysUserDOExample();
        // 用户名或者邮箱
        sysUserDOExample.createCriteria().andDeletedEqualTo(false).andUserNameEqualTo(username);
        sysUserDOExample.or().andDeletedEqualTo(false).andEmailEqualTo(username);
        List<SysUserDO> sysUserList = sysUserDOMapper.selectByExample(sysUserDOExample);
        if (sysUserList == null || sysUserList.isEmpty()) {
            return null;
        }
        if (sysUserList.size() > 1) {
            throw new BusinessException("存在重复的用户名");
        }
        SysUserDO sysUser = sysUserList.get(0);
        user.setUsername(sysUser.getUserName());
        user.setPassword(sysUser.getUserCipher());
        user.setEnabled(sysUser.getEnabled());
        user.setLastPasswordResetDate(sysUser.getUpdateTime());
        user.setEmail(sysUser.getEmail());
        user.setRoles(getRoles(sysUser.getRoleId()));
        return user;
    }

    private User getSysOrgUser(String username) throws BusinessException {
        User user = new User();
        SysOrgUserDOExample example = new SysOrgUserDOExample();
        // 用户名或者邮箱
        example.createCriteria().andDeletedEqualTo(false).andUserNameEqualTo(username);
        example.or().andDeletedEqualTo(false).andEmailEqualTo(username);
        List<SysOrgUserDO> sysOrgUserList = sysOrgUserDOMapper.selectByExample(example);
        if (sysOrgUserList == null || sysOrgUserList.isEmpty()) {
            return null;
        }
        if (sysOrgUserList.size() > 1) {
            throw new BusinessException("存在重复的用户名");
        }
        SysOrgUserDO sysOrgUser = sysOrgUserList.get(0);
        user.setUsername(sysOrgUser.getUserName());
        user.setPassword(sysOrgUser.getUserCipher());
        user.setEnabled(sysOrgUser.getEnabled());
        user.setLastPasswordResetDate(sysOrgUser.getUpdateTime());
        user.setEmail(sysOrgUser.getEmail());
        user.setRoles(getRoles(sysOrgUser.getRoleId()));
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("查询用户 {}", username);
//      User user = userRepository.findByUsername(username);
        User user = null;
        try {
            user = getSysUser(username);
            if (user == null) {
                user = getSysOrgUser(username);
            }
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

package tech.tystnad.works.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import tech.tystnad.works.config.RegularExpressionConfig;
import tech.tystnad.works.converter.SysUserConverter;
import tech.tystnad.works.core.service.BaseService;
import tech.tystnad.works.enums.ResponseMessage;
import tech.tystnad.works.model.JwtUser;
import tech.tystnad.works.model.ResponseObjectEntity;
import tech.tystnad.works.model.dto.SysUserDTO;
import tech.tystnad.works.model.vo.SysUserVO;
import tech.tystnad.works.repository.domain.SysUserDO;
import tech.tystnad.works.service.AuthService;
import tech.tystnad.works.util.JwtTokenUtil;
import tech.tystnad.works.util.StringUtils;

@Service
public class AuthServiceImpl extends BaseService implements AuthService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    private RegularExpressionConfig rec;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService,
                           JwtTokenUtil jwtTokenUtil, RegularExpressionConfig rec) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.rec = rec;
    }

    @Override
    public ResponseObjectEntity<SysUserVO> register(SysUserDTO sysUserDTO) {
        SysUserDO sysUserDO = SysUserConverter.dto2do(sysUserDTO);
        ResponseMessage message = checkSysUser(sysUserDO);
        if (message != null) {
            return fail(message);
        }
        SysUserVO vo = new SysUserVO();
        vo.setUserName(sysUserDO.getUserName());
        return ok(vo);
    }

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        // 进行安全认证
        logger.debug("开始验证");
        final Authentication authentication = authenticationManager.authenticate(upToken);
        logger.debug("设置权限上下文");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 生成token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtTokenUtil.generateToken(userDetails);
    }

    @Override
    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }

    /**
     * 检查用户信息
     *
     * @param sysUserDO 用户信息
     * @return 错误信息
     */
    private ResponseMessage checkSysUser(SysUserDO sysUserDO) {
        if (sysUserDO == null) {
            return ResponseMessage.MSG1001;
        }
        String userName = sysUserDO.getUserName();
        String userPassword = sysUserDO.getUserCipher();
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(userPassword)) {
            return ResponseMessage.MSG1001;
        }
        if (!userName.matches(rec.userName)) {
            return ResponseMessage.MSG1002;
        }
        if (!userPassword.matches(rec.userPassword)) {
            return ResponseMessage.MSG1003;
        }
        if (!StringUtils.isEmpty(sysUserDO.getEmail())) {
            String email = sysUserDO.getEmail();
            if (!email.matches(rec.userEmail)) {
                return ResponseMessage.MSG1004;
            }
        }
        if (!StringUtils.isEmpty(sysUserDO.getNickname())) {
            String nickname = sysUserDO.getNickname();
            if (!nickname.matches(rec.userNickname) || nickname.length() > 32) {
                return ResponseMessage.MSG1005;
            }
        }
        return null;
    }
}

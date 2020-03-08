package cn.sjlm.sjlmapi.resulver;

import cn.sjlm.sjlmapi.resulver.annotation.LoginUser;
import cn.sjlm.sjlmapi.userToken.UserTokenManager;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * token转换
 * */
public class LoginUserResulver implements HandlerMethodArgumentResolver {
    //管理员用户
    private static final String USER_TOKEN = "X-SJLM-Token";

    @Override
    public boolean supportsParameter(MethodParameter parameter){
        //获取参数是否为Integer,该参数是否包含UserLogin注解
        return parameter.getParameterType().isAssignableFrom(Integer.class) &&
                parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
                           NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception{
        String token = webRequest.getHeader(USER_TOKEN);
        if (token == null || token.trim().isEmpty()) {
            return null;
        }
        return UserTokenManager.getUserId(token);
    }
}

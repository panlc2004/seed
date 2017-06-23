package com.czy.seed.mvc.auth;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by 003914[panlc] on 2017-06-23.
 */
@Service
public class CustomInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

//    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
//
//    @Override
//    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
//        System.out.println("nwuidhwuiehdfu");
//        // object 是一个URL，被用户请求的url。</span>
//        FilterInvocation filterInvocation = (FilterInvocation) object;
//        if (resourceMap == null) {
//            loadResourceDefine();
//        }
//        Iterator<String> ite = resourceMap.keySet().iterator();
//        while (ite.hasNext()) {
//            String resURL = ite.next();
//            RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);
//            if (requestMatcher.matches(filterInvocation.getHttpRequest())) {
//                return resourceMap.get(resURL);
//            }
//        }
//
//        return null;
//    }
//
//    @Override
//    public Collection<ConfigAttribute> getAllConfigAttributes() {
//        return new ArrayList<ConfigAttribute>();
//    }
//
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return true;
//    }
//
//    /**
//     * 在Web服务器启动时，提取系统中的所有权限
//     */
//    @PostConstruct
//    private void loadResourceDefine() {
//        List<Map<String, Object>> list = sRoleVODao.findAll();
//        List<String> query = new ArrayList<String>();
//        if (list != null && list.size() > 0) {
//            for (Map<String, Object> sr : list) {
//                //String name = sr.get("name")
//                Object value = sr.get("name");
//                String name = String.valueOf(value);
//                query.add(name);
//            }
//        }
//        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
//        for (String auth : query) {
//            ConfigAttribute ca = new SecurityConfig(auth);
//            //List<Map<String,Object>> query1 = sResourceVODao.findByRoleName(auth);
//            List<String> query1 = new ArrayList<String>();
//            List<Map<String, Object>> list1 = sResourceVODao.findByRoleName(auth);
//            if (list1 != null && list1.size() > 0) {
//                for (Map<String, Object> map : list1) {
//                    Object value = map.get("resource_string");
//                    String url = String.valueOf(value);
//                    query1.add(url);
//                }
//            }
//            for (String res : query1) {
//                String url = res;
//                if (resourceMap.containsKey(url)) {
//
//                    Collection<ConfigAttribute> value = resourceMap.get(url);
//                    value.add(ca);
//                    resourceMap.put(url, value);
//                } else {
//                    Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
//                    atts.add(ca);
//                    resourceMap.put(url, atts);
//                }
//            }
//        }
//    }
}
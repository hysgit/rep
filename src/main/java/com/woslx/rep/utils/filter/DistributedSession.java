package com.woslx.rep.utils.filter;

import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by hy on 10/31/16.
 */
public  class DistributedSession {

    private JedisPool pool;
    private boolean invalid;
    private String sessionId;
    private String sessionKey;
    private Map<String, String> attributes;
    private boolean newCreate;  //是否新创建

    private DistributedSession(JedisPool pool, String sessionId) {
        if(StringUtils.isEmpty(sessionId))
        {
            this.sessionId = UUID.randomUUID().toString().replaceAll("-", "");
        }
        else {
            this.sessionId = sessionId;
        }

        this.sessionKey = "session_"+this.sessionId;
        this.attributes = new HashMap<>();
        this.pool = pool;
    }

    public  String getSessionId()      //获取session id
    {
        return this.sessionId;
    }

    public String getAttribute(String name)       //获取指定属性的名称
    {
        return attributes.get(name);
    }

    public boolean setAttribute(String name, String value)         //设置属性值，同步到redis
    {
        attributes.put(name, value);
        Jedis jedis = pool.getResource();
        Long aLong = jedis.hset(sessionKey, name, value);
        jedis.close();
        return 1 == aLong.intValue();
    }

    public String[] getAllAttributeNames()        //获取所有的属性名
    {
        Set<Map.Entry<String, String>> entries = attributes.entrySet();
        String[] names = new String[entries.size()];
        int index = 0;
        for (Map.Entry<String, String> entry : entries) {
            names[index++] = entry.getKey();
        }
        return names;
    }

    public Date getCreateTime() throws ParseException       //获取session创建时间
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.parse(attributes.get("create_time"));

    }

    public Date getLastAccessTime() throws ParseException       //获取上次访问时间
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.parse(attributes.get("last_access"));
    }

    public boolean removeAttribute(String name) {
        attributes.remove(name);
        Jedis jedis = pool.getResource();
        Long aLong = jedis.hdel(sessionKey, name);
        jedis.close();
        return 1 == aLong.intValue();

    }

    public boolean isNewCreate() {
        return newCreate;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public boolean isInvalid() {
        return invalid;
    }

    public void setInvalid(boolean invalid) {
        this.invalid = invalid;
    }

    private void save(){
        Jedis jedis = pool.getResource();
        jedis.hmset(sessionKey, attributes);
        jedis.close();
    }
    public void resetTimeOut()
    {
        Jedis jedis = pool.getResource();
        jedis.expire(sessionKey,1800);
        jedis.close();
    }

    public static DistributedSession getDistributedSession(JedisPool pool, HttpServletRequest request)
    {
        String name = null;
        String value = null;

        Cookie[] cookies = request.getCookies();
        boolean hasSessionCookie = false;
        for (Cookie cookie : cookies) {
            name = cookie.getName();
            value = cookie.getValue();

            if ("distributed-session".equals(name) && org.apache.commons.lang.StringUtils.isNotEmpty(value)) {
                hasSessionCookie = true;
                break;
            }
        }
        DistributedSession distributedSession = null;
        if (!hasSessionCookie) {
            distributedSession = createDistributedSession(pool);

        } else {
            Jedis resource = pool.getResource();
            Map<String, String> map = resource.hgetAll("session_" + value);
            if (map == null || map.size() == 0) {
                //无效的session 需要创建session
                distributedSession = createDistributedSession(pool);
            } else {
                //用获取的值创建session对象
                distributedSession = new DistributedSession(pool, value);
                distributedSession.setAttributes(map);
                distributedSession.setAttribute("last_access",new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
                distributedSession.newCreate = false;
            }

            resource.close();
        }
        return distributedSession;
    }

    private static DistributedSession createDistributedSession(JedisPool pool) {
        DistributedSession distributedSession;//不存在需要的cookie 需要创建session
        distributedSession = new DistributedSession(pool, null);
        //保存session
        distributedSession.setAttribute("create_time",new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        distributedSession.setAttribute("last_access",new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        distributedSession.save();
        distributedSession.newCreate = true;
        return distributedSession;
    }
}

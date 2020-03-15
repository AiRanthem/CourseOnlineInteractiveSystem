package com.gp03d.learn.springboot.dao;


import com.gp03d.learn.springboot.entities.GroupUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class GroupUserDao {
    @Autowired
    public static Map<String, GroupUser> groupusers = null;

    static {
        groupusers = new HashMap<String, GroupUser>();

        groupusers.put("1006", new GroupUser(100,"1006"));
    }

    public Collection<GroupUser> getAll(){
        return groupusers.values();
    }
    public void save(GroupUser guser)
    {
        groupusers.put(guser.getUID(),guser);
    }
}

package com.woniuxy.mapper;

import com.woniuxy.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author WP
 * @since 2021-02-02
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    @Select("SELECT p.id\n" +
            "FROM t_role_permission AS rp\n" +
            "JOIN t_permission p\n" +
            "ON rp.pid=p.id\n" +
            "WHERE rp.rid=#{rid}\n" +
            "AND p.`level`=3")
    List<Integer> getPermissionsIdByRid(Integer rid);

    @Delete("DELETE FROM t_role_permission WHERE rid=#{rid}")
    //根据角色id删除角色所有权限
    public void DeleteRoleByRid(Integer rid);

    //新增角色权限
    @Insert("insert into t_role_permission(rid,pid) values(#{rid},#{pid})")
    public void insertPermissionByRidAndPid(@RequestParam Integer rid,@RequestParam Integer pid);
}

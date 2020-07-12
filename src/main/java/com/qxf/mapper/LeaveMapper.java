package com.qxf.mapper;

import com.qxf.pojo.Leave;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @ClassName LeaveMapper
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/7/5 23:23
 **/
public interface LeaveMapper {

    int addLeave(Leave leave);
    // leave为mysql的关键字
    @Update("update `leave` set process_instance_id = #{processInstanceId} where id = #{id}")
    int updateLeave(@Param("id") String id, @Param("processInstanceId") String processInstanceId);

    @Select("select * from `leave` where id=#{id}")
    Leave getLeaveById(String id);
}

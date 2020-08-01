package com.zl.demo.mapper.teacher;

import com.zl.demo.entity.teacher.TeacherInfo;

import java.util.List;

/**
 * @Author: zhoule
 * @Description:
 * @Date:
 */
public interface TeacherMapper {
    List<TeacherInfo> selectAll();
}

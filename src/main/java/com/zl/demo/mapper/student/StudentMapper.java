package com.zl.demo.mapper.student;

import com.zl.demo.entity.student.StudentInfo;

import java.util.List;

/**
 * @Author: zhoule
 * @Description:
 * @Date:
 */
public interface StudentMapper {

    List<StudentInfo> selectAll();
}

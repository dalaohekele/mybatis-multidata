package com.zl.demo.service;

import com.zl.demo.entity.student.StudentInfo;
import com.zl.demo.entity.teacher.TeacherInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: zhoule
 * @Description:
 * @Date:
 */

public interface GetInfoService {

    List<StudentInfo> getStudent();

    List<TeacherInfo> getTeacher();
}

package com.zl.demo.service.impl;

import com.zl.demo.entity.student.StudentInfo;
import com.zl.demo.entity.teacher.TeacherInfo;
import com.zl.demo.mapper.student.StudentMapper;
import com.zl.demo.mapper.teacher.TeacherMapper;
import com.zl.demo.service.GetInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zhoule
 * @Description:
 * @Date:
 */
@Service
public class GetInfoServiceImpl implements GetInfoService {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public List<StudentInfo> getStudent() {
        return studentMapper.selectAll();
    }

    @Override
    public List<TeacherInfo> getTeacher() {
        return teacherMapper.selectAll();
    }
}

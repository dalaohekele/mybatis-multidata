package com.zl.demo.controller;

import com.github.pagehelper.PageHelper;
import com.zl.demo.entity.student.StudentInfo;
import com.zl.demo.entity.teacher.TeacherInfo;
import com.zl.demo.mapper.student.StudentMapper;
import com.zl.demo.mapper.teacher.TeacherMapper;
import com.zl.demo.service.GetInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhoule
 * @Description:
 * @Date:
 */
@RequestMapping("/api")
@RestController
public class IndexController {

    @Autowired
    GetInfoService getInfoService;


    @GetMapping("/index")
    public Map<String,Object> getAll() {
        Map<String,Object> map = new HashMap<>();
        List<StudentInfo> studentInfoList = getInfoService.getStudent();
        List<TeacherInfo> teacherInfoList = getInfoService.getTeacher();
        map.put("students",studentInfoList);
        map.put("teacher",teacherInfoList);
        return map;
    }

    @GetMapping("/page")
    public ArrayList<Map<String, Object>> page(@RequestParam int pageNum,@RequestParam int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String,Object> map = new HashMap<>();
        List<StudentInfo> studentInfoList = getInfoService.getStudent();
        for (StudentInfo studentInfo:studentInfoList) {
            map.put("students", studentInfo);
            list.add(map);
        }
        return list;
    }

}

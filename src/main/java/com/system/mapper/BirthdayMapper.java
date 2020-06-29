package com.system.mapper;
import com.system.po.Birthday;
import com.system.po.PagingVO;

import java.util.List;

public interface BirthdayMapper {
    int insert(Birthday birthday);

    int deleteByID(Integer id);

    List<Birthday> selectAll (PagingVO pagingVO);
    List<Birthday> selectAllAuth (PagingVO pagingVO);

    int countBirth();

    int countBirthAuth(String teacherName);
}



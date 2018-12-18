package com.system.mapper;

import com.system.po.ExcelLog;

import java.util.List;

public interface ExcelLogMapper {

    //插入

    int insert (ExcelLog excelLog);
    //查看全部
    List<ExcelLog> selectUploadSeccess();

    List<ExcelLog> selectUploadFail();

    List<ExcelLog> selectUploadConfirm();


    //清空表格
    void deleteExcelLog();
}

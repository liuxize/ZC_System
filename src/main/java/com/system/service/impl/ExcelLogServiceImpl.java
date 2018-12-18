package com.system.service.impl;

import com.system.mapper.ExcelLogMapper;
import com.system.po.ExcelLog;
import com.system.service.ExcelLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcelLogServiceImpl implements ExcelLogService {
    @Autowired
    private ExcelLogMapper excelLogMapper;




    public  void saveExcel (ExcelLog excelLog){
        excelLogMapper.insert(excelLog);
    }

    public void deleteExcel(){
        excelLogMapper.deleteExcelLog();
    }

    public List<ExcelLog> findUploadSuccess() throws Exception{
        return excelLogMapper.selectUploadSeccess();
    }

    public List<ExcelLog> findUploadFail() throws Exception{
        return excelLogMapper.selectUploadFail();
    }

    public List<ExcelLog> findUploadConfirm() throws Exception{
        return excelLogMapper.selectUploadConfirm();
    }



}

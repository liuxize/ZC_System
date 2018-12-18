package com.system.service;

import com.system.po.ExcelLog;

import java.util.List;

public interface ExcelLogService {

    void saveExcel (ExcelLog excelLog);

    void deleteExcel();

    List<ExcelLog> findUploadSuccess() throws Exception;

    List<ExcelLog> findUploadFail() throws Exception;

    List<ExcelLog> findUploadConfirm() throws Exception;



}

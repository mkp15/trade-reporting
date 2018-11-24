package com.jp.trade.reporting.service;

import com.jp.trade.reporting.vo.ReportVo;

import java.util.List;

public interface Report {

    List<ReportVo> generate();
}

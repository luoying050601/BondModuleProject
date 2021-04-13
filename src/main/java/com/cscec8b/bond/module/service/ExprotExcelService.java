package com.cscec8b.bond.module.service;

import com.cscec8b.bond.module.object.BondTally;

import java.util.List;

public interface ExprotExcelService {
    /*
   保证金台账导出
    **/
    List getTallyDetailderive(BondTally bondTally);
}
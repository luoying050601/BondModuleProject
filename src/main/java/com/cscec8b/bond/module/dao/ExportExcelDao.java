package com.cscec8b.bond.module.dao;

import com.cscec8b.bond.module.object.BondTally;
import com.cscec8b.common.module.object.CSCECUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Mapper
@Component
public interface ExportExcelDao {
    /*
    保证金台账导出
     */
    List getTallyDetailderive(@Param("bondTally")BondTally bondTally,
                              @Param("cscecUser")CSCECUser cscecUser,
                              @Param("containChild")Boolean containChild
    );
}
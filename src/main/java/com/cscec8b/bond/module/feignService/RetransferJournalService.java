package com.cscec8b.bond.module.feignService;

import com.cscec8b.bond.module.object.RetransferCBSprepare;
import com.cscec8b.bond.module.object.RetransferJournal;
import com.cscec8b.bond.module.object.RetransferResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "JournalService", url = "${url.DBPMApproval}")
public interface RetransferJournalService {
    /**ERP重传
     * @param retransferJournal
     * @return
     */
    @PostMapping(value = "/FDMBondOSBProject/ERPJournalImportPS")
    RetransferResult retransferJournal(@RequestBody RetransferJournal retransferJournal);

    /**CBS制单重传
     * @param RetransferCBSprepare
     */
    @PostMapping(value = "/FDMBondOSBProject/CBSImportPS")
    void retransferPrepare(@RequestBody RetransferCBSprepare RetransferCBSprepare);
}
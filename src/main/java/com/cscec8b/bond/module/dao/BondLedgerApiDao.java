package com.cscec8b.bond.module.dao;

import com.cscec8b.bond.module.object.*;
import com.cscec8b.common.module.object.CSCECUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface BondLedgerApiDao {


    Integer updateCbsPayments();

    void createErpCft(Map<String, Object> map);

    Integer getCBSStatementIdBySourceIdAndType(@Param("sourceId") Integer sourceId,
                                               @Param("sourceType") String sourceType);

    /**
     * 获取erp凭证列表
     *
     * @param erpCft
     * @param cscecUser
     * @param start
     * @param end
     * @return
     */
    List<ERPCft> getErpCftList(@Param("erpCft") ERPCft erpCft,
                               @Param("cscecUser") CSCECUser cscecUser,
                               @Param("start") Integer start,
                               @Param("end") Integer end);


    void updateERPStatus(@Param("erpCftDetail") ERPCftDetail erpCftDetail);

    /**
     * 通过erp日记账的名称获取erp详情
     *
     * @param erpBatchName
     * @return
     */
    ERPCftDetail getErpCftDetailByErpBatchName(@Param("erpBatchName") String erpBatchName);

    ERPCftDetail getErpCftDetailByErpId(@Param("headerId") Integer headerId);

    ERPCftDetail getErpCftDetailHeaderId(@Param("headerId") Integer headerId);

    /**
     * 根据headerId查询erp行信息
     *
     * @param headerId
     * @return
     */
    List<ErpLine> getErpLineListByHeaderId(@Param("headerId") Integer headerId);

    List<ErpLine> getSimpleErpLineListByHeaderId(@Param("headerId") Integer headerId);

    /**
     * CBS记录查询接口
     *
     * @param cbspayMent
     * @param start
     * @param end
     * @return
     */
    List getCBSPayMentList(@Param("cbspayMent") CBSPayMent cbspayMent,
                           @Param("cscecUser") CSCECUser cscecUser,
                           @Param("start") Integer start,
                           @Param("end") Integer end);


    /**
     * 查询CBS流水
     *
     * @param cbsStatement
     * @return
     */
    List<CBSStatement> getCBSStatementList(@Param("cbsStatement") CBSStatement cbsStatement,
                                           @Param("cscecUser") CSCECUser cscecUser,
                                           @Param("reqId") Integer reqId,
                                           @Param("applyType") String applyType,
                                           @Param("start") Integer start,
                                           @Param("end") Integer end);


    /**
     * 获取主键 用于生成 支付状态
     *
     * @param nodeName
     * @param sourceId
     * @return
     */
    Integer getCBSStatementPaymentId(@Param("nodeName") String nodeName,
                                     @Param("sourceId") Integer sourceId);

    /**
     * 获取验证cbs流水
     *
     * @param transId
     * @return
     */
    List<CBSStatement> getCBSStatementByTransId(@Param("transId") String transId);

    String getCBSStatementId(@Param("bankSeqNumber") String bankSeqNumber);

    /**
     * 获取保证金台账
     *
     * @param bondTally
     * @return
     */
    List<BondTallyDetail> getBondTallyList(@Param("bondTally") BondTally bondTally,
                                           @Param("cscecUser") CSCECUser cscecUser,
                                           @Param("orgId") Integer orgId,
                                           @Param("start") Integer start,
                                           @Param("end") Integer end,
                                           @Param("containChild") Boolean containChild);

    /**
     * 获取保证金台账
     *
     * @param bondTally
     * @return
     */
    Double getBalance(@Param("bondTally") BondTally bondTally,
                      @Param("cscecUser") CSCECUser cscecUser,
                      @Param("containChild") Boolean containChild
    );

    /**
     * CBS记录重传接口
     *
     * @param cbsPaymentId
     * @return
     */
    CBSPayMent getCbspayMentListCbsPaymentId(@Param("cbsPaymentId") Integer cbsPaymentId);

    /**
     * CBS重传修改状态
     *
     * @param cbsPaymentId
     * @return
     */
    Integer updateCbsPaymentId(@Param("cbsPaymentId") Integer cbsPaymentId);

    /**修改cbs的状态为推送成功
     * @param cbsPaymentId
     * @return
     */
    Integer updateCbsPaymentStatus(@Param("cbsPaymentId") Integer cbsPaymentId);


    Integer updateERPHeaderByHeaderId(
            @Param("headerId") Integer headerId,
            @Param("cscecUser") CSCECUser cscecUser,
            @Param("msg") String msg,
            @Param("status") String status);


    void IS_VALIDATE_TRANSFER_DATE(Map<String, Object> map);


    String getBankSeqNumber(@Param("reqId") Integer reqId);


    /**
     * 获取groupId  的序列
     *
     * @return
     */
    @Select("select FDM.FDM_GROUP_ID_S.nextval from dual")
    Integer getNextGroupId();


}

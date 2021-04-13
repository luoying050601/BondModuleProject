package com.cscec8b.bond.module.service.serviceImpl;

import com.cscec8b.bond.module.object.*;
import com.cscec8b.bond.module.service.BondConfigApiService;
import com.cscec8b.common.module.object.CSCECUser;
import com.cscec8b.common.module.object.PagerVo;
import com.cscec8b.common.module.object.exceptionObject.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class BondConfigApiServiceImpl extends BaseServiceImpl implements BondConfigApiService {
    private Logger logger = LoggerFactory.getLogger(BondConfigApiServiceImpl.class);

    /**
     * 保证金新增支付
     *
     * @param orgPayDefList
     * @return
     */
    @Override
    @Transactional( transactionManager = "TransactionManager_one",propagation = Propagation.REQUIRES_NEW)
    public Integer saveOrgPayDefList(OrgPayDefList orgPayDefList) {
        CSCECUser cscecUser = jwtTokenUtil.getPersonFromToken(servletRequest.getHeader("authorizationToken"));
        //代表新增
        Integer result = null;
        Integer defId = null;
        //保存
        List<BondOrgPayDefs> newLines = orgPayDefList.getNewLines();
        List<BondOrgPayDefs> updateLines = orgPayDefList.getUpdateLines();
        List<Integer> deleteLines = orgPayDefList.getDeleteLines();
        try {
            if (newLines != null) {
                for (BondOrgPayDefs line : newLines) {
                    String accountBankNumber2 = line.getAccountBankNumber().replaceAll(" ", "");
                    line.setAccountBankNumber(accountBankNumber2);
                    if (line.getCompanyId() == null) {
                        throw new CommonException("【错误】：保证金支付所属组织不能为空！");
                    } else {
                        defId = BondConfigApiDao.getNextval();
                        line.setDefId(defId);
                        BondConfigApiDao.insertBondOrgPayDefs(line, cscecUser);
                    }
                }
            }
            if (updateLines != null) {
                for (BondOrgPayDefs line : updateLines) {
                    String accountBankNumber2 = line.getAccountBankNumber().replaceAll(" ", "");
                    line.setAccountBankNumber(accountBankNumber2);
                    BondConfigApiDao.updateBondOrgPayDefs(line, cscecUser);
                }
            }
            if (deleteLines != null) {
                for (Integer i = 0; i < deleteLines.size(); i++) {
                    BondConfigApiDao.delBondOrgPayDef(deleteLines.get(i));
                }
            }
        } catch (CommonException e) {
            e.printStackTrace();
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("【错误】：保证金支付保存异常！");
        }
        return result;
    }


    /**
     * 支付方式查询分页
     *
     * @param bondOrgPayDefs
     * @param page
     * @param size
     * @return
     */
    @Override
    public PagerVo getOrgPayDefsList(BondOrgPayDefs bondOrgPayDefs, Integer page, Integer size) {
        CSCECUser cscecUser = jwtTokenUtil.getPersonFromToken(servletRequest.getHeader("authorizationToken"));


        PagerVo pagerVo = new PagerVo();
        pagerVo.setPage(page);
        pagerVo.setSize(size);
        List<BondOrgPayDefs> list = new ArrayList<BondOrgPayDefs>();
        try {
            list = BondConfigApiDao.getOrgPayDefsList(bondOrgPayDefs, cscecUser, (page - 1) * size, page * size);
            if (list.size() > 0) {
                pagerVo.setTotal(list.get(0).getTotal());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("【错误】：查询保证金支付方式表异常！");
        }
        pagerVo.setList(list);
        return pagerVo;
    }

    /**
     * 查询支付定义分页
     *
     * @param bondCategory
     * @param page
     * @param size
     * @return
     */
    @Override
    public PagerVo getBondCategoryList(BondCategory bondCategory, Integer page, Integer size) {
        PagerVo pagerVo = new PagerVo();
        pagerVo.setPage(page);
        pagerVo.setSize(size);
        List<BondCategory> list = new ArrayList<BondCategory>();
        try {
            list = BondConfigApiDao.getBondCategoryList(bondCategory, (page - 1) * size, page * size);
            if (list.size() > 0) {
                pagerVo.setTotal(list.get(0).getTotal());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("【错误】：查询保证金分类定义表异常！");
        }
        pagerVo.setList(list);
        return pagerVo;
    }

    /**
     * 保存分类定义
     *
     * @param orgPayDefList
     * @return
     */
    @Override
    @Transactional( transactionManager = "TransactionManager_one",propagation = Propagation.REQUIRES_NEW)
    public Integer saveBondCategory(OrgPayDefList orgPayDefList) {
        CSCECUser cscecUser = jwtTokenUtil.getPersonFromToken(servletRequest.getHeader("authorizationToken"));
        //代表新增
        Integer result = null;
        Integer CategoryId = null;
        List<BondCategory> newLine = orgPayDefList.getNewLine();
        List<Integer> deleteLine = orgPayDefList.getDeleteLine();
        List<BondCategory> updateLine = orgPayDefList.getUpdateLine();
        try {
            if (newLine != null) {
                //新增
                for (BondCategory line : newLine) {
                    if (line.getAccountSegment() == "") {
                        throw new CommonException("【错误】：保证金分类定义会计科目代码不能为空！");
                    } else {
                        CategoryId = BondConfigApiDao.getBondCategoryNextval();
                        line.setCategoryId(CategoryId);
                        BondConfigApiDao.insertBondCategory(line, cscecUser);
                    }
                }
            }
            if (updateLine != null) {
                //修改
                for (BondCategory line : updateLine) {
                    BondConfigApiDao.updateBondCategory(line, cscecUser);
                }
            }
            if (deleteLine != null) {
                //根据id 修改状态
                for (Integer i = 0; i < deleteLine.size(); i++) {
                    BondConfigApiDao.delBondCategory(deleteLine.get(i));
                }
            }
        } catch (CommonException e) {
            e.printStackTrace();
            throw new CommonException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("【错误】：保证金分类保存异常！");
        }
        return result;
    }


    /**
     * 查询招标方分页
     *
     * @param tendereeAccount
     * @param page
     * @param size
     * @return
     */
    @Override
    public PagerVo getTendereeAccountList(TendereeAccount tendereeAccount, Integer page, Integer size) {
        PagerVo pagerVo = new PagerVo();
        pagerVo.setPage(page);
        pagerVo.setSize(size);
        List<TendereeAccount> list = new ArrayList<TendereeAccount>();
        try {
            list = BondConfigApiDao.getTendereeAccountList(tendereeAccount, (page - 1) * size, page * size);
            if (list.size() > 0) {
                pagerVo.setTotal(list.get(0).getTotal());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("【错误】：查询招标方表异常！");
        }
        pagerVo.setList(list);
        return pagerVo;
    }

    /**
     * 招标方保存
     *
     * @param orgPayDefList
     * @return
     */
    @Override
    @Transactional( transactionManager = "TransactionManager_one",propagation = Propagation.REQUIRES_NEW)
    public Integer saveTendereeAccount(OrgPayDefList orgPayDefList) {
        CSCECUser cscecUser = jwtTokenUtil.getPersonFromToken(servletRequest.getHeader("authorizationToken"));
        //代表新增
        Integer result = null;
        Integer accountId = null;
        //保存
        List<TendereeAccount> newLineT = orgPayDefList.getNewLineT();
        List<TendereeAccount> updateLineT = orgPayDefList.getUpdateLineT();
        List<Integer> deleteLineT = orgPayDefList.getDeleteLineT();
        try {
            //新增
            if (newLineT != null) {
                for (TendereeAccount line : newLineT) {
                    String accountBankNumber = line.getAccountBankNumber();
                    String accountBankNumber2 = accountBankNumber.replaceAll(" ", "");
                    line.setAccountBankNumber(accountBankNumber2);
                    accountId = BondConfigApiDao.getTendereeAccountNextval();
                    line.setAccountId(accountId);
                    BondConfigApiDao.insertTendereeAccount(line, cscecUser);
                }
            }
            if (updateLineT != null) {
                //修改
                for (TendereeAccount line : updateLineT) {
                    String accountBankNumber = line.getAccountBankNumber();
                    String accountBankNumber2 = accountBankNumber.replaceAll(" ", "");
                    line.setAccountBankNumber(accountBankNumber2);
                    BondConfigApiDao.updateTendereeAccount(line, cscecUser);
                }
            }
            if (deleteLineT != null) {
                //根据id修改状态
                for (Integer i = 0; i < deleteLineT.size(); i++) {
                    BondConfigApiDao.delTendereeAccount(deleteLineT.get(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("【错误】：保存招标方异常!");
        }
        return result;
    }

    /**
     * 会计账科目查询接口 分页
     *
     * @param accountSegment
     * @param page
     * @param size
     * @return
     */
    @Override
    public PagerVo getAccoutSegmentList(AccountSegment accountSegment, Integer page, Integer size) {
        PagerVo pagerVo = new PagerVo();
        pagerVo.setPage(page);
        pagerVo.setSize(size);
        List<AccountSegment> list = new ArrayList<AccountSegment>();
        try {
            list = BondConfigApiDao.getAccoutSegmentList(accountSegment, (page - 1) * size, page * size);
            if (list.size() > 0) {
                pagerVo.setTotal(list.get(0).getTotal());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("【错误】：查询会计账科目表异常！");
        }
        pagerVo.setList(list);
        return pagerVo;
    }

    /**
     * 部门段查询分页
     *
     * @param segmentValues
     * @param page
     * @param size
     * @return
     */
    @Override
    public PagerVo getSegmentValuesList(SegmentValues segmentValues, Integer page, Integer size) {
        PagerVo pagerVo = new PagerVo();
        pagerVo.setPage(page);
        pagerVo.setSize(size);
        String SegmentType = "CCEED_COA_DP";
        List<SegmentValues> list = new ArrayList<SegmentValues>();
        try {
            list = BondConfigApiDao.getSegmentValuesList(segmentValues, (page - 1) * size, page * size, SegmentType);
            if (list.size() > 0) {
                pagerVo.setTotal(list.get(0).getTotal());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("【错误】：查询部门段表异常！");
        }
        pagerVo.setList(list);
        return pagerVo;
    }

    @Override
    public PagerVo getBackUpSegmentValuesList(SegmentValues segmentValues, Integer page, Integer size) {
        PagerVo pagerVo = new PagerVo();
        pagerVo.setPage(page);
        pagerVo.setSize(size);
        String SegmentType = "CCEED_COA_SP";
        List<SegmentValues> list = new ArrayList<SegmentValues>();
        try {
            list = BondConfigApiDao.getSegmentValuesList(segmentValues, (page - 1) * size, page * size, SegmentType);
            if (list.size() > 0) {

                pagerVo.setTotal(list.get(0).getTotal());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("【错误】：查询备用段表异常！");
        }
        pagerVo.setList(list);
        return pagerVo;
    }

    /**
     * 保证金菜单权限获取
     *
     * @param userCode
     * @return
     */
    @Override
    public List<String> getUserMenuList(String userCode) {
        List<String> user = new ArrayList<String>();
        try {
            user = BondConfigApiDao.getUserMenuList(userCode);
            if (user.size() > 0) {
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("【错误】：获取保证金菜单权限异常！");
        }
        return user;
    }


    /**
     * 获取往来段列表
     *
     * @param contact
     * @param page
     * @param size
     * @return
     */
    @Override
    public PagerVo<Contact> getContactSegmentList(Contact contact, Integer page, Integer size) {
        PagerVo pagerVo = new PagerVo();
        try {
            List<Contact> contacts = new ArrayList<Contact>();
            contacts = BondConfigApiDao.getContactSegmentList(contact, (page - 1) * size, page * size);
            if (contacts.size() > 0) {
                pagerVo.setTotal(contacts.get(0).getTotal());
            }
            pagerVo.setPage(page);
            pagerVo.setSize(size);
            pagerVo.setList(contacts);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("【错误】:获取往来段数据失败！");
        }
        return pagerVo;
    }
}
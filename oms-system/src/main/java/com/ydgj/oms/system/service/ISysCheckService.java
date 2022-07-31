package com.ydgj.oms.system.service;

import java.util.List;
import com.ydgj.oms.system.domain.SysCheck;

/**
 * 查验Service接口
 * 
 * @author ruoyi
 * @date 2022-07-02
 */
public interface ISysCheckService 
{
    /**
     * 查询查验
     * 
     * @param checkId 查验主键
     * @return 查验
     */
    public SysCheck selectSysCheckByCheckId(Long checkId);

    /**
     * 查询查验列表
     * 
     * @param sysCheck 查验
     * @return 查验集合
     */
    public List<SysCheck> selectSysCheckList(SysCheck sysCheck);

    /**
     * 新增查验
     * 
     * @param sysCheck 查验
     * @return 结果
     */
    public int insertSysCheck(SysCheck sysCheck);

    /**
     * 修改查验
     * 
     * @param sysCheck 查验
     * @return 结果
     */
    public int updateSysCheck(SysCheck sysCheck);

    /**
     * 批量删除查验
     * 
     * @param checkIds 需要删除的查验主键集合
     * @return 结果
     */
    public int deleteSysCheckByCheckIds(Long[] checkIds);

    /**
     * 删除查验信息
     * 
     * @param checkId 查验主键
     * @return 结果
     */
    public int deleteSysCheckByCheckId(Long checkId);

    /**
     * 查询所有查验
     *
     */
    public List<SysCheck> selectCheckAll();
}

package com.ydgj.oms.system.mapper;

import java.util.List;
import com.ydgj.oms.system.domain.SysCheck;

/**
 * 查验Mapper接口
 * 
 * @author ruoyi
 * @date 2022-07-02
 */
public interface SysCheckMapper 
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
     * 删除查验
     * 
     * @param checkId 查验主键
     * @return 结果
     */
    public int deleteSysCheckByCheckId(Long checkId);

    /**
     * 批量删除查验
     * 
     * @param checkIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysCheckByCheckIds(Long[] checkIds);

    /**
     * 查询所有查验
     *
     */
    public List<SysCheck> selectCheckAll();


    /**
     * 获取检验by name
     * @param checkName
     * @return
     */
    SysCheck selectSysCheckByCheckName(String checkName);
}

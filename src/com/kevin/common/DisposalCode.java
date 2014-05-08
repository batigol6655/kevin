
package com.kevin.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.beanutils.DynaBean;

import com.kevin.vo.SysCodeVO;


/**
 * 通过类型等条件查找码表数据
 * @author fanzl
 */

@SuppressWarnings("unchecked")
public class DisposalCode
{
    private static List<DynaBean> codes = new ArrayList();
    
	private DisposalCode(){
    	
    }
    public static void setCodes(List<DynaBean> code)
    {
        DisposalCode.codes = code;
        
    }

    
    /**
     * 通过码表类型检索出该类型下的所有码表数据
     * @param codeType 码表类型
     * @return 该类型下的所有数据
     */
    public static List<SysCodeVO> getCode(String codeType)
    {
        Iterator<DynaBean> i = codes.iterator();
        List<SysCodeVO> retVal = new ArrayList<SysCodeVO>();
        while (i.hasNext())
        {
        	DynaBean temp = (DynaBean) i.next();
        	SysCodeVO rv = new SysCodeVO();
            	rv.setCodeName((String) temp.get("codename"));
            	rv.setCodeType((String) temp.get("codetype"));
            	rv.setCodeId((String) temp.get("codeid"));
                retVal.add(rv);
        }
        return retVal;
    }
    
    /**
     * 通过类型和编码获取到该条记录
     * @param codeType 码表类型
     * @param codeKey 码表编码
     * @return 该类型+该编码的记录
     */
    public static SysCodeVO getCodeAll(String codeType, String codeKey)
    {
        Iterator<DynaBean> i = codes.iterator();
        while (i.hasNext())
        {
        	SysCodeVO rv = new SysCodeVO();        	
        	DynaBean temp = (DynaBean) i.next();
            String type = (String) temp.get("codetype");
            String key = (String) temp.get("codeid");

            codeType=codeType==null?"":codeType;
            codeKey=codeKey==null?"":codeKey;
            	rv.setCodeName((String) temp.get("codename"));
            	rv.setCodeType((String) temp.get("codetype"));
            	rv.setCodeId((String) temp.get("codeid"));            
                return  rv;
        }
        return null;
    }
    /**
     * 通过类型和编码获取到该条记录
     * @param codeId 码表id
     * @return 该名称
     */
    public static String getCodeName(String codeId)
    {
        Iterator<DynaBean> i = codes.iterator();
        while (i.hasNext())
        {
        	DynaBean temp = (DynaBean) i.next();
            String id = (String) temp.get("code_id");

            codeId=codeId==null?"":codeId;
            if (codeId.equalsIgnoreCase(id))
            {
                return  (String)temp.get("code_name");
            }
        }
        return null;
    }


    /**
     * 通过类型和编码获取到名称
     * @param codeType 码表类型
     * @param codeKey 码表编码
     * @return 名称
     */

    public static String getCodeName(String codeType, String codeKey)
    {

        Iterator<DynaBean> i = codes.iterator();
        while (i.hasNext())
        {
        	DynaBean temp = (DynaBean) i.next();
            String type = (String) temp.get("codetype");
            String key = (String) temp.get("codeid");
            if (type.equalsIgnoreCase(codeType) && key.equalsIgnoreCase(codeKey))
            {
                return (String)temp.get("codename");
            }
        }
        return "not find";
    }
}
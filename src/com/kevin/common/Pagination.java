package com.kevin.common;

import java.util.List;

/**
* * 分页数据存储类 * *
* 
* @author Z
*/
public class Pagination {


// 每页显示的记录数
private int numPerPage;

// 记录总数
private int totalRows;

// 总页数
private int totalPages;

// 当前页码
private int currentPage;

// 起始行数
private int startIndex;

// 结束行数
private int lastIndex;

// 结果集存放List
private List resultList;

private String pageStr;

private String emphasisBgColor;//当前页背景颜色

private String emphasisFontColor;//当前页字体颜色

private String emphasisBorderColor;//当前页边框颜色

private String normalBgColor;//其他页背景颜色

private String normalFontColor;//其他页字体颜色

private String normalBorderColor;//其他页边框颜色

private String jumpEnabled;//是否开启转到

public String getEmphasisBgColor() {
   return emphasisBgColor;
}

public void setEmphasisBgColor(String emphasisBgColor) {
   this.emphasisBgColor = emphasisBgColor;
}

public String getEmphasisFontColor() {
	   return emphasisFontColor;
	}

public void setEmphasisFontColor(String emphasisFontColor) {
	   this.emphasisFontColor = emphasisFontColor;
}


public String getEmphasisBorderColor() {
	   return emphasisBorderColor;
}

public void setEmphasisBorderColor(String emphasisBorderColor) {
	   this.emphasisBorderColor = emphasisBorderColor;
}		
	

public String getNormalBgColor() {
   return normalBgColor;
}

public void setNormalBgColor(String normalBgColor) {
   this.normalBgColor = normalBgColor;
}	
	
	
public String getNormalFontColor() {
	   return normalFontColor;
}

public void setNormalFontColor(String normalFontColor) {
	   this.normalFontColor = normalFontColor;
}	
		
public String getNormalBorderColor() {
	   return normalBorderColor;
}

public void setNormalBorderColor(String normalBorderColor) {
	   this.normalBorderColor = normalBorderColor;
}		
	
	
public String getJumpEnabled() {
	   return jumpEnabled;
}

public void setJumpEnabled(String jumpEnabled) {
	   this.jumpEnabled = jumpEnabled;
}		





public int getCurrentPage() {
   return currentPage;
}

public void setCurrentPage(int currentPage) {
   this.currentPage = currentPage;
}

public int getNumPerPage() {
   return numPerPage;
}

public void setNumPerPage(int numPerPage) {
   this.numPerPage = numPerPage;
}

public List getResultList() {
   return resultList;
}

public void setResultList(List resultList) {
   this.resultList = resultList;
}

public int getTotalPages() {
   return totalPages;
}

// 计算总页数
public void setTotalPages() {
   if (totalRows % numPerPage == 0) {
    this.totalPages = totalRows / numPerPage;
   } else {
    this.totalPages = (totalRows / numPerPage) + 1;
   }
}

public int getTotalRows() {
   return totalRows;
}

public void setTotalRows(int totalRows) {
   this.totalRows = totalRows;
}

public int getStartIndex() {
   return startIndex;
}

public void setStartIndex() {
   this.startIndex = (currentPage - 1) * numPerPage;
}

public int getLastIndex() {
   return lastIndex;
}

// 计算结束时候的索引
public void setLastIndex() {

   if (totalRows < numPerPage) {
    this.lastIndex = totalRows;
   } else if ((totalRows % numPerPage == 0)
     || (totalRows % numPerPage != 0 && currentPage < totalPages)) {
    this.lastIndex = currentPage * numPerPage;
   } else if (totalRows % numPerPage != 0 && currentPage == totalPages) {// 最后一页
    this.lastIndex = totalRows;
   }
}


public String getPageNStr(String n,String emphasis) {

	if(emphasis.equals("yes")){
		return "<input type=\"button\" value=\""+n+"\" style=\"color:"+this.emphasisFontColor+";width:30 px;height:23px;border: solid 1px "+this.emphasisBorderColor+";BACKGROUND: "+this.emphasisBgColor+";CURSOR: hand\"  onClick=\"changePage("+(n)+")\"> ";
	}else{
		return "<input type=\"button\" value=\""+n+"\" style=\"color:"+this.normalFontColor+";width:30 px;height:23px;border: solid 1px "+this.normalBorderColor+";BACKGROUND: "+this.normalBgColor+";CURSOR: hand\"  onClick=\"changePage("+(n)+")\"> ";
	}
	
}

//得到分页显示代码
public String getPageStr() {
	
	if(this.emphasisBgColor==null){
		this.emphasisBgColor="#EFEFEF";//配置默认颜色
	}
	if(this.emphasisFontColor==null){
		this.emphasisFontColor="#999999";//配置默认颜色
	}
	if(this.emphasisBorderColor==null){
		this.emphasisBorderColor="#999999";//配置默认颜色
	}
	
	if(this.normalBgColor==null){
		this.normalBgColor="#FFFFFF";//配置默认颜色
	}
	if(this.normalFontColor==null){
		this.normalFontColor="#999999";//配置默认颜色
	}
	if(this.normalBorderColor==null){
		this.normalBorderColor="#999999";//配置默认颜色
	}
	
	
	this.pageStr="";//初始化
	String prev_show="<input type=\"button\" value=\"<<上一页\" style=\"height:23px;color:"+this.normalFontColor+";border: solid 1px "+this.normalBorderColor+";BACKGROUND: "+this.normalBgColor+";CURSOR: hand\" onClick=\"changePage("+(currentPage-1)+")\"> ";
	String prev_no="<input type=\"button\" value=\"<<上一页\" disabled style=\"height:23px;color:#DDDDDD;border: solid 1px #DDDDDD;BACKGROUND: "+this.normalBgColor+"\"> ";
	String next_show="<input type=\"button\" value=\"下一页>>\" style=\"height:23px;color:"+this.normalFontColor+";border: solid 1px "+this.normalBorderColor+";BACKGROUND: "+this.normalBgColor+";CURSOR: hand\" onClick=\"changePage("+(currentPage+1)+")\"> ";
	String next_no="<input type=\"button\" value=\"下一页>>\" disabled style=\"height:23px;color:#DDDDDD;border: solid 1px #DDDDDD;BACKGROUND: "+this.normalBgColor+"\" > ";
	String dig="<span style=\"width:10 px\"></span>...<span style=\"width:10 px\"></span>";

	
	if(totalPages>=9){//一、如果总页数大于等于9时的情况下，出现省略号
	    if(currentPage==1){//1、当前页等于1的时候，前面到3为止，后面出现省略号，上页禁用。例：(no)上一页 1 2 3..45 46 下一页
	    	this.pageStr=prev_no+getPageNStr(String.valueOf(1),"yes")+getPageNStr(String.valueOf(2),"no")+getPageNStr(String.valueOf(3),"no")+dig+getPageNStr(String.valueOf(totalPages-1),"no")+getPageNStr(String.valueOf(totalPages),"no")+next_show;
	    }else if(currentPage==2){//2、当前页等于2和3时，前面到3为止，上页启用，后面出现省略号。例：上一页 1 2 3..45 46 下一页
	    	this.pageStr=prev_show+getPageNStr(String.valueOf(1),"no")+getPageNStr(String.valueOf(2),"yes")+getPageNStr(String.valueOf(3),"no")+dig+getPageNStr(String.valueOf(totalPages-1),"no")+getPageNStr(String.valueOf(totalPages),"no")+next_show;
	    }else if(currentPage==3){//3、当前页等于3时，前面到3为止，上页启用，后面出现省略号。例：上一页 1 2 3 4..45 46 下一页
	    	this.pageStr=prev_show+getPageNStr(String.valueOf(1),"no")+getPageNStr(String.valueOf(2),"no")+getPageNStr(String.valueOf(3),"yes")+getPageNStr(String.valueOf(4),"no")+dig+getPageNStr(String.valueOf(totalPages-1),"no")+getPageNStr(String.valueOf(totalPages),"no")+next_show;
	    }else if(currentPage==4){//4、当前页等于3时，前面到4为止，上页启用，后面出现省略号。例：上一页 1 2 3 4 5..45 46 下一页
	    	this.pageStr=prev_show+getPageNStr(String.valueOf(1),"no")+getPageNStr(String.valueOf(2),"no")+getPageNStr(String.valueOf(3),"no")+getPageNStr(String.valueOf(4),"yes")+getPageNStr(String.valueOf(5),"no")+dig+getPageNStr(String.valueOf(totalPages-1),"no")+getPageNStr(String.valueOf(totalPages),"no")+next_show;
	    }else if(currentPage==5){//5、当前页等于5时，前面到5为止，上页启用，后面出现省略号。例：上一页 1 2 3 4 5..45 46 下一页
	    	this.pageStr=prev_show+getPageNStr(String.valueOf(1),"no")+getPageNStr(String.valueOf(2),"no")+getPageNStr(String.valueOf(3),"no")+getPageNStr(String.valueOf(4),"no")+getPageNStr(String.valueOf(5),"yes")+getPageNStr(String.valueOf(6),"no")+dig+getPageNStr(String.valueOf(totalPages-1),"no")+getPageNStr(String.valueOf(totalPages),"no")+next_show;	   

	    
	    }else if(currentPage>=6&&currentPage<(totalPages-4)){//1、如果当前页大于等于6时，小于总页数减2，前后都出现省略号
			this.pageStr=prev_show+getPageNStr(String.valueOf(1),"no")+getPageNStr(String.valueOf(2),"no")+dig;
			this.pageStr=this.pageStr+getPageNStr(String.valueOf(currentPage-1),"no")+getPageNStr(String.valueOf(currentPage),"yes")+getPageNStr(String.valueOf(currentPage+1),"no");
			this.pageStr=this.pageStr+dig+getPageNStr(String.valueOf(totalPages-1),"no")+getPageNStr(String.valueOf(totalPages),"no")+next_show;
		
		}else if(currentPage==(totalPages-4)){//倒数第5个,上一页 1 2..41 42 43 44 45 46 下一页
			this.pageStr=prev_show+getPageNStr(String.valueOf(1),"no")+getPageNStr(String.valueOf(2),"no");
			this.pageStr=this.pageStr+dig;
			this.pageStr=this.pageStr+getPageNStr(String.valueOf(totalPages-5),"no")+getPageNStr(String.valueOf(totalPages-4),"yes")+getPageNStr(String.valueOf(totalPages-3),"no")+getPageNStr(String.valueOf(totalPages-2),"no")+getPageNStr(String.valueOf(totalPages-1),"no")+getPageNStr(String.valueOf(totalPages),"no")+next_show;
		}else if(currentPage==(totalPages-3)){//倒数第4个,上一页 1 2..42 43 44 45 46 下一页
			this.pageStr=prev_show+getPageNStr(String.valueOf(1),"no")+getPageNStr(String.valueOf(2),"no");
			this.pageStr=this.pageStr+dig;
			this.pageStr=this.pageStr+getPageNStr(String.valueOf(totalPages-4),"no")+getPageNStr(String.valueOf(totalPages-3),"yes")+getPageNStr(String.valueOf(totalPages-2),"no")+getPageNStr(String.valueOf(totalPages-1),"no")+getPageNStr(String.valueOf(totalPages),"no")+next_show;
		}else if(currentPage==(totalPages-2)){//倒数第3个,上一页 1 2..43 44 45 46 下一页
			this.pageStr=prev_show+getPageNStr(String.valueOf(1),"no")+getPageNStr(String.valueOf(2),"no");
			this.pageStr=this.pageStr+dig;
			this.pageStr=this.pageStr+getPageNStr(String.valueOf(totalPages-3),"no")+getPageNStr(String.valueOf(totalPages-2),"yes")+getPageNStr(String.valueOf(totalPages-1),"no")+getPageNStr(String.valueOf(totalPages),"no")+next_show;
		}else if(currentPage==(totalPages-1)){//倒数第2个,上一页 1 2..44 45 46 下一页
			this.pageStr=prev_show+getPageNStr(String.valueOf(1),"no")+getPageNStr(String.valueOf(2),"no");
			this.pageStr=this.pageStr+dig;
			this.pageStr=this.pageStr+getPageNStr(String.valueOf(totalPages-2),"no")+getPageNStr(String.valueOf(totalPages-1),"yes")+getPageNStr(String.valueOf(totalPages),"no")+next_show;
		}else if(currentPage==(totalPages)){//倒数第1个,上一页 1 2..44 45 46 下一页(no)
			this.pageStr=prev_show+getPageNStr(String.valueOf(1),"no")+getPageNStr(String.valueOf(2),"no");
			this.pageStr=this.pageStr+dig;
			this.pageStr=this.pageStr+getPageNStr(String.valueOf(totalPages-2),"no")+getPageNStr(String.valueOf(totalPages-1),"no")+getPageNStr(String.valueOf(totalPages),"yes")+next_no;
		}else{
			
		}
		
	}else{//如果总页数小于9的时候
	
			if(currentPage==1&&currentPage!=totalPages){
				this.pageStr=prev_no;
				for(int i=1;i<=totalPages;i++){
					if(i==1){
						this.pageStr=this.pageStr+getPageNStr(String.valueOf(i),"yes");
					}else{
						this.pageStr=this.pageStr+getPageNStr(String.valueOf(i),"no");
					}
					
				}
				this.pageStr=this.pageStr+next_show;
			}else if(currentPage==1&&currentPage==totalPages){
				        this.pageStr=prev_no;
						this.pageStr=this.pageStr+getPageNStr(String.valueOf(1),"yes");
				        this.pageStr=this.pageStr+next_no;
				
				
			}else if(currentPage!=1&&currentPage==totalPages){
				this.pageStr=prev_show;
				for(int i=1;i<=totalPages;i++){
					if(i==currentPage){
						this.pageStr=this.pageStr+getPageNStr(String.valueOf(i),"yes");
					}else{
						this.pageStr=this.pageStr+getPageNStr(String.valueOf(i),"no");
					}
					
				}
				this.pageStr=this.pageStr+next_no;	
			}else{
				this.pageStr=prev_show;
				for(int i=1;i<=totalPages;i++){
					if(i==currentPage){
						this.pageStr=this.pageStr+getPageNStr(String.valueOf(i),"yes");
					}else{
						this.pageStr=this.pageStr+getPageNStr(String.valueOf(i),"no");
					}
					
				}
				this.pageStr=this.pageStr+next_show;	
			}
		

	}
	

	
	//开启转到
	if(this.jumpEnabled!=null&&this.jumpEnabled.equals("true")){
		this.pageStr="<input type=\"button\" value=\"转到\" style=\"height:23px;color:"+this.normalFontColor+";border: solid 1px "+this.normalBorderColor+";BACKGROUND: "+this.normalBgColor+";CURSOR: hand\" onClick=\"jump()\"> <input type=\"text\" id=\"jumpPage\" style=\"width:30px;height:23px;color:"+this.normalFontColor+";border: solid 1px "+this.normalBorderColor+";BACKGROUND: "+this.normalBgColor+"\"> 　"+this.pageStr;
	} 
	
	if(totalPages==0){
		this.pageStr="";
	}

return pageStr;
}

	public static int getPageNumber(String page){
		int ss=1;
		try{
			
		  if(page==null||"null".equals(page)){
			  ss= 1;
          }else{
    		ss=Integer.parseInt(page);
    		if(ss<=0){
    			 ss=1;
    		}	  			  
          }	
        }catch(Exception e){
			return 1;
		}
        return ss;
	}
}


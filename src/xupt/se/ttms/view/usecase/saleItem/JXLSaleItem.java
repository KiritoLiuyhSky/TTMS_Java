package xupt.se.ttms.view.usecase.saleItem;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xupt.se.ttms.model.Sale;
import xupt.se.ttms.model.SaleItem;
import xupt.se.ttms.service.SaleItemSrv;
import xupt.se.ttms.service.SaleSrv;
import xupt.se.ttms.view.usecase.sale.JXLSale;

public class JXLSaleItem {
	
	public JXLSaleItem(){
		 
	 }
	 
	 public int excle() {
		 
	      String[] title = {"销售明细id","影票id","销售id","金额"};     
	        
	        try {     
	        	
	        	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
//	        	System.out.println(df.format(new Date()));
	            
	             String filePath = "E:\\workspace\\TTMS_JAVA\\resource\\excel\\saleitem\\SaleItem_" + df.format(new Date()).toString() + ".xls";     
	             WritableWorkbook wwb;         
	             OutputStream os = new FileOutputStream(filePath);     
	             wwb = Workbook.createWorkbook(os);      
	             
	             
	           // 添加第一个工作表并设置第一个Sheet的名字     
	           WritableSheet sheet = wwb.createSheet("统计", 0);     
	             Label label;     
	             for(int i=0;i<title.length;i++){     
	               // Label(x,y,z) 代表单元格的第x+1列，第y+1行, 内容z     
	               // 在Label对象的子对象中指明单元格的位置和内容     
	                label = new Label(i,0,title[i]);     
	                // 将定义好的单元格添加到工作表中     
	               sheet.addCell(label);     
	            }     
	             
	             
	            // 下面是填充数据     
	             /*    
	              * 保存数字到单元格，需要使用jxl.write.Number  
	              * 必须使用其完整路径，否则会出现错误  
	              * */
	             
	            List<SaleItem> SaleItemlist = new SaleItemSrv(). FetchAll();
	            
	            int y = 1;
	            
	            for (SaleItem itm : SaleItemlist) {
	         
	                jxl.write.Number id = new jxl.write.Number(0,y,itm.getId());     
	                sheet.addCell(id);     
	           
	                jxl.write.Number em_id = new jxl.write.Number(1,y,itm.getTicketId());        
	                sheet.addCell(em_id);
	                
	                /*  
	               * 定义对于显示金额的公共格式  
	                 * jxl会自动实现四舍五入  
	                 * 例如 2.456会被格式化为2.46,2.454会被格式化为2.45  
	                 * */   
	                jxl.write.NumberFormat nf = new jxl.write.NumberFormat("#.##");               
	                jxl.write.WritableCellFormat wcf = new jxl.write.WritableCellFormat(nf);     
	                 
	                jxl.write.Number nb = new jxl.write.Number(3,y,itm.getPrice(),wcf);     
	                 sheet.addCell(nb);  
	        
	                jxl.write.Number sale = new jxl.write.Number(2,y,itm.getSaleId()); 
	                sheet.addCell(sale);   
	                 
	                 y++;
	            }
	            
	            
	           // 写入数据     
	           wwb.write();     
	            // 关闭文件     
	           wwb.close();     
	      
	        } catch (Exception e) {     
	            System.out.println("---出现异常---");     
	            e.printStackTrace();     
	        }   
		 
		 return 0;
	 }

    public static void main(String[] args) {     
       // 准备设置excel工作表的标题     
   	 JXLSale it = new JXLSale();
   	 it.excle();
   
   }     
}

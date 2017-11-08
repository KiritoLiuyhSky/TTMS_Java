package xupt.se.ttms.view.component;

import java.awt.Font;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import xupt.se.ttms.model.Movie;
import xupt.se.ttms.model.Sale;
import xupt.se.ttms.service.SaleSrv;

public class BarChart {
    ChartPanel frame1;
 
    public BarChart() {
        CategoryDataset dataset = getDataSet();
        JFreeChart chart = ChartFactory.createBarChart3D("销售额统计", // 图表标题
                "销售时间", // 目录轴的显示标签
                "销售额", // 数值轴的显示标签
                dataset, // 数据集
                PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                true, // 是否显示图例(对于简单的柱状图必须是false)
                false, // 是否生成工具
                false // 是否生成URL链接
                );
 
        // 从这里开始
        CategoryPlot plot = chart.getCategoryPlot();// 获取图表区域对象
        CategoryAxis domainAxis = plot.getDomainAxis(); // 水平底部列表
        domainAxis.setLabelFont(new Font("华文行楷", Font.BOLD, 14)); // 水平底部标题
        domainAxis.setTickLabelFont(new Font("华文行楷", Font.BOLD, 12)); // 垂直标题
        ValueAxis rangeAxis = plot.getRangeAxis();// 获取柱状
        rangeAxis.setLabelFont(new Font("华文行楷", Font.BOLD, 15));
        chart.getLegend().setItemFont(new Font("华文行楷", Font.BOLD, 15));
        chart.getTitle().setFont(new Font("华文行楷", Font.BOLD, 20));// 设置标题字体
 
        // 到这里结束，虽然代码有点多，但只为一个目的，解决汉字乱码问题
 
        frame1 = new ChartPanel(chart, true); // 这里也可以用chartFrame,可以直接生成一个独立的Frame
 
    }
 
    private static CategoryDataset getDataSet() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        List<Sale> data = new ArrayList<Sale>();
//        NewsDao dao = new NewsDao();
//        ArrayList<news> list = dao.queryAll();]
        
        List<Sale> salist = new SaleSrv().FetchAll();
        
        double value =0;
        String rowKeys=null;
        String columnKeys=null;
        for (Sale news : salist) {
//          data.add(new News(news.getNewsId(), news.getTypeId(), news
//                  .getTitle(), news.getPublishTime(), news.getBody(), news
//                  .getTag(), news.getAuthor(), news.getClicks(), news
//                  .getImgUrl()));
            value = news.getPayment();
            
//            rowKeys = news.getTime().toString();
            columnKeys = String.valueOf(news.getId());
            
//            columnKeys = String.valueOf(news.getId());
            rowKeys = news.getTime().toString();
            
            dataset.addValue(value, rowKeys, columnKeys);
//            System.out.println(value+"  "+rowKeys+"  "+columnKeys);
        }
//      if(value!=0&&rowKeys!=null&&columnKeys!=null){
//          System.out.println(value+"  "+rowKeys+"  "+columnKeys);
//          dataset.addValue(value, rowKeys, columnKeys);
//      }
//      dataset.addValue(100, "北京", "苹果");
//      dataset.addValue(100, "上海", "苹果");
//      dataset.addValue(100, "广州", "苹果");
//      dataset.addValue(200, "北京", "梨子");
//      dataset.addValue(200, "上海", "梨子");
//      dataset.addValue(200, "广州", "梨子");
//      dataset.addValue(300, "北京", "葡萄");
//      dataset.addValue(300, "上海", "葡萄");
//      dataset.addValue(300, "广州", "葡萄");
//      dataset.addValue(400, "北京", "香蕉");
//      dataset.addValue(400, "上海", "香蕉");
//      dataset.addValue(400, "广州", "香蕉");
//      dataset.addValue(500, "北京", "荔枝");
//      dataset.addValue(500, "上海", "荔枝");
//      dataset.addValue(500, "广州", "荔枝");
        return dataset;
    }
 
    public ChartPanel getChartPanel() {
        return frame1;
 
    }
 
    public static void main(String[] args) {
        JFrame frame = new JFrame("销售额统计");

        frame.add(new BarChart().getChartPanel()); // 添加柱形图

        frame.setBounds(50, 50, 800, 600);
        frame.setVisible(true);
    }
}

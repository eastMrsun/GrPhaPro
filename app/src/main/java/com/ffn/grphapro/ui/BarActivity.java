package com.ffn.grphapro.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;

import com.ffn.grphapro.R;
import com.ffn.grphapro.mrsunpackage.mrsunview.mrsunutils.DateUtils;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.PercentFormatter;
import com.github.mikephil.charting.utils.ValueFormatter;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;


/**
 * Created by GT on 2017/8/8.
 */
public class BarActivity extends AppCompatActivity {
    //Bar
    private BarChart barChart;
    private XAxis xAxis;
    String[] barPlaceItem=new String[]{"长沙市","岳阳","郴州","常德","邵阳","株洲","湘潭","张家界"};
    Integer[] barCountItem =new Integer[]{21,56,55,87,54,65,32,15};
    //Line
    LineChartView lineChart;
    String[] XlineDateItem = new String[7];//X轴的标注
    int[] yLineDateItem = {1000, 2211, 3654, 1455, 4522, 3144, 2566};//图表的数据点
    private List<PointValue> mPointValues = new ArrayList<PointValue>();
    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();

    //Yuan
    private PieChart pieChart;
    private PieChart pieChart1;
    private String[] bigBodyName =new String[]{"腾讯","新闻","网易","阿里","论坛","移动APP","传统媒体","博客"};
    private String[] smallBodyName =new String[]{"论坛","移动APP","传统媒体","博客"};
    private Integer[] bigItem =new Integer[]{23,18,36,65,54,95,55,99};
    private Integer[] smallItem =new Integer[]{54,95,55,99};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bar);
        //1 访问接口 三个线程异步请求 圆表 折现表 柱状表的数据 依次对每个对应的数组进行初始化
        //2 初始化成功后分别走以下三个方法
        ArrayList<String> test = DateUtils.test(7);
        for (int i = 0; i < test.size(); i++) {
            XlineDateItem[i]=test.get(i);
        }
        System.out.print(test);
        initBarDate();
        initineDate();
        initYuanDate();


    }
    //初始化圆柱表
    private void initBarDate() {
        barChart= (BarChart) findViewById(R.id.barChart);
        //1、基本设置
        xAxis=barChart.getXAxis();
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        barChart.setDrawGridBackground(false); // 是否显示表格颜色
        barChart.getAxisLeft().setDrawAxisLine(false);
        barChart.setTouchEnabled(false); // 设置是否可以触摸
        barChart.setDragEnabled(true);// 是否可以拖拽
        barChart.setScaleEnabled(true);// 是否可以缩放
        //2、y轴和比例尺


        barChart.getAxisLeft().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);
        MrsunMarkView markView=new MrsunMarkView(this,R.layout.markview);
        barChart.setMarkerView(markView);
        Legend legend = barChart.getLegend();//隐藏比例尺
        legend.setEnabled(false);
        //3、x轴数据,和显示位置

        ArrayList<String> xValues = new ArrayList<String>();
        for (int i = 0; i < barPlaceItem.length; i++) {
            xValues.add(barPlaceItem[i]);
        }
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//数据位于底部
        //4、y轴数据

        ArrayList<BarEntry> yValues = new ArrayList<BarEntry>();
        //new BarEntry(20, 0)前面代表数据，后面代码柱状图的位置；
        for (int i = 0; i < barCountItem.length; i++) {
            yValues.add(new BarEntry(barCountItem[i],i));
        }
        //5、设置显示的数字为整形
        BarDataSet barDataSet=new BarDataSet(yValues,"");
        barDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float v) {
                int n = (int) v;
                return n + "k";
            }
        });
        //6、设置柱状图的颜色
        barDataSet.setColors(new int[]{Color.rgb(104, 202, 37), Color.rgb(192, 32, 32),
                Color.rgb(34, 129, 197), Color.rgb(175, 175, 175),Color.rgb(15, 177, 175),Color.rgb(115, 175, 175),
                Color.rgb(175, 125, 75),Color.rgb(175, 111, 175)});
        //7、显示，柱状图的宽度和动画效果
        BarData barData = new BarData(xValues, barDataSet);
        barDataSet.setBarSpacePercent(40f);//值越大，柱状图就越宽度越小；
        barChart.animateY(1500);
        barChart.setData(barData);
    }
    //初始化折现表
    private void initineDate() {
        lineChart = (LineChartView) findViewById(R.id.line_chart);
        getAxisXLables();//获取x轴的标注
        getAxisPoints();//获取坐标点
        initLineChart();//初始化
    }
    //初始化圆饼表
    private void initYuanDate() {
        pieChart = (PieChart) findViewById(R.id.pie_chart);
        pieChart1 = (PieChart) findViewById(R.id.pie_chart1);

        init1();
        init2();
    }





    private void init2() {
        //1、基本设置
        pieChart1.setDrawCenterText(true);  //饼状图中间文字不显示
        pieChart1.setDescriptionTextSize(5);
        pieChart1.setCenterTextSize(5);
        pieChart1.setDescription("");
        pieChart1.setDrawHoleEnabled(false);    //设置实心/空心
        pieChart1.setRotationAngle(90); // 初始旋转角度

        //2、添加数据
        ArrayList<String> xValues1 = new ArrayList<>();  //xVals用来表示每个饼块上的内容
        for (int i = 0; i < smallBodyName.length; i++) {
            xValues1.add(smallBodyName[i]);
        }

        ArrayList<Entry> yValues1 = new ArrayList<>();
        for (int i = 0; i < smallItem.length; i++) {
            yValues1.add(new Entry(smallItem[i],i));
        }
        //3、y轴数据
        PieDataSet pieDataSet1 = new PieDataSet(yValues1,"");
        pieDataSet1.setSliceSpace(0f); //设置个饼状图之间的距离
        //4、设置颜色
        ArrayList<Integer> colors1 = new ArrayList<>();
        colors1.add(Color.rgb(205, 205, 205));
        colors1.add(Color.rgb(114, 188, 223));
        colors1.add(Color.rgb(255, 123, 124));
        colors1.add(Color.rgb(57, 135, 200));
        colors1.add(Color.rgb(255, 233, 55));
        colors1.add(Color.rgb(57, 45, 200));
        colors1.add(Color.rgb(57, 135, 124));
        colors1.add(Color.rgb(57, 156, 200));
        pieDataSet1.setColors(colors1);
        //5、 设置数据
        PieData pieData1 = new PieData(xValues1, pieDataSet1);
        DisplayMetrics metrics1 = getResources().getDisplayMetrics();
        float px1 = 5 * (metrics1.densityDpi / 160f);
        pieDataSet1.setSelectionShift(px1); // 选中态多出的长度
        pieData1.setValueFormatter(new PercentFormatter());//显示百分比
        //6、去掉/增加 比例尺和说明
        Legend legend1 = pieChart1.getLegend();//下标说明，false
        legend1.setEnabled(false);

        //7、显示百分比
        pieData1.setValueFormatter(new PercentFormatter());

        //8、显示
        pieChart1.setData(pieData1);
    }

    private void init1() {
        //1、基本设置
        pieChart.setDrawCenterText(false);  //饼状图中间文字不显示
        pieChart.setDescription("");
        pieChart.setDrawHoleEnabled(true);    //设置实心/空心
        pieChart.setRotationAngle(90); // 初始旋转角度

        //2、添加数据
        ArrayList<String> xValues = new ArrayList<>();  //xVals用来表示每个饼块上的内容
        for (int i = 0; i < bigBodyName.length; i++) {
            xValues.add(bigBodyName[i]);
        }

        ArrayList<Entry> yValues = new ArrayList<>();
        for (int i = 0; i < bigItem.length; i++) {
            yValues.add(new Entry(bigItem[i],i));
        }
        //3、y轴数据
        PieDataSet pieDataSet = new PieDataSet(yValues,"");
        pieDataSet.setSliceSpace(0f); //设置个饼状图之间的距离
        //4、设置颜色
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(205, 205, 205));
        colors.add(Color.rgb(114, 188, 223));
        colors.add(Color.rgb(255, 123, 124));
        colors.add(Color.rgb(57, 135, 200));
        colors.add(Color.rgb(255, 233, 55));
        colors.add(Color.rgb(57, 45, 200));
        colors.add(Color.rgb(57, 135, 124));
        colors.add(Color.rgb(57, 156, 200));
        pieDataSet.setColors(colors);
        //5、 设置数据
        PieData pieData = new PieData(xValues, pieDataSet);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = 5 * (metrics.densityDpi / 160f);
        pieDataSet.setSelectionShift(px); // 选中态多出的长度
        pieData.setValueFormatter(new PercentFormatter());//显示百分比
        //6、去掉/增加 比例尺和说明
        Legend legend = pieChart.getLegend();//下标说明，false
        legend.setEnabled(false);

        //7、显示百分比
        pieData.setValueFormatter(new PercentFormatter());

        //8、显示
        pieChart.setData(pieData);
    }
    private void initLineChart() {
        Line line = new Line(mPointValues).setColor(Color.parseColor("#9EC7EF"));  //折线的颜色（橙色）
        List<Line> lines = new ArrayList<Line>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line.setCubic(false);//曲线是否平滑，即是曲线还是折线
        line.setFilled(true);//是否填充曲线的面积
        line.setHasLabels(true);//曲线的数据坐标是否加上备注
//        line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(true);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextSize(10);//设置字体大小
        axisX.setMaxLabelChars(7); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
        //data.setAxisXTop(axisX);  //x 轴在顶部
        axisX.setHasLines(false); //x 轴分割线

        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis();  //Y轴
        axisY.setHasLines(true);
        axisY.setTextSize(10);//设置字体大小
        axisY.setMaxLabelChars(4);
        data.setAxisYLeft(axisY);  //Y轴设置在左边
//        data.setAxisYRight(axisY);  //y轴设置在右边


        //设置行为属性，支持缩放、滑动以及平移
        lineChart.setInteractive(true);
        lineChart.setZoomType(ZoomType.HORIZONTAL);
        lineChart.setMaxZoom((float) 1);//最大方法比例
        lineChart.setInteractive(true);
        lineChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChart.setLineChartData(data);
        lineChart.setVisibility(View.VISIBLE);
        /**注：下面的7，10只是代表一个数字去类比而已
         * 当时是为了解决X轴固定数据个数。见（http://forum.xda-developers.com/tools/programming/library-hellocharts-charting-library-t2904456/page2）;
         */
        Viewport v = new Viewport(lineChart.getMaximumViewport());
        v.left = 0;
        v.right = 5;
        lineChart.setCurrentViewport(v);
    }

    private void getAxisPoints() {
        for (int i = 0; i < yLineDateItem.length; i++) {
            mPointValues.add(new PointValue(i, yLineDateItem[i]));
        }
    }

    private void getAxisXLables() {
        for (int i = 0; i < XlineDateItem.length; i++) {
            mAxisXValues.add(new AxisValue(i).setLabel(XlineDateItem[i]));
        }
    }

}

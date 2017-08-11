package com.ffn.grphapro.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import com.ffn.grphapro.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.PercentFormatter;

import java.util.ArrayList;

/**
 * Created by GT on 2017/8/9.
 */
public class YuanActivity  extends AppCompatActivity{

    private PieChart pieChart;
    private PieChart pieChart1;
    private ArrayList<String> arrayList;
    private ArrayList<String> arrayList1;
    private String[] bodyname=new String[]{"腾讯","新闻","网易","阿里","论坛","移动APP","传统媒体","博客"};
    private String[] bodyname1=new String[]{"论坛","移动APP","传统媒体","博客"};
    private Integer[] intname=new Integer[]{23,18,36,65,54,95,55,99};
    private Integer[] intname1=new Integer[]{54,95,55,99};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yuan);
        pieChart = (PieChart) findViewById(R.id.pie_chart);
        pieChart1 = (PieChart) findViewById(R.id.pie_chart1);
        arrayList=new ArrayList<String>();
        arrayList1=new ArrayList<String>();

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
        for (int i = 0; i < bodyname1.length; i++) {
            xValues1.add(bodyname1[i]);
        }

        ArrayList<Entry> yValues1 = new ArrayList<>();
        for (int i = 0; i < intname1.length; i++) {
            yValues1.add(new Entry(intname1[i],i));
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
        for (int i = 0; i < bodyname.length; i++) {
            xValues.add(bodyname[i]);
        }

        ArrayList<Entry> yValues = new ArrayList<>();
        for (int i = 0; i < intname.length; i++) {
            yValues.add(new Entry(intname[i],i));
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
}

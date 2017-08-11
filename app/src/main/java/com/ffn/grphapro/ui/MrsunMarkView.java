package com.ffn.grphapro.ui;

import android.content.Context;
import android.widget.TextView;

import com.ffn.grphapro.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;

/**
 * Created by GT on 2017/8/9.
 */
public class MrsunMarkView extends MarkerView {
    TextView tvContent;
    public MrsunMarkView(Context context, int layoutResource) {
        super(context, layoutResource);
        tvContent=(TextView)findViewById(R.id.mktv);
    }

    @Override
    public void refreshContent(Entry entry, int i) {
        tvContent.setText("" + entry.getVal());
    }

    @Override
    public int getXOffset() {
        return -(getWidth() / 2);
    }

    @Override
    public int getYOffset() {
        return -getHeight();
    }
}

package com.bsbdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ScrollingActivity extends AppCompatActivity implements View.OnClickListener {

    private BottomSheetBehavior behavior;
    private int state = BottomSheetBehavior.STATE_COLLAPSED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        initBottomView();

        Button btn_showBottom = (Button) findViewById(R.id.btn_showBottom);
        assert btn_showBottom != null;
        btn_showBottom.setOnClickListener(this);
    }

    private void initBottomView() {
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.cl_activity_scroll);
        assert coordinatorLayout != null;
        View view = coordinatorLayout.findViewById(R.id.ns_content_scroll);
        behavior = BottomSheetBehavior.from(view);

        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            boolean first = true;

            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                state = newState;
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                if (first) {
                    first = false;
                    bottomSheet.setTranslationY(0);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        behavior.setState(state == BottomSheetBehavior.STATE_COLLAPSED ? BottomSheetBehavior.STATE_EXPANDED
                : BottomSheetBehavior.STATE_COLLAPSED);
    }
}

package com.example.maku.latestnews;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Maku on 2017-09-18.
 */

public class Decoration extends RecyclerView.ItemDecoration {
    int Space;




    public Decoration(int Space) {
        this.Space=Space;
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left=Space;
        outRect.bottom =Space;
        outRect.right=Space;

        if(parent.getChildLayoutPosition(view) == 0){
            outRect.top=Space;
        }
    }
}

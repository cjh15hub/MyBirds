package com.cjh15hub.mybirds;

import android.graphics.Rect;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by MSI DRAGON on 3/1/2017.
 */

public class BackgroundItemDecoration extends RecyclerView.ItemDecoration {

    private final int mOddBackground;
    private final int mEvenBackground;

    public BackgroundItemDecoration(@DrawableRes int evenBackground, @DrawableRes int oddBackground) {
        mOddBackground = oddBackground;
        mEvenBackground = evenBackground;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        view.setBackgroundResource(position % 2 == 0 ? mEvenBackground : mOddBackground);
        view.findViewById(R.id.sep_bar).setBackgroundResource(position % 2 == 0 ? R.color.colorPrimary: R.color.colorAccent);
    }
}

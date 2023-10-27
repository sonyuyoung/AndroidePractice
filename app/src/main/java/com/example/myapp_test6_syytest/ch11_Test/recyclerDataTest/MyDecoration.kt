package com.example.myapp_test6_syytest.ch11_Test.recyclerDataTest

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp_test6_syytest.R


// 리사이클러 뷰에  구분선 넣기 이미지 목록요소를 기준으로 전후 넣기
class MyDecoration(val context: Context): RecyclerView.ItemDecoration() {
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        c.drawBitmap(
            //배경
            BitmapFactory.decodeResource(context.getResources(), R.drawable.matzip), 0f,0f,null);
    }


    //전경 foreground
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        //뷰 사이즈 계산
        val width = parent.width
        val height = parent.height
        //이미지 사이즈 계산
        val dr: Drawable? = ResourcesCompat.getDrawable(context.getResources(), R.drawable.matzip, null)
        val drWidth = dr?.intrinsicWidth
        val drHeight = dr?.intrinsicHeight
        //이미지가 그려질 위치 계산 : 정중앙을 계산식으로 표현함
        val left = width / 2 - drWidth?.div(2) as Int
        val top = height / 2 - drHeight?.div(2) as Int
        c.drawBitmap(
            BitmapFactory.decodeResource(context.getResources(), R.drawable.matzip),
            left.toFloat(),
            top.toFloat(),
            null
        )

    }


    // 구분선
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val index = parent.getChildAdapterPosition(view) + 1
        //목록요소의 갯수가 3개씩
        if (index % 3 == 0) //left, top, right, bottom
            outRect.set(10, 10, 10, 100)
        else
            outRect.set(10, 10, 10, 0)

        view.setBackgroundColor(Color.GREEN)
        // 픽셀단위 높이
        ViewCompat.setElevation(view, 50.0f)

    }
}

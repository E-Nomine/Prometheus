package com.nomine.prometheus.ui.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;
import com.nomine.prometheus.BaseApplication;

/**
 * Created by E Nomine on 2016/3/12.
 */
public class PullLoadMoreRecyclerView extends RecyclerView implements LoadFinishCallBack{

    private onLoadMoreListener loadMoreListener;
    private boolean isLoadingMore;

    public PullLoadMoreRecyclerView(Context context) {
        this(context, null);
    }

    public PullLoadMoreRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullLoadMoreRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.isLoadingMore = false;
        setOnScrollListener(new AutoLoadScrollListener(null,true,true));
    }

    public void setOnPauseListenerParams(Context context, boolean pauseOnScroll, boolean pauseOnFling) {
        setOnScrollListener(new AutoLoadScrollListener(context, pauseOnScroll, pauseOnFling));
    }

    public void setLoadMoreListener(onLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    @Override
    public void loadFinish(Object object) {
        isLoadingMore = false;
    }

    public interface onLoadMoreListener {
        void loadMore();
    }

    private class AutoLoadScrollListener extends OnScrollListener {

        private final boolean pauseOnScroll;
        private final boolean pauseOnFling;
        private final Context context;

        public AutoLoadScrollListener(Context context, boolean pauseOnScroll, boolean pauseOnFling) {
            super();
            this.pauseOnScroll = pauseOnScroll;
            this.pauseOnFling = pauseOnFling;
            this.context = context;
        }
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            //由于GridLayoutManager是LinearLayoutManager子类，所以也适用
            if (getLayoutManager() instanceof LinearLayoutManager) {
                int lastVisibleItem = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
                int totalItemCount = PullLoadMoreRecyclerView.this.getAdapter().getItemCount();

                //有回调接口，并且不是加载状态，并且剩下2个item，并且向下滑动，则自动加载
                if (loadMoreListener != null && !isLoadingMore && lastVisibleItem >= totalItemCount -
                        2 && dy > 0) {
                    isLoadingMore = true;
                    loadMoreListener.loadMore();
                }
            }
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (this.context != null) {
                switch (newState) {
                    case SCROLL_STATE_IDLE:
                        Glide.with(context).resumeRequestsRecursive();
                        break;
                    case SCROLL_STATE_DRAGGING:
                        if (pauseOnScroll) {
                            Glide.with(context).pauseRequestsRecursive();
                        } else {
                            Glide.with(context).resumeRequestsRecursive();
                        }
                        break;
                    case SCROLL_STATE_SETTLING:
                        if (pauseOnFling) {
                            Glide.with(context).pauseRequestsRecursive();
                        } else {
                            Glide.with(context).resumeRequestsRecursive();
                        }
                        break;
                }
            }
        }
    }

}

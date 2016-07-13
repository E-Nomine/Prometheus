package com.nomine.prometheus.ui.category;

import com.nomine.prometheus.model.SourceModel;
import com.nomine.prometheus.ui.base.IBaseView;

import java.util.List;

/**
 * Created by E Nomine on 2016/4/4.
 */
public interface ICatDetailsView extends IBaseView {
    void showDatas(List<SourceModel> mData);
    void showMoreDatas(List<SourceModel> mData);
    void showErrorView();
}

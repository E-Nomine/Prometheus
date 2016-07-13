package com.nomine.prometheus.ui.mashup;

import com.nomine.prometheus.model.BaseModel;
import com.nomine.prometheus.ui.base.IBaseView;
import java.util.List;

/**
 * MashupView
 * Created by E Nomine on 2016/3/28.
 */
public interface IMashupView extends IBaseView{
    void showDatas(List<BaseModel> mData);
    void showMoreDatas(List<BaseModel> mData);
    void showErrorView();
}
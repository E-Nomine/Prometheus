/*
 * Copyright (c) 2014, 青岛司通科技有限公司 All rights reserved.
 * File Name：ShowToast.java
 * Version：V1.0
 * Author：zhaokaiqiang
 * Date：2014-8-7
 */
package com.nomine.prometheus.utils;

import android.widget.Toast;

import com.nomine.prometheus.BaseApplication;

public class ShowToast {

	public static void Short(CharSequence sequence) {
		Toast.makeText(BaseApplication.getContext(), sequence, Toast.LENGTH_SHORT).show();
	}

	public static void Long(CharSequence sequence) {
		Toast.makeText(BaseApplication.getContext(), sequence, Toast.LENGTH_SHORT).show();
	}

}

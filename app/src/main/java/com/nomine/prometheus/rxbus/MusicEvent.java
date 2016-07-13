package com.nomine.prometheus.rxbus;

import android.support.design.widget.FloatingActionButton;

/**
 * Created by E Nomine on 2016/3/30.
 */
public class MusicEvent {
    String event;
    String url;
    FloatingActionButton floatingActionButton;
    public MusicEvent(String event, String url, FloatingActionButton floatingActionButton) {
        this.event = event;
        this.url = url;
        this.floatingActionButton = floatingActionButton;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public FloatingActionButton getFloatingActionButton() {
        return floatingActionButton;
    }

    public void setFloatingActionButton(FloatingActionButton floatingActionButton) {
        this.floatingActionButton = floatingActionButton;
    }
}

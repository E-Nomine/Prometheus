package com.nomine.prometheus.model;

/**
 * Created by E Nomine on 2016/3/18.
 */
public class MusicModel extends BaseModel {

    /**
     * type : 2
     * song_url : http://luoo-mp3.kssws.ks-cdn.com/low/luoo/radio801/01.mp3
     * vol_date : 2016-03-09
     * vol_desc :
     在和旧时光对峙的日子里，未来显现的是那么的明亮且旷远。尽管你依然会在夜晚的城市里遇见白衣飘飘的少女，遇见吃火锅喝酒流泪的男孩。
     * song_name : One in the Morning
     * album_imgsrc : http://7xkszy.com2.z0.glb.qiniucdn.com/pics/albums/9801/cover.jpg?imageView2/1/w/580/h/580
     * song_album : Some Kind of Champion
     * vol_imgsrc : http://7xkszy.com2.z0.glb.qiniucdn.com/pics/vol/56e04233bae99.jpg?imageView2/1/w/640/h/452
     * _id : {"$oid":"56ea503fe3afed2cf8a9f085"}
     * song_artist : Sara Lov
     * vol_title : 在过去与未来之间来回游走
     */
    private String song_url;
    private String song_name;
    private String song_artist;
    private String song_album;
    private String album_imgsrc;

    private String vol_title;
    private String vol_desc;
    private String vol_imgsrc;
    private String vol_date;

    public String getSong_url() {
        return song_url;
    }

    public void setSong_url(String song_url) {
        this.song_url = song_url;
    }

    public String getVol_date() {
        return vol_date;
    }

    public void setVol_date(String vol_date) {
        this.vol_date = vol_date;
    }

    public String getVol_desc() {
        return vol_desc;
    }

    public void setVol_desc(String vol_desc) {
        this.vol_desc = vol_desc;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getAlbum_imgsrc() {
        return album_imgsrc;
    }

    public void setAlbum_imgsrc(String album_imgsrc) {
        this.album_imgsrc = album_imgsrc;
    }

    public String getSong_album() {
        return song_album;
    }

    public void setSong_album(String song_album) {
        this.song_album = song_album;
    }

    public String getVol_imgsrc() {
        return vol_imgsrc;
    }

    public void setVol_imgsrc(String vol_imgsrc) {
        this.vol_imgsrc = vol_imgsrc;
    }

    public String getSong_artist() {
        return song_artist;
    }

    public void setSong_artist(String song_artist) {
        this.song_artist = song_artist;
    }

    public String getVol_title() {
        return vol_title;
    }

    public void setVol_title(String vol_title) {
        this.vol_title = vol_title;
    }

    @Override
    public int getType() {
        return super.getType();
    }

    @Override
    public void setType(int type) {
        super.setType(type);
    }
}

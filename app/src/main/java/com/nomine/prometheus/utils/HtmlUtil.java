package com.nomine.prometheus.utils;

import com.nomine.prometheus.net.RetrofitManager;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Html工具类
 * Created by E Nomine on 2016/3/28.
 */
public class HtmlUtil {
    public static String getHtml(String content) {
        final StringBuilder sb = new StringBuilder();
        final String base = RetrofitManager.BASE_URL;
        sb.append("<!DOCTYPE html>");
        sb.append("<html dir=\"ltr\" >");
        sb.append("<head>");
        sb.append("<script src=\""+base+ "/static/jquery/jquery.min.js\"></script>");
        sb.append("<link href=\""+base+"/static/themes/content.css\" rel=\"stylesheet\" type=\"text/css\">");
        sb.append("<meta name=\"viewport\" content=\"width=100%; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;\" />");
        sb.append("<meta name=\"app-mobile-web-app-capable\" content=\"yes\"> ");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<div class=\"container\">");
        sb.append("<div class=\"content\">");
        sb.append(content);
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</body>");
        sb.append("<script src = \""+base+"/static/jquery/clearstyle.js\"></script>");
        sb.append("</html>");
        return sb.toString();
    }

    public static String getText(String html,int num){
        String txtcontent = html.replaceAll("</?[^>]+>", ""); //剔出<html>的标签
        txtcontent = txtcontent.replaceAll("\\s*|\t|\r|\n", "");//去除字符串中的空格,回车,换行符,制表符
        num = txtcontent.length()<num?txtcontent.length():num;
        txtcontent = txtcontent.substring(0, num);
        System.out.println(txtcontent);
        return txtcontent;
    }
    public static List<String> getImgStrs(String htmlStr) {
        List<String> imgs = new ArrayList<String>();
        String imagePatternStr = "<img[\\w\\W]*?src=[\"|\']?([\\w\\W]*?)(jpg|png|gif|bmp|jpeg)[\\w\\W]*?/>";
        Pattern imagePattern = Pattern.compile(imagePatternStr,Pattern.CASE_INSENSITIVE);

        Matcher matcher = imagePattern.matcher(htmlStr);
        while (matcher.find()) {
            // img整个标签
            String imageFragment = matcher.group(0);
            // img src中图片的url前缀
            String imageFragmentURL = matcher.group(1);
            // img src中图片的url后缀
            String imageFragmentSuffix = matcher.group(2);
            imgs.add(imageFragmentURL + imageFragmentSuffix);
        }
        return imgs;
    }
    public static String getImgStr(String htmlStr) {
        if(!getImgStrs(htmlStr).isEmpty()){
            return getImgStrs(htmlStr).get(0);
        }
        return null;
    }
}

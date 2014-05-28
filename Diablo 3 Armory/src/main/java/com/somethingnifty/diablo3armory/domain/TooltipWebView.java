package com.somethingnifty.diablo3armory.domain;

import android.webkit.WebView;

public class TooltipWebView {
    private WebView webView;
    private String tooltipParams;
    private String tooltipHtml;

    public TooltipWebView(WebView webView, String tooltipParams){
        this.webView = webView;
        this.tooltipParams = tooltipParams;
        tooltipHtml = "";
    }

    public WebView getWebView() {
        return webView;
    }

    public String getTooltipParams() {
        return tooltipParams;
    }

    public String getTooltipHtml() {
        return tooltipHtml;
    }

    public void setTooltipHtml(String tooltipHtml) {
        this.tooltipHtml = tooltipHtml;
    }
}


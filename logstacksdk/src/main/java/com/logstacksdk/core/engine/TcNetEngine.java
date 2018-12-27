package com.logstacksdk.core.engine;

import android.content.Context;
import android.os.Looper;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.logstacksdk.config.NetConfig;
import com.logstacksdk.config.StaticsConfig;
import com.logstacksdk.utils.LogHeadUtil;
import com.logstacksdk.core.callback.IUpLoadlistener;
import com.logstacksdk.http.TcHttpClient;
import com.logstacksdk.utils.JsonUtil;
import com.logstacksdk.utils.LogUtil;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Set;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.ParseException;

/**
 * Created by LIUYONGKUI726 on 2016-04-18.
 */
public class TcNetEngine {
    private static final String TAG = TcNetEngine.class.getSimpleName();
    private static final boolean debug = true;

    private Context context;

    private TcHttpClient mHttpClient;

    private String mKey;

    /**
     * 重试次数
     */
    protected int mRetrytimes = NetConfig.RETRY_TIMES;


    /**
     * 是否支持断点
     */
    protected boolean mCanContinue;

    private String mHostUrl = NetConfig.ONLINE_URL;

    private PaJsonHttpResponseHandler mTaskHandler;

    private IUpLoadlistener mUpLoadlistener;

    private HashMap<String, String> headers;


    Header[] reqHeaders;

    Header header;

    public TcNetEngine(Context context, IUpLoadlistener upLoadlistener) {

        this(context, null, upLoadlistener);

    }

    public TcNetEngine(Context context, TcHttpClient httpClient, IUpLoadlistener upLoadlistener) {
        this.context = context;
        mHttpClient = httpClient;
        mCanContinue = true;
        mTaskHandler = new PaJsonHttpResponseHandler(true);
        mUpLoadlistener = upLoadlistener;
        init();


    }

    private void init() {

        if (StaticsConfig.DEBUG) {
            mHostUrl = NetConfig.URL;
        }
        headers = new HashMap<String, String>();
    }

    public TcHttpClient getHttpClient() {
        return mHttpClient;
    }

    public void setHttpClient(TcHttpClient mHttpClient) {
        this.mHttpClient = mHttpClient;
    }

    public String start(final Object... strings) {


        String str = JsonUtil.toJSONString(LogHeadUtil.getHeader(context));

        LogUtil.d(TAG, "head:" + str);
        if (headers.size() >= 0) {
            headers.clear();
        }
        headers.put(NetConfig.HEADERS_KEY, URLEncoder.encode(str));
        //headers.put("Accept", "application/json");


        Object string = strings[0];

        LogUtil.d(TAG, "body:" + string);

        if (headers != null && headers.size() > 0) {
            reqHeaders = new Header[headers.size()];
            Set<String> keys = headers.keySet();
            int index = 0;
            for (final String mykey : keys) {
                if (mykey != null && headers.get(mykey) != null) {
                    header = new Header() {
                        @Override
                        public String getName() {
                            return mykey;
                        }

                        @Override
                        public String getValue() {
                            return headers.get(mykey);
                        }

                        @Override
                        public HeaderElement[] getElements() throws ParseException {
                            return new HeaderElement[0];
                        }
                    };
                    reqHeaders[index++] = header;
                }
            }
        }
        Log.d(TAG, "【TcNetEngine.start()】【body=" + string + "】");
        TcHttpClient.post(context, mHostUrl, reqHeaders, JSON.toJSONString(string), "application/json", mTaskHandler);
        return null;
    }

    public void cancel() {
        TcHttpClient.cancle(mKey, true);
    }

    private class PaJsonHttpResponseHandler extends AsyncHttpResponseHandler {

        public PaJsonHttpResponseHandler() {
        }

        public PaJsonHttpResponseHandler(Looper looper) {
            super(looper);
        }

        public PaJsonHttpResponseHandler(boolean usePoolThread) {
            super(usePoolThread);
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

            if (mUpLoadlistener != null) {
                mUpLoadlistener.onSucess();
            }

            for (Header tmp : headers) {
                LogUtil.d(TAG, tmp.getName() + ":" + tmp.getValue());
            }

            LogUtil.d(TAG, "response code: " + statusCode);
            if (statusCode == HttpStatus.SC_OK) {
                LogUtil.d(TAG, "onSuccess");
                mCanContinue = false;
            } else if (statusCode == HttpStatus.SC_PARTIAL_CONTENT) {
                mCanContinue = true;
            }
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            if (mUpLoadlistener != null) {
                mUpLoadlistener.onFailure();
            }
            cancel();
        }

    }

}

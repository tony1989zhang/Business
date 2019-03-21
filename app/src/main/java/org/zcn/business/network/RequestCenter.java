package org.zcn.business.network;

import android.util.Log;

import org.zcn.zcnlib.okhttp.CommonOkHttpClient;
import org.zcn.zcnlib.okhttp.listener.DisposeDataHandle;
import org.zcn.zcnlib.okhttp.listener.DisposeDataListener;
import org.zcn.zcnlib.okhttp.request.CommonRequest;

public class RequestCenter {



    public static void loadHomeData(DisposeDataListener listener){
        CommonOkHttpClient.get(CommonRequest.createGetRequest(Constants.HOME_DATA,
                        null),
                        new DisposeDataHandle(listener));
    }
}

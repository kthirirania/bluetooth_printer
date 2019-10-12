package com.lange.bluetooth_printer;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Context;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.flutter.plugin.common.EventChannel;

/**
 * {描述}
 *
 * @author GUOCHENG LIANG
 * @version 1.0
 * @date 2019-10-12
 */
public class ScanBlueToothEventListener implements EventChannel.StreamHandler{

    private Activity context;
    private EventChannel.EventSink eventSink;
    ScanBlueToothEventListener(Activity activity){
        this.context=activity;
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void event(ScanBlueToothResultEvent event){
        List<String> nameList = new ArrayList<>();
        for (BluetoothDevice bluetoothDevice : event.getBluetoothDevices()) {
            nameList.add(bluetoothDevice.getName());
        }
        this.eventSink.success(new Gson().toJson(nameList));
    }

    @Override
    public void onListen(Object o, EventChannel.EventSink eventSink) {
        this.eventSink=eventSink;
    }

    @Override
    public void onCancel(Object o) {

    }
}

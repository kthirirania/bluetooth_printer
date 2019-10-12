package com.lange.bluetooth_printer;

import com.google.gson.Gson;

import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * BluetoothPrinterPlugin
 */
public class BluetoothPrinterPlugin implements MethodCallHandler {
    /**
     * Plugin registration.
     */
    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "bluetooth_printer/methodChannel");
        channel.setMethodCallHandler(new BluetoothPrinterPlugin());

        EventChannel eventChannel = new EventChannel(registrar.messenger(), "bluetooth_printer/scanBlueToothEvent");
        ScanBlueToothEventListener scanBlueToothEventListener = new ScanBlueToothEventListener(registrar.activity());
        eventChannel.setStreamHandler(scanBlueToothEventListener);
    }

    BlueToothPrinter blueToothPrinter;

    @Override
    public void onMethodCall(MethodCall call, Result result) {
        if(blueToothPrinter==null){
            blueToothPrinter = new BlueToothPrinter();
        }
        if (call.method.equals("startScanBlueTooth")) {
            blueToothPrinter.startScanBlueTooth();
        } else if(call.method.equals("isConnected")){
            boolean b = blueToothPrinter.isConnected();
            result.success(b?1:0);
        }else if(call.method.equals("connectBlueTooth")){
            int index = call.argument("index");
            blueToothPrinter.connectBlueTooth(index);
            result.success(1);
        }else if(call.method.equals("print")){
            String orderJsonStr = call.argument("orderJsonStr");
            OrderModel orderModel = new Gson().fromJson(orderJsonStr,OrderModel.class);
            blueToothPrinter.print(orderModel);
        }

        else {
            result.notImplemented();
        }
    }
}

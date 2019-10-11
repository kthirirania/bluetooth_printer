package com.lange.bluetooth_printer;

import com.google.gson.Gson;

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
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "bluetooth_printer");
        channel.setMethodCallHandler(new BluetoothPrinterPlugin());
    }

    @Override
    public void onMethodCall(MethodCall call, Result result) {
        if (call.method.equals("getPlatformVersion")) {
            result.success("Android " + android.os.Build.VERSION.RELEASE);
        } else if (call.method.equals("printOrderTicket")) {
            String orderJsonStr = call.argument("orderJsonStr");
            System.out.println(orderJsonStr);
            OrderModel orderModel = new Gson().fromJson(orderJsonStr,OrderModel.class);
            BlueToothPrinter printer = new BlueToothPrinter(orderModel);
            boolean b = printer.printOrderTicket();
            printer.close();
            result.success(b);
        } else {
            result.notImplemented();
        }
    }
}

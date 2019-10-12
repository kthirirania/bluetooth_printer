package com.lange.bluetooth_printer;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import am.util.printer.PrintExecutor;

/**
 * {描述}
 *
 * @author GUOCHENG LIANG
 * @version 1.0
 * @date 2019-10-10
 */
public class BlueToothPrinter {

    private BluetoothAdapter bluetoothAdapter;
    private BluetoothDevice mDevice;
    private PrintExecutor executor;
    private OrderPrintDataMaker maker;
    List<BluetoothDevice> bondedDeviceSet;

    BlueToothPrinter() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    /*开始扫描蓝牙*/
    public void startScanBlueTooth() {
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            bondedDeviceSet = new ArrayList<>(bluetoothAdapter.getBondedDevices());
            EventBus.getDefault().post(new ScanBlueToothResultEvent(bondedDeviceSet));
        }
    }

    /*连接蓝牙设备*/
    public void connectBlueTooth(int index) {
        mDevice = bondedDeviceSet.get(index);
        executor = new PrintExecutor(mDevice, 80);
        executor.setDevice(mDevice);
    }

    public boolean isConnected() {
        return mDevice == null ? false : true;
    }

    /*打印订单小票*/
    public void print(OrderModel orderModel) {
        maker = new OrderPrintDataMaker(orderModel);
        executor.doPrinterRequestAsync(maker);
    }

    public void close() {
        if (executor != null) {
            executor.closeSocket();
        }
    }


}

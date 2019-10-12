package com.lange.bluetooth_printer;

import android.bluetooth.BluetoothDevice;

import java.util.List;
import java.util.Set;

/**
 * {描述}
 *
 * @author GUOCHENG LIANG
 * @version 1.0
 * @date 2019-10-12
 */
public class ScanBlueToothResultEvent {
    private List<BluetoothDevice> bluetoothDevices;

    public ScanBlueToothResultEvent(List<BluetoothDevice> bluetoothDevices) {
        this.bluetoothDevices = bluetoothDevices;
    }

    public List<BluetoothDevice> getBluetoothDevices() {
        return bluetoothDevices;
    }

    public void setBluetoothDevices(List<BluetoothDevice> bluetoothDevices) {
        this.bluetoothDevices = bluetoothDevices;
    }
}

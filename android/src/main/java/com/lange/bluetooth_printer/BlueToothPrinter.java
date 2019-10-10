package com.lange.bluetooth_printer;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

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

    private  BluetoothAdapter bluetoothAdapter;
    private  BluetoothDevice mDevice;
    private PrintExecutor executor;
    private OrderPrintDataMaker maker;
    private OrderModel orderModel;
    BlueToothPrinter(OrderModel orderModel){
        bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        this.orderModel=orderModel;
    }

    /*打印订单小票*/
    public  boolean printOrderTicket(){
        if(bluetoothAdapter==null || !bluetoothAdapter.isEnabled()){
            return false;
        }
        Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
        if (bondedDevices.isEmpty()){
            System.out.println("bluetooth list is empty");
            return false;
        }else{
            /*默认取第一个元素*/
            mDevice =bondedDevices.iterator().next();
            executor = new PrintExecutor(mDevice, 80);
            executor.setDevice(mDevice);
            maker = new OrderPrintDataMaker(orderModel);
            executor.doPrinterRequestAsync(maker);
            return true;
        }
    }

    public void close(){
        if (executor != null) {
            executor.closeSocket();
        }
    }
}

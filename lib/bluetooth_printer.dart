import 'dart:async';
import 'dart:convert';

import 'package:flutter/services.dart';

class BluetoothPrinter {
  /*方法通道*/
  static const MethodChannel _methodChannel =
      const MethodChannel('bluetooth_printer/methodChannel');

  /*事件通道*/
  static const EventChannel _scanBlueToothEvent =
      const EventChannel('bluetooth_printer/scanBlueToothEvent');

  /*开始扫描蓝牙*/
  Future startScanBlueTooth() async {
    await _methodChannel.invokeMethod('startScanBlueTooth');
  }

  /*连接蓝牙设备*/
  Future<int> connectBlueTooth(int index)async{
    int result = await _methodChannel.invokeMethod('connectBlueTooth',{'index':index});
    return result;
  }

  /*打印*/
  Future<int> print(Map orderInfo)async{
    int result = await _methodChannel.invokeMethod('print',{'orderJsonStr':json.encode(orderInfo)});
    return result;
  }

  /*是否已连接*/
  Future<bool> isConnected()async{
    int result = await _methodChannel.invokeMethod('isConnected');
    return result==1?true:false;
  }

  /*监听扫描蓝牙设备回调事件*/
  Stream<List<dynamic>> get scanBlueToothEvent =>
      _scanBlueToothEvent.receiveBroadcastStream().map((data)=>json.decode(data));
}

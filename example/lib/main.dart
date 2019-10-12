import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:bluetooth_printer/bluetooth_printer.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';
  BluetoothPrinter bluetoothPrinter;
  @override
  void initState() {

    super.initState();

    initPlatformState();
    bluetoothPrinter = new BluetoothPrinter();

  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    String platformVersion;
    // Platform messages may fail, so we use a try/catch PlatformException.

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Column(children: <Widget>[
            IconButton(icon: Icon(Icons.add), onPressed: (){
              bluetoothPrinter.scanBlueToothEvent.listen((data)async{
                print('listent data = $data');
              });
              bluetoothPrinter.startScanBlueTooth();
            }),
            IconButton(icon: Icon(Icons.bluetooth_connected),onPressed: ()async{
              bluetoothPrinter.connectBlueTooth(0);
              bool b =await bluetoothPrinter.isConnected();
              print('isConnected:$b');
            },),
            IconButton(icon: Icon(Icons.print),onPressed: (){
              bluetoothPrinter.print({
              "Id": "15704350570000004",
              "order": {
                "Id": "15704350570000004",
                "MemberId": 4,
                "MerchantId": 79,
                "Receiver": "陈",
                "ReceiverPhone": "15388077737",
                "ReceiverAddress": "湖南软件职业学院qqq",
                "ReceiverLongitude": 112.93244,
                "ReceiverLatitude": 27.90795,
                "CreateTime": "2019-10-07 15:57:45",
                "Source": 2,
                "ExpectedTime": null,
                "Remake": "",
                "FullReduction": 0.0,
                "Deduction": 0.0,
                "TotalAmount": 0.2,
                "IsEvaluation": 1,
                "CompleteTime": "0001-01-01 00:00:00",
                "Staus": 4,
                "CouponId": null,
                "DeductionType": 0,
                "Freight": 0.0,
                "IsMerchantFreight": false,
                "MerchantName": "湘潭市经开区和谐超市",
                "MerchantLogo": "http://xzjssdj.oss-cn-zhangjiakou.aliyuncs.com/Merchant/湘潭市经开区和谐超市/Logo.jpg?Expires=4721967937&OSSAccessKeyId=LTAIiQqDUgjhZnkB&Signature=eLoIqBSYwCJH4MshxbEgpw%2Br4jk%3D&x-oss-process=style%2Fssdj_small",
                "ProductInfo": "怡宝纯净水 等1种商品",
                "MerchantPhone": "13367322881",
                "ShipType": 2,
                "PayMode": 1,
                "RefundAmount": 0.0,
                "Weight": 1110,
                "MerchantFreight": 0.0,
                "ProductAmount": 0.2,
                "ServiceFee": 1.0,
                "ActualIncome": 0.2
              },
              "ExpireTime": "2019-10-07 16:12:45",
              "details": [
                {
                  "Id": "157043505700000041111",
                  "OrderId": "15704350570000004",
                  "ProductId": 1111,
                  "Name": "怡宝纯净水",
                  "ImgUrl": "https://xzjssdj.oss-cn-zhangjiakou.aliyuncs.com/img/6901285863875_1.jpg",
                  "IsSpecial": false,
                  "Description": "555g/ml",
                  "Price": 0.1,
                  "SpecialPrice": 0.0,
                  "Num": 2
                }
              ],
              "HasCourier": false,
              "CourierName": null,
              "CourierPhone": null,
              "prepay_id": "wx07155739391081e3cffc355c1711783100",
              "mt_peisong_id": null
            });
            },),
          ],),
        ),
      ),
    );
  }
}

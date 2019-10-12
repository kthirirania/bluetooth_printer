#import "BluetoothPrinterPlugin.h"
#import "BlueToothPrinter.h"
#import "ScanBlueToothEvent.h"
@implementation BluetoothPrinterPlugin{
     BlueToothPrinter *blueToothPrinter;
}
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
    /*方法通道*/
    FlutterMethodChannel* channel = [FlutterMethodChannel
                                     methodChannelWithName:@"bluetooth_printer/methodChannel"
                                     binaryMessenger:[registrar messenger]];
    BluetoothPrinterPlugin* instance = [[BluetoothPrinterPlugin alloc] init];
    [registrar addMethodCallDelegate:instance channel:channel];
    /*事件通道*/
    FlutterEventChannel* eventChannle = [FlutterEventChannel eventChannelWithName:@"bluetooth_printer/scanBlueToothEvent" binaryMessenger:[registrar messenger]];
    ScanBlueToothEvent *scanBlueToothEvent=[[ScanBlueToothEvent alloc]init];
    [eventChannle setStreamHandler:scanBlueToothEvent];

}

- (void)handleMethodCall:(FlutterMethodCall*)call result:(FlutterResult)result {
    if(!blueToothPrinter){
        blueToothPrinter =[[BlueToothPrinter alloc]init];
    }
    if([@"startScanBlueTooth" isEqualToString:call.method]){
        [blueToothPrinter startScanBlueTooth];
    }
    else if([@"connectBlueTooth" isEqualToString:call.method]){
        int index = [call.arguments[@"index"] intValue];
        [blueToothPrinter connectBlueTooth:index];
        result(@(1));
    }else if([@"print" isEqualToString:call.method]){
        NSString * orderJsonStr = call.arguments[@"orderJsonStr"];
        [blueToothPrinter print:orderJsonStr];
    }
    else if([@"isConnected" isEqualToString:call.method]){
        int r = [blueToothPrinter isConnected];
        result(@(r));
    }
    else {
        result(FlutterMethodNotImplemented);
    }
}

@end


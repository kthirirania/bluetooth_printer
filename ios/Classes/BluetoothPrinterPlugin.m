#import "BluetoothPrinterPlugin.h"
#import "BlueToothPrinter.h"
@implementation BluetoothPrinterPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  FlutterMethodChannel* channel = [FlutterMethodChannel
      methodChannelWithName:@"bluetooth_printer"
            binaryMessenger:[registrar messenger]];
  BluetoothPrinterPlugin* instance = [[BluetoothPrinterPlugin alloc] init];
  [registrar addMethodCallDelegate:instance channel:channel];
}

- (void)handleMethodCall:(FlutterMethodCall*)call result:(FlutterResult)result {
  if ([@"getPlatformVersion" isEqualToString:call.method]) {
    result([@"iOS " stringByAppendingString:[[UIDevice currentDevice] systemVersion]]);
  }
  else if([@"printOrderTicket" isEqualToString:call.method]){
        BlueToothPrinter * printer = [[BlueToothPrinter alloc]init];
        [printer print];
        result(0);
  }
  else {
    result(FlutterMethodNotImplemented);
  }
}

@end

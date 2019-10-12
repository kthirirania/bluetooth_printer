//
//  ScanBlueToothEvent.m
//  JWBluetoothPrinte
//
//  Created by lange on 2019/10/12.
//  Copyright © 2019 张竟巍. All rights reserved.
//

#import "ScanBlueToothEvent.h"
#import "JWBluetoothManage.h"
@implementation ScanBlueToothEvent{
   FlutterEventSink  _eventSink;
}
- (instancetype)init{
    self = [super init];
    if (self) {
         [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(onScanBlueToothResultAction:) name:SCAN_BLUETOOTH_RESULT_NOTIFICATION object:nil];
    }
    return self;
}

/*接收到蓝牙扫描结果*/
-(void)onScanBlueToothResultAction:(NSNotification*)notification{
    NSDictionary * params = notification.userInfo;
    NSArray * arrays = params[@"params"];
    NSMutableArray * resultArray  = [NSMutableArray array];
    for (CBPeripheral * p in arrays) {
        [resultArray addObject:p.name];
    }
    NSData *jsonData = [NSJSONSerialization dataWithJSONObject:resultArray options:0 error:nil];

    NSString *strJson = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
    _eventSink(strJson);
}


-(FlutterError *)onListenWithArguments:(id)arguments eventSink:(FlutterEventSink)events{
    _eventSink=events;
    return nil;
}

-(FlutterError *)onCancelWithArguments:(id)arguments{
    return nil;
}
@end

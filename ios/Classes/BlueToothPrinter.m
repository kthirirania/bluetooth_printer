//
//  BlueToothPrinter.m
//  JWBluetoothPrinte
//
//  Created by lange on 2019/10/11.
//  Copyright © 2019 张竟巍. All rights reserved.
//

#import "BlueToothPrinter.h"
#import "JWBluetoothManage.h"
@implementation BlueToothPrinter
/*result:1成功 0失败*/
-(int)print{
    JWBluetoothManage * manage = [JWBluetoothManage sharedInstance];
    [manage beginScanPerpheralSuccess:^(NSArray<CBPeripheral *> *peripherals, NSArray<NSNumber *> *rssis) {
        [manage stopScanPeripheral];

        NSLog(@"%@",[NSString stringWithFormat:@"扫描完成：%@",peripherals.description]);
        if(peripherals.count>0){
            for (CBPeripheral * p in peripherals) {
                if ([p.name isEqualToString:@"MHT-P16"]) {
                    NSLog(@"已连接：%@",p.name);
                    [manage connectPeripheral:p completion:^(CBPeripheral *perpheral, NSError *error) {
                           if (!error) {
                                 NSLog(@"连接成功:%@",error);
                               [self testprint:manage];
                           }else{
                               NSLog(@"连接失败:%@",error);
                           }
                    }];
                }
            }
        }
    } failure:^(CBManagerState status) {

    }];
    return 0;
}

- (void)testprint:(JWBluetoothManage*)manage{
    NSLog(@"打印连接状态:%@",manage.stage);
//    if (manage.stage != JWScanStageCharacteristics) {
//        return;
//    }
    JWPrinter *printer = [[JWPrinter alloc] init];
    NSString *str1 = @"=============银智付=============";
    [printer appendText:str1 alignment:HLTextAlignmentCenter];
    [printer appendTitle:@"商户名称：" value:@"永和大豆浆"];
    [printer appendTitle:@"商户编号：" value:@"1234567890"];
    [printer appendTitle:@"订单编号：" value:@"MS1234567890"];
    [printer appendTitle:@"交易类型：" value:@"微信支付"];
    [printer appendTitle:@"交易时间：" value:@"2017-06-14"];
    [printer appendTitle:@"金    额：" value:@"1000元"];
    [printer appendFooter:@"欢迎使用银智付!"];
    [printer appendNewLine];
    NSData *mainData = [printer getFinalData];
    [[JWBluetoothManage sharedInstance] sendPrintData:mainData completion:^(BOOL completion, CBPeripheral *connectPerpheral,NSString *error) {
        if (completion) {
            NSLog(@"打印成功");
        }else{
            NSLog(@"写入错误---:%@",error);
        }
    }];
}
@end



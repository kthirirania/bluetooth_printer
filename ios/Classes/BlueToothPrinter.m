//
//  BlueToothPrinter.m
//  JWBluetoothPrinte
//
//  Created by lange on 2019/10/11.
//  Copyright © 2019 张竟巍. All rights reserved.
//

#import "BlueToothPrinter.h"
#import "JWBluetoothManage.h"
#import "ScanBlueToothEvent.h"
#define WeakSelf __block __weak typeof(self)weakSelf = self;
@implementation BlueToothPrinter{
    JWBluetoothManage * manage;
    NSArray<CBPeripheral *> * peripheralArrays;
    CBPeripheral * lastPeripheral;
    bool isConnected;
}

/*扫描设备*/
-(void)startScanBlueTooth{
    WeakSelf;
    manage = [JWBluetoothManage sharedInstance];
    [manage beginScanPerpheralSuccess:^(NSArray<CBPeripheral *> *peripherals, NSArray<NSNumber *> *rssis) {
        peripheralArrays=peripherals;
        [[NSNotificationCenter defaultCenter] postNotificationName:SCAN_BLUETOOTH_RESULT_NOTIFICATION object:nil userInfo: @{@"params":peripherals}];
    } failure:^(CBManagerState status) {
           
    }];
    /*上一次连接的蓝牙设备*/
    [manage autoConnectLastPeripheralCompletion:^(CBPeripheral *perpheral, NSError *error) {
           if (!error) {
               lastPeripheral =perpheral;
           }else{
               
           }
       }];
}

/*连接设备*/
-(int)connectBlueTooth:(int)index{
    CBPeripheral *peripheral = peripheralArrays[index];
    [manage connectPeripheral:peripheral completion:^(CBPeripheral *perpheral, NSError *error) {
        if (!error) {
            isConnected=true;
            NSLog(@"连接成功：%@",perpheral.name);
        }else{
            isConnected=false;
            NSLog(@"连接失败：%@",error);
        }
    }];
    return 0;
}

/*打印订单小票*/
-(void)print:(NSString *)orderJsonStr{
    NSData *jsonData = [orderJsonStr dataUsingEncoding:NSUTF8StringEncoding];
    NSError *err;
    NSDictionary *orderInfo = [NSJSONSerialization JSONObjectWithData:jsonData options:NSJSONReadingMutableContainers
    error:&err];
    NSDictionary * order = orderInfo[@"order"];
    //打印内容
    if (manage.stage != JWScanStageCharacteristics) {
           NSLog(@"打印失败：%@",@"");
           return;
    }
    JWPrinter *printer = [[JWPrinter alloc] init];
    [printer appendHeader:@"随时到家"];
    [printer appendTitle:@"订单号：" value:order[@"Id"]];
    NSString *createTime =order[@"CreateTime"];
    [printer appendTitle:@"下单时间：" value:[createTime substringWithRange:NSMakeRange(0, 16)]];
    [printer appendTitle:@"商户名称：" value:order[@"MerchantName"]];
    [printer appendTitle:@"联系电话：" value:order[@"MerchantPhone"]];
    [printer appendSeperatorLine];
    [printer appendTitle:@"收货人：" value:order[@"Receiver"]];
    [printer appendTitle:@"手机号码：" value:order[@"ReceiverPhone"]];
    [printer appendTitle:@"配送地址：" value:order[@"ReceiverAddress"]];
    [printer appendTitle:@"用户备注：" value:order[@"Remake"]];
    [printer appendSeperatorLine];
    double productTotal = 0;
    NSArray * details = orderInfo[@"details"];
    for (NSDictionary *temp in details) {
        double price = [temp[@"IsSpecial"] boolValue]?[temp[@"SpecialPrice"] doubleValue]:[temp[@"Price"] doubleValue];
        productTotal +=price*[temp[@"Num"] intValue];
        [printer appendTitle:temp[@"Name"] value:@""];
        [printer appendTitle:[NSString stringWithFormat:@"￥%.2f",price] value:[NSString stringWithFormat:@"x%@",temp[@"Num"]]];
        [printer appendSeperatorLine];
    }
    [printer appendTitle:@"商品合计：" value:[NSString stringWithFormat:@"%.2f",productTotal]];
    [printer appendTitle:@"配送费：" value:[NSString stringWithFormat:@"%.2f",[order[@"Freight"] doubleValue]]];
    [printer appendTitle:@"实付金额：" value:[NSString stringWithFormat:@"%.2f",[order[@"TotalAmount"] doubleValue]]];
    [printer appendFooter:@"感谢您对随时到家的信任，期待再次光临！"];
    [printer appendNewLine];
    [printer appendNewLine];
    /*执行打印*/
    NSData *mainData = [printer getFinalData];
    [[JWBluetoothManage sharedInstance] sendPrintData:mainData completion:^(BOOL completion, CBPeripheral *connectPerpheral,NSString *error) {
        if (completion) {
            NSLog(@"打印成功");
        }else{
            NSLog(@"写入错误---:%@",error);
        }
    }];
}

-(int)isConnected{
    return isConnected?1:0;
}

//
- (void)testprint{
    if (manage.stage != JWScanStageCharacteristics) {
        NSLog(@"打印失败：%@",@"");
        return;
    }
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




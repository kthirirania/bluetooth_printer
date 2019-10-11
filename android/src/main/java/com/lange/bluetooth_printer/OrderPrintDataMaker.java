package com.lange.bluetooth_printer;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import am.util.printer.PrintDataMaker;
import am.util.printer.PrinterWriter;
import am.util.printer.PrinterWriter58mm;
import am.util.printer.PrinterWriter80mm;

/**
 * 测试数据生成器
 * Created by Alex on 2016/11/10.
 */

public class OrderPrintDataMaker implements PrintDataMaker {

    private int width=300;
    private int height=255;
    private OrderModel orderModel;
    OrderPrintDataMaker(OrderModel orderModel){
        this.orderModel=orderModel;
    }

    @Override
    public List<byte[]> getPrintData(int type) {
        ArrayList<byte[]> data = new ArrayList<>();
        try {
            PrinterWriter printer;
            printer = type == PrinterWriter58mm.TYPE_58 ? new PrinterWriter58mm(height, width) : new PrinterWriter80mm(height, width);
            printer.setAlignCenter();
            printer.setFontSize(1);
            printer.print("随时到家");
            printer.printLineFeed();

            printer.setFontSize(0);
            printer.setAlignLeft();
            printer.printLineFeed();
            printer.print("订单号："+orderModel.getOrder().getId());
            printer.printLineFeed();
            printer.print("下单时间："+orderModel.getOrder().getCreateTime());
            printer.printLineFeed();
            printer.print("商户名称："+orderModel.getOrder().getMerchantName());
            printer.printLineFeed();
            printer.print("联系电话："+orderModel.getOrder().getMerchantPhone());
            printer.printLineFeed();
            printer.print(getImaginaryLine());
            printer.printLineFeed();
            printer.print("收货人："+orderModel.getOrder().getReceiver());
            printer.printLineFeed();
            printer.print("手机号码：" + orderModel.getOrder().getReceiverPhone());
            printer.printLineFeed();
            printer.print("配送地址：" + orderModel.getOrder().getReceiverAddress());
            printer.printLineFeed();
            printer.print("用户备注：" + orderModel.getOrder().getRemake());
            printer.printLineFeed();
            printer.print(getImaginaryLine());
            printer.printLineFeed();
            /*商品*/
            double productTotal=0;
            for (OrderModel.Details detail : orderModel.getDetails()) {
                double price = detail.isSpecial()?detail.getSpecialPrice():detail.getPrice();
                productTotal+=price*detail.getNum();
                printer.setAlignLeft();
                printer.print(detail.getName());
                printer.printLineFeed();
                String priceStr = "￥"+price;
                String numStr = "x"+detail.getNum();
                printer.print(priceStr+getImaginaryLine(32-priceStr.length()-numStr.length())+numStr);
                printer.printLineFeed();
                printer.print(getImaginaryLine());
                printer.printLineFeed();
            }
            printer.print(String.format("商品合计：%.2f",productTotal));
            printer.printLineFeed();
            printer.print(String.format("配送费：%.2f",orderModel.getOrder().getFreight()));
            printer.printLineFeed();
            printer.print(String.format("实付金额：%.2f",orderModel.getOrder().getTotalAmount()));
            printer.printLineFeed();
            printer.printLineFeed();
            printer.print("感谢您对随时到家的信任，期待再次光临！");
            printer.printLineFeed();

            /*结束*/
            printer.setEmphasizedOff();
            printer.printLineFeed();
            printer.printLineFeed();
            printer.printLineFeed();
            printer.feedPaperCutPartial();
            data.add(printer.getDataAndClose());
            return data;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /*32个字符*/
    private String getImaginaryLine(){
        return "--------------------------------";
    }

    private String getImaginaryLine(int length){
        String result = "";
        for (int i = 0; i < length-1; i++) {
            result+=" ";
        }
        return result;
    }
}

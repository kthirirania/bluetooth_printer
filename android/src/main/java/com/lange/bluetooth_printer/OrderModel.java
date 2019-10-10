package com.lange.bluetooth_printer;

import java.util.List;

/**
 * {描述}
 *
 * @author GUOCHENG LIANG
 * @version 1.0
 * @date 2019-10-10
 */
public class OrderModel {
    private String Id;

    private Order order;

    private String ExpireTime;

    private List<Details> details ;

    private boolean HasCourier;

    private String CourierName;

    private String CourierPhone;

    private String prepay_id;

    private String mt_peisong_id;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getExpireTime() {
        return ExpireTime;
    }

    public void setExpireTime(String expireTime) {
        ExpireTime = expireTime;
    }

    public List<Details> getDetails() {
        return details;
    }

    public void setDetails(List<Details> details) {
        this.details = details;
    }

    public boolean isHasCourier() {
        return HasCourier;
    }

    public void setHasCourier(boolean hasCourier) {
        HasCourier = hasCourier;
    }

    public String getCourierName() {
        return CourierName;
    }

    public void setCourierName(String courierName) {
        CourierName = courierName;
    }

    public String getCourierPhone() {
        return CourierPhone;
    }

    public void setCourierPhone(String courierPhone) {
        CourierPhone = courierPhone;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getMt_peisong_id() {
        return mt_peisong_id;
    }

    public void setMt_peisong_id(String mt_peisong_id) {
        this.mt_peisong_id = mt_peisong_id;
    }

    public class Details{
        private String Id;

        private String OrderId;

        private int ProductId;

        private String Name;

        private String ImgUrl;

        private boolean IsSpecial;

        private String Description;

        private double Price;

        private double SpecialPrice;

        private int Num;

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }

        public String getOrderId() {
            return OrderId;
        }

        public void setOrderId(String orderId) {
            OrderId = orderId;
        }

        public int getProductId() {
            return ProductId;
        }

        public void setProductId(int productId) {
            ProductId = productId;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getImgUrl() {
            return ImgUrl;
        }

        public void setImgUrl(String imgUrl) {
            ImgUrl = imgUrl;
        }

        public boolean isSpecial() {
            return IsSpecial;
        }

        public void setSpecial(boolean special) {
            IsSpecial = special;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String description) {
            Description = description;
        }

        public double getPrice() {
            return Price;
        }

        public void setPrice(double price) {
            Price = price;
        }

        public double getSpecialPrice() {
            return SpecialPrice;
        }

        public void setSpecialPrice(double specialPrice) {
            SpecialPrice = specialPrice;
        }

        public int getNum() {
            return Num;
        }

        public void setNum(int num) {
            Num = num;
        }
    }

    public class Order{
        private String Id;

        private int MemberId;

        private int MerchantId;

        private String Receiver;

        private String ReceiverPhone;

        private String ReceiverAddress;

        private double ReceiverLongitude;

        private double ReceiverLatitude;

        private String CreateTime;

        private int Source;

        private String ExpectedTime;

        private String Remake;

        private double FullReduction;

        private double Deduction;

        private double TotalAmount;

        private int IsEvaluation;

        private String CompleteTime;

        private int Staus;

        private String CouponId;

        private int DeductionType;

        private double Freight;

        private boolean IsMerchantFreight;

        private String MerchantName;

        private String MerchantLogo;

        private String ProductInfo;

        private String MerchantPhone;

        private int ShipType;

        private int PayMode;

        private double RefundAmount;

        private int Weight;

        private double MerchantFreight;

        private double ProductAmount;

        private double ServiceFee;

        private double ActualIncome;

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }

        public int getMemberId() {
            return MemberId;
        }

        public void setMemberId(int memberId) {
            MemberId = memberId;
        }

        public int getMerchantId() {
            return MerchantId;
        }

        public void setMerchantId(int merchantId) {
            MerchantId = merchantId;
        }

        public String getReceiver() {
            return Receiver;
        }

        public void setReceiver(String receiver) {
            Receiver = receiver;
        }

        public String getReceiverPhone() {
            return ReceiverPhone;
        }

        public void setReceiverPhone(String receiverPhone) {
            ReceiverPhone = receiverPhone;
        }

        public String getReceiverAddress() {
            return ReceiverAddress;
        }

        public void setReceiverAddress(String receiverAddress) {
            ReceiverAddress = receiverAddress;
        }

        public double getReceiverLongitude() {
            return ReceiverLongitude;
        }

        public void setReceiverLongitude(double receiverLongitude) {
            ReceiverLongitude = receiverLongitude;
        }

        public double getReceiverLatitude() {
            return ReceiverLatitude;
        }

        public void setReceiverLatitude(double receiverLatitude) {
            ReceiverLatitude = receiverLatitude;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String createTime) {
            CreateTime = createTime;
        }

        public int getSource() {
            return Source;
        }

        public void setSource(int source) {
            Source = source;
        }

        public String getExpectedTime() {
            return ExpectedTime;
        }

        public void setExpectedTime(String expectedTime) {
            ExpectedTime = expectedTime;
        }

        public String getRemake() {
            return Remake;
        }

        public void setRemake(String remake) {
            Remake = remake;
        }

        public double getFullReduction() {
            return FullReduction;
        }

        public void setFullReduction(double fullReduction) {
            FullReduction = fullReduction;
        }

        public double getDeduction() {
            return Deduction;
        }

        public void setDeduction(double deduction) {
            Deduction = deduction;
        }

        public double getTotalAmount() {
            return TotalAmount;
        }

        public void setTotalAmount(double totalAmount) {
            TotalAmount = totalAmount;
        }

        public int getIsEvaluation() {
            return IsEvaluation;
        }

        public void setIsEvaluation(int isEvaluation) {
            IsEvaluation = isEvaluation;
        }

        public String getCompleteTime() {
            return CompleteTime;
        }

        public void setCompleteTime(String completeTime) {
            CompleteTime = completeTime;
        }

        public int getStaus() {
            return Staus;
        }

        public void setStaus(int staus) {
            Staus = staus;
        }

        public String getCouponId() {
            return CouponId;
        }

        public void setCouponId(String couponId) {
            CouponId = couponId;
        }

        public int getDeductionType() {
            return DeductionType;
        }

        public void setDeductionType(int deductionType) {
            DeductionType = deductionType;
        }

        public double getFreight() {
            return Freight;
        }

        public void setFreight(double freight) {
            Freight = freight;
        }

        public boolean isMerchantFreight() {
            return IsMerchantFreight;
        }

        public void setMerchantFreight(boolean merchantFreight) {
            IsMerchantFreight = merchantFreight;
        }

        public String getMerchantName() {
            return MerchantName;
        }

        public void setMerchantName(String merchantName) {
            MerchantName = merchantName;
        }

        public String getMerchantLogo() {
            return MerchantLogo;
        }

        public void setMerchantLogo(String merchantLogo) {
            MerchantLogo = merchantLogo;
        }

        public String getProductInfo() {
            return ProductInfo;
        }

        public void setProductInfo(String productInfo) {
            ProductInfo = productInfo;
        }

        public String getMerchantPhone() {
            return MerchantPhone;
        }

        public void setMerchantPhone(String merchantPhone) {
            MerchantPhone = merchantPhone;
        }

        public int getShipType() {
            return ShipType;
        }

        public void setShipType(int shipType) {
            ShipType = shipType;
        }

        public int getPayMode() {
            return PayMode;
        }

        public void setPayMode(int payMode) {
            PayMode = payMode;
        }

        public double getRefundAmount() {
            return RefundAmount;
        }

        public void setRefundAmount(double refundAmount) {
            RefundAmount = refundAmount;
        }

        public int getWeight() {
            return Weight;
        }

        public void setWeight(int weight) {
            Weight = weight;
        }

        public double getMerchantFreight() {
            return MerchantFreight;
        }

        public void setMerchantFreight(double merchantFreight) {
            MerchantFreight = merchantFreight;
        }

        public double getProductAmount() {
            return ProductAmount;
        }

        public void setProductAmount(double productAmount) {
            ProductAmount = productAmount;
        }

        public double getServiceFee() {
            return ServiceFee;
        }

        public void setServiceFee(double serviceFee) {
            ServiceFee = serviceFee;
        }

        public double getActualIncome() {
            return ActualIncome;
        }

        public void setActualIncome(double actualIncome) {
            ActualIncome = actualIncome;
        }
    }
}

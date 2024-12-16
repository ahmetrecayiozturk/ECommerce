package com.ecommerce.ecommercebackend.ecommerce.dto;


import io.iyzipay.model.Buyer;
import io.iyzipay.model.PaymentCard;
import io.iyzipay.model.Address;
import io.iyzipay.model.BasketItem;
import io.iyzipay.model.Currency;
import io.iyzipay.model.Locale;
import io.iyzipay.model.PaymentChannel;
import io.iyzipay.model.PaymentGroup;

import java.util.List;

public class PaymentRequest {
    private Locale locale;
    private String conversationId;
    private String price;
    private String paidPrice;
    private Currency currency;
    private int installment;
    private String basketId;
    private PaymentChannel paymentChannel;
    private PaymentGroup paymentGroup;
    private PaymentCard paymentCard;
    private Buyer buyer;
    private Address shippingAddress;
    private Address billingAddress;
    private List<BasketItem> basketItems;

    // Getter ve Setter metodları
}

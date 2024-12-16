package org.ecommerce.payment.dto;


import io.iyzipay.model.*;

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

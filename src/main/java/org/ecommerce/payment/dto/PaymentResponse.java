package org.ecommerce.payment.dto;

import io.iyzipay.model.PaymentItem;

import java.util.List;

public class PaymentResponse {
    private String status;
    private String errorCode;
    private String errorMessage;
    private String errorGroup;
    private String paymentId;
    private int fraudStatus;
    private String merchantCommissionRate;
    private String merchantCommissionRateAmount;
    private String iyziCommissionRateAmount;
    private String iyziCommissionFee;
    private String cardType;
    private String cardAssociation;
    private String cardFamily;
    private String cardToken;
    private String cardUserKey;
    private String binNumber;
    private String lastFourDigits;
    private String basketId;
    private String currency;
    private List<PaymentItem> itemTransactions;

    // Getter ve Setter metodları
}
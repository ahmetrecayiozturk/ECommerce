package com.ecommerce.ecommercebackend.ecommerce.services;

import com.ecommerce.ecommercebackend.ecommerce.dto.PaymentRequest;
import com.ecommerce.ecommercebackend.ecommerce.dto.PaymentResponse;
import io.iyzipay.model.Payment;
import io.iyzipay.request.CreatePaymentRequest;
import io.iyzipay.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private Options iyzicoOptions;

    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        CreatePaymentRequest request = new CreatePaymentRequest();
        request.setLocale(paymentRequest.getLocale());
        request.setConversationId(paymentRequest.getConversationId());
        request.setPrice(paymentRequest.getPrice());
        request.setPaidPrice(paymentRequest.getPaidPrice());
        request.setCurrency(paymentRequest.getCurrency());
        request.setInstallment(paymentRequest.getInstallment());
        request.setBasketId(paymentRequest.getBasketId());
        request.setPaymentChannel(paymentRequest.getPaymentChannel());
        request.setPaymentGroup(paymentRequest.getPaymentGroup());
        request.setPaymentCard(paymentRequest.getPaymentCard());
        request.setBuyer(paymentRequest.getBuyer());
        request.setShippingAddress(paymentRequest.getShippingAddress());
        request.setBillingAddress(paymentRequest.getBillingAddress());
        request.setBasketItems(paymentRequest.getBasketItems());

        Payment payment = Payment.create(request, iyzicoOptions);
        PaymentResponse response = new PaymentResponse();
        response.setStatus(payment.getStatus());
        response.setErrorCode(payment.getErrorCode());
        response.setErrorMessage(payment.getErrorMessage());
        response.setErrorGroup(payment.getErrorGroup());
        response.setPaymentId(payment.getPaymentId());
        response.setFraudStatus(payment.getFraudStatus());
        response.setMerchantCommissionRate(payment.getMerchantCommissionRate());
        response.setMerchantCommissionRateAmount(payment.getMerchantCommissionRateAmount());
        response.setIyziCommissionRateAmount(payment.getIyziCommissionRateAmount());
        response.setIyziCommissionFee(payment.getIyziCommissionFee());
        response.setCardType(payment.getCardType());
        response.setCardAssociation(payment.getCardAssociation());
        response.setCardFamily(payment.getCardFamily());
        response.setCardToken(payment.getCardToken());
        response.setCardUserKey(payment.getCardUserKey());
        response.setBinNumber(payment.getBinNumber());
        response.setLastFourDigits(payment.getLastFourDigits());
        response.setBasketId(payment.getBasketId());
        response.setCurrency(payment.getCurrency());
        response.setItemTransactions(payment.getItemTransactions());

        return response;
    }
}
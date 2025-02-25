package org.ecommerce.payment.service;

import com.iyzipay.model.Payment;
import com.iyzipay.request.CreatePaymentRequest;
import com.iyzipay.Options;
import org.ecommerce.payment.dto.PaymentRequest;
import org.ecommerce.payment.dto.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {

    @Autowired
    private Options iyzicoOptions;

    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        CreatePaymentRequest request = new CreatePaymentRequest();
        request.setLocale(paymentRequest.getLocale().toString()); // Convert to String
        request.setConversationId(paymentRequest.getConversationId());
        request.setPrice(new BigDecimal(paymentRequest.getPrice())); // Convert to BigDecimal
        request.setPaidPrice(new BigDecimal(paymentRequest.getPaidPrice())); // Convert to BigDecimal
        request.setCurrency(paymentRequest.getCurrency().toString()); // Convert to String
        request.setInstallment(paymentRequest.getInstallment());
        request.setBasketId(paymentRequest.getBasketId());
        request.setPaymentChannel(paymentRequest.getPaymentChannel().toString()); // Convert to String
        request.setPaymentGroup(paymentRequest.getPaymentGroup().toString()); // Convert to String
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
        response.setMerchantCommissionRate(payment.getMerchantCommissionRate().toString()); // Convert to String
        response.setMerchantCommissionRateAmount(payment.getMerchantCommissionRateAmount().toString()); // Convert to String
        response.setIyziCommissionRateAmount(payment.getIyziCommissionRateAmount().toString()); // Convert to String
        response.setIyziCommissionFee(payment.getIyziCommissionFee().toString()); // Convert to String
        response.setCardType(payment.getCardType());
        response.setCardAssociation(payment.getCardAssociation());
        response.setCardFamily(payment.getCardFamily());
        response.setCardToken(payment.getCardToken());
        response.setCardUserKey(payment.getCardUserKey());
        response.setBinNumber(payment.getBinNumber());
        response.setLastFourDigits(payment.getLastFourDigits());
        response.setBasketId(payment.getBasketId());
        response.setCurrency(payment.getCurrency().toString()); // Convert to String
        // Assuming getItemTransactions() is a valid method or replace with correct method
        //response.setItemTransactions(payment.getItemTransactions());
        response.setItemTransactions(payment.getPaymentItems());
        return response;
    }
}
package com.contract.domain.enumeration;

/**
 * وضعیت پرداخت
 * وضعیت جریان پرداخت‌های قرارداد
 */
public enum PaymentStatus {
    REQUESTED,
    APPROVED,
    REJECTED,
    PAID,
    OVERDUE,
}

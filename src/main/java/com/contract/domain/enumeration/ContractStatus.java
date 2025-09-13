package com.contract.domain.enumeration;

/**
 * وضعیت قرارداد
 * تعیین کننده وضعیت جاری قرارداد در چرخه حیات آن
 */
public enum ContractStatus {
    DRAFT,
    ACTIVE,
    SUSPENDED,
    COMPLETED,
    TERMINATED,
}

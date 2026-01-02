package com.docstencil.core.helper

import java.math.BigDecimal
import java.time.LocalDate


data class Invoice02Invoice(
    val invoiceNumber: String,
    val invoiceDate: LocalDate,
    val company: Invoice02Company,
    val customer: Invoice02Customer,
    val items: List<Invoice02LineItem>,
    val taxRate: Int,
    val paymentTermsDays: Int,
    val paid: Boolean = false
) {
    val subtotal: BigDecimal
        get() = items.fold(BigDecimal.ZERO) { acc, item ->
            acc + (item.unitPrice * item.quantity.toBigDecimal())
        }

    val taxAmount: BigDecimal
        get() = subtotal * (taxRate.toBigDecimal() / BigDecimal(100))

    val total: BigDecimal
        get() = subtotal + taxAmount
}

data class Invoice02Company(
    val name: String,
    val address: String,
    val bankName: String,
    val accountNumber: String
)

data class Invoice02Customer(
    val name: String,
    val email: String
)

data class Invoice02LineItem(
    val description: String,
    val quantity: Int,
    val unitPrice: BigDecimal
)

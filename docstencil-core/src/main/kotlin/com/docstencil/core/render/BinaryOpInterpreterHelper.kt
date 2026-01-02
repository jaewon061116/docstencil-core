package com.docstencil.core.render

import com.docstencil.core.error.TemplaterException
import com.docstencil.core.scanner.model.TemplateToken
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

class BinaryOpInterpreterHelper {
    enum class ComparisonOp {
        GREATER, GREATER_EQUAL, LESS, LESS_EQUAL
    }

    enum class ArithmeticOp {
        ADD, SUBTRACT, MULTIPLY, DIVIDE, MODULO
    }

    fun performArithmetic(left: Any?, right: Any?, operator: TemplateToken, op: ArithmeticOp): Any {
        // Handle BigDecimal (highest precision, takes priority).
        if (left != null && right != null && (left is BigDecimal || right is BigDecimal)) {
            val leftBD = when (left) {
                is BigDecimal -> left
                is Number -> BigDecimal(left.toString())
                else -> throw TemplaterException.RuntimeError(
                    "Operands must be numbers for arithmetic operations.",
                    operator,
                )
            }
            val rightBD = when (right) {
                is BigDecimal -> right
                is Number -> BigDecimal(right.toString())
                else -> throw TemplaterException.RuntimeError(
                    "Operands must be numbers for arithmetic operations.",
                    operator,
                )
            }

            if ((op == ArithmeticOp.DIVIDE || op == ArithmeticOp.MODULO) &&
                rightBD.compareTo(BigDecimal.ZERO) == 0
            ) {
                val opName = if (op == ArithmeticOp.DIVIDE) "Division" else "Modulo"
                throw TemplaterException.RuntimeError("$opName by zero.", operator)
            }

            return when (op) {
                ArithmeticOp.ADD -> leftBD.add(rightBD)
                ArithmeticOp.SUBTRACT -> leftBD.subtract(rightBD)
                ArithmeticOp.MULTIPLY -> leftBD.multiply(rightBD)
                ArithmeticOp.DIVIDE -> leftBD.divide(rightBD, 10, RoundingMode.HALF_UP)
                ArithmeticOp.MODULO -> leftBD.remainder(rightBD)
            }
        }

        // Handle primitive numbers.
        if (left != null && right != null &&
            (left is Byte || left is Short || left is Int || left is Long || left is Float || left is Double) &&
            (right is Byte || right is Short || right is Int || right is Long || right is Float || right is Double)
        ) {
            val zeroByte: Byte = 0
            val zeroShort: Short = 0
            val zeroInt = 0
            val zeroLong = 0L
            val zeroFloat = 0F
            val zeroDouble = 0.0
            val rightIsZero =
                right == zeroByte || right == zeroShort || right == zeroInt || right == zeroLong || right == zeroFloat || right == zeroDouble

            if (op == ArithmeticOp.DIVIDE && rightIsZero) {
                throw TemplaterException.RuntimeError("Division by zero.", operator)
            }
            if (op == ArithmeticOp.MODULO && rightIsZero) {
                throw TemplaterException.RuntimeError("Modulo by zero.", operator)
            }

            return when (left) {
                is Byte -> {
                    when (right) {
                        is Byte -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        is Short -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        is Int -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        is Long -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        is Float -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        is Double -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        else -> throw TemplaterException.FatalError("Missed number type.", operator)
                    }
                }

                is Short -> {
                    when (right) {
                        is Byte -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        is Short -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        is Int -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        is Long -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        is Float -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        is Double -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        else -> throw TemplaterException.FatalError("Missed number type.", operator)
                    }
                }

                is Int -> {
                    when (right) {
                        is Byte -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        is Short -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        is Int -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        is Long -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        is Float -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        is Double -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        else -> throw TemplaterException.FatalError("Missed number type.", operator)
                    }
                }

                is Long -> {
                    when (right) {
                        is Byte -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        is Short -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        is Int -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        is Long -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        is Float -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        is Double -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        else -> throw TemplaterException.FatalError("Missed number type.", operator)
                    }
                }

                is Float -> {
                    when (right) {
                        is Byte -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        is Short -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        is Int -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        is Long -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        is Float -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        is Double -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        else -> throw TemplaterException.FatalError("Missed number type.", operator)
                    }
                }

                is Double -> {
                    when (right) {
                        is Byte -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        is Short -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        is Int -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        is Long -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        is Float -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        is Double -> when (op) {
                            ArithmeticOp.ADD -> left + right
                            ArithmeticOp.SUBTRACT -> left - right
                            ArithmeticOp.MULTIPLY -> left * right
                            ArithmeticOp.DIVIDE -> left / right
                            ArithmeticOp.MODULO -> left % right
                        }

                        else -> throw TemplaterException.FatalError("Missed number type.", operator)
                    }
                }

                else -> throw TemplaterException.FatalError("Missed number type.", operator)
            }
        }

        // Handle non-numbers via string concatenation.
        if (op == ArithmeticOp.ADD && (left is String || right is String)) {
            return Objects.toString(left) + Objects.toString(right)
        }

        val opName = when (op) {
            ArithmeticOp.SUBTRACT -> "subtraction"
            ArithmeticOp.MULTIPLY -> "multiplication"
            ArithmeticOp.DIVIDE -> "division"
            ArithmeticOp.MODULO -> "modulo"
            else -> "operation"
        }
        throw TemplaterException.RuntimeError("Operands must be numbers for $opName.", operator)
    }

    fun compareValues(left: Any?, right: Any?, operator: TemplateToken, op: ComparisonOp): Boolean {
        // Handle numeric type coercion for cross-type comparisons.
        if (left is Number && right is Number) {
            val result = when {
                left is BigDecimal || right is BigDecimal -> {
                    val leftBD = left as? BigDecimal ?: BigDecimal(left.toString())
                    val rightBD = right as? BigDecimal ?: BigDecimal(right.toString())
                    leftBD.compareTo(rightBD)
                }
                left is Double || left is Float || right is Double || right is Float -> {
                    left.toDouble().compareTo(right.toDouble())
                }
                left is Long || right is Long -> {
                    left.toLong().compareTo(right.toLong())
                }
                else -> {
                    left.toInt().compareTo(right.toInt())
                }
            }
            return when (op) {
                ComparisonOp.GREATER -> result > 0
                ComparisonOp.GREATER_EQUAL -> result >= 0
                ComparisonOp.LESS -> result < 0
                ComparisonOp.LESS_EQUAL -> result <= 0
            }
        }

        if (left is Comparable<*> && right != null) {
            try {
                @Suppress("UNCHECKED_CAST")
                val comparableLeft = left as Comparable<Any>
                val result = comparableLeft.compareTo(right)
                return when (op) {
                    ComparisonOp.GREATER -> result > 0
                    ComparisonOp.GREATER_EQUAL -> result >= 0
                    ComparisonOp.LESS -> result < 0
                    ComparisonOp.LESS_EQUAL -> result <= 0
                }
            } catch (_: ClassCastException) {
                throw TemplaterException.RuntimeError(
                    "Operands are not comparable: ${left::class.simpleName} and ${right::class.simpleName}",
                    operator,
                )
            }
        }

        throw TemplaterException.RuntimeError(
            "Operands must be numbers or comparable types.",
            operator,
        )
    }
}

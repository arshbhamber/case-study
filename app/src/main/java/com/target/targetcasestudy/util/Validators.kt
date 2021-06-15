package com.target.targetcasestudy.util

/**
 * For an explanation of how to validate credit card numbers read:
 *
 * https://www.freeformatter.com/credit-card-number-generator-validator.html#fakeNumbers
 *
 * This contains a breakdown of how this algorithm should work as
 * well as a way to generate fake credit card numbers for testing
 *
 * The structure and signature of this is open to modification, however
 * it *must* include a method, field, etc that returns a [Boolean]
 * indicating if the input is valid or not
 *
 * Additional notes:
 *  * This method does not need to validate the credit card issuer
 *  * This method must validate card number length (13 - 19 digits), but does not
 *    need to validate the length based on the issuer.
 *
 * @param creditCardNumber - credit card number of (13, 19) digits
 * @return true if a credit card number is believed to be valid,
 * otherwise false
 */

object Validators {

    fun validateCreditCard(creditCardNumber: String): Boolean {

        val length = creditCardNumber.length

        if (length < 13 || length > 19) {
            return false
        }

        val last: Int = Character.digit(creditCardNumber[length - 1], 10)
        var sum = 0

        for (i in 0 until length - 1) {

            var number = Character.digit(creditCardNumber[length - 2 - i], 10)

            if (i % 2 == 0) {
                number *= 2
            }

            if (number > 9) {
                number -= 9
            }

            sum += number
        }

        return (sum+last) % 10 == 0 && creditCardNumber.length >= 13 && creditCardNumber.length <= 19

    }

}

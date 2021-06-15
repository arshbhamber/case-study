package com.target.targetcasestudy

import com.target.targetcasestudy.util.Validators
import org.junit.Assert
import org.junit.Test

/**
 * Feel free to make modifications to these unit tests! Remember, you have full technical control
 * over the project, so you can use any libraries and testing strategies that see fit.
 */
class CreditCardValidatorTest {

    @Test
    fun `is credit card number valid`() {

        val validNumbers = arrayOf(
            "6763544546245813",
            "6762028831982802",
            "5020362423266039",
            "6388119291077391",
            "6388938275643385",
            "6383564847055265",
            "3529183089857528",
            "3528394073539565",
            "3529258257283318194",
            "4792985350367815",
            "4024007189747172",
            "4716439849404666339",
            "5168656939447989",
            "342514410443599",
        )

        validNumbers.forEach {
            Assert.assertTrue(
                "valid credit card number should yield true",
                Validators.validateCreditCard(it)
            )
        }

    }

    @Test
    fun `is credit card number invalid`() {

        val invalidNumbers = arrayOf(
            "",
            "092309029370592730272057",
            "9384tlkdlwdg",
            "0928309",
            "35$%3940!@539565",
            "aaaaaaaaaaaaaaaaa",
        )

        invalidNumbers.forEach {
            Assert.assertFalse(
                "invalid credit card number should yield false",
                Validators.validateCreditCard(it)
            )
        }


    }

}

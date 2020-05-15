package org.mifos.mobilewallet.mifospay.utils

import android.text.TextUtils
import android.util.Patterns

object ValidateUtil {
  fun isValidEmail(email: String?): Boolean {
    return (!TextUtils.isEmpty(email)
      && Patterns.EMAIL_ADDRESS.matcher(email)
      .matches())
  }
}
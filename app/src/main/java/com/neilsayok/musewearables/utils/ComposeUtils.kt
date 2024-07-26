package com.neilsayok.musewearables.utils

import androidx.annotation.DimenRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.neilsayok.musewearables.R

private val provider by lazy {
    GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )
}

private val weightList by lazy {
    listOf(
        FontWeight.Thin,
        FontWeight.ExtraLight,
        FontWeight.Light,
        FontWeight.Normal,
        FontWeight.Medium,
        FontWeight.SemiBold,
        FontWeight.Bold,
        FontWeight.ExtraBold,
    )
}

private val roboto by lazy { GoogleFont("Roboto") }

val FontPrimary by lazy {
    FontFamily(
        weightList.map { weight ->
            androidx.compose.ui.text.googlefonts.Font(
                googleFont = roboto,
                fontProvider = provider,
                weight = weight
            )
        }
    )
}

val FontSFPro by lazy { FontFamily(Font(R.font.sf_pro_text_regular)) }

@Composable
@ReadOnlyComposable
fun fontDimensionResource(@DimenRes id: Int): TextUnit = dimensionResource(id = id).value.sp

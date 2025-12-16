package com.vasiliev.onelook.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.vasiliev.onelook.R

private val NotoSans = FontFamily(
    Font(R.font.noto_sans_regular, FontWeight.Normal),
    Font(R.font.noto_sans_semibold, FontWeight.SemiBold),
    Font(R.font.noto_sans_bold, FontWeight.Bold),
)

object AppText {

    val H1 = TextStyle(
        fontFamily = NotoSans,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        lineHeight = 39.sp,
        letterSpacing = 0.002.em
    )

    val H2 = TextStyle(
        fontFamily = NotoSans,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.012.em
    )

    val H3 = TextStyle(
        fontFamily = NotoSans,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp
    )

    val Body1 = TextStyle(
        fontFamily = NotoSans,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.012.em
    )

    val Body2 = TextStyle(
        fontFamily = NotoSans,
        fontSize = 14.sp,
        lineHeight = 21.sp,
        letterSpacing = 0.012.em
    )

    val Body3 = TextStyle(
        fontFamily = NotoSans,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.012.em
    )

    val Button = TextStyle(
        fontFamily = NotoSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        letterSpacing = 0.012.em
    )

    val ButtonSmall = TextStyle(
        fontFamily = NotoSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        letterSpacing = 0.012.em
    )
}

val AppTypography = Typography(
    headlineLarge = AppText.H1,
    headlineMedium = AppText.H2,
    titleLarge = AppText.H3,
    bodyLarge = AppText.Body1,
    bodyMedium = AppText.Body2,
    bodySmall = AppText.Body3,
    labelLarge = AppText.Button,
    labelSmall = AppText.ButtonSmall
)

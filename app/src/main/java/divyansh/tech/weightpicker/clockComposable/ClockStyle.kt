package divyansh.tech.weightpicker.clockComposable

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ClockStyle(
    val fiveStepColor: Color = Color.Black,
    val normalColor: Color = Color.LightGray,
    val clockRadius: Dp = 150.dp,
    val clockWidth: Dp = 30.dp,
    val fiveStepLength: Dp = clockWidth,
    val normalStepLength: Dp = clockWidth / 2,
)
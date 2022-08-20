package divyansh.tech.weightpicker.clockComposable

import android.graphics.Color
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

enum class ClockTicks {
    MINUTE, SECONDS
}

@Composable
fun Clock(
    modifier: Modifier,
    style: ClockStyle = ClockStyle(),
    onTickChange: (Int) -> Unit
) {

    val radius = style.clockRadius
    val clockWidth = style.clockWidth

    var angle by remember {
        mutableStateOf(0f)
    }

    Canvas(modifier = modifier.size(radius * 2f)) {
        val center = this.center
        val clockCenter = Offset(
            x = center.x,
            y = (clockWidth.toPx() / 2f) + radius.toPx()
        )
        val outerRadius = radius.toPx() + clockWidth.toPx() / 2f
        val innerRadius = radius.toPx() - clockWidth.toPx()

        drawContext.canvas.nativeCanvas.apply {
            drawCircle(
                clockCenter.x,
                clockCenter.y,
                style.clockRadius.toPx(),
                Paint().apply {
                    strokeWidth = style.clockWidth.toPx()
                    color = Color.WHITE
                    setStyle(Paint.Style.STROKE)
                    setShadowLayer(
                        60f,
                        0f,
                        0f,
                        Color.argb(50, 0, 0, 0)
                    )
                }
            )
        }

        for (i in 0..59) {
            val lineType = when {
                i % 5 == 0 -> ClockTicks.MINUTE
                else -> ClockTicks.SECONDS
            }
            val lineColor = when(lineType) {
                ClockTicks.MINUTE -> style.fiveStepColor
                ClockTicks.SECONDS -> style.normalColor
            }
            val lineLength = when(lineType) {
                ClockTicks.MINUTE -> 20.dp.toPx()
                ClockTicks.SECONDS -> 15.dp.toPx()
            }

            val angleInRad = i * 6f * (PI.toFloat() / 180f)

            val startOffset = Offset(
                x = clockCenter.x + cos(angleInRad) * outerRadius,
                y = outerRadius * sin(angleInRad) + clockCenter.y
            )

            val endOffset = Offset(
                x = clockCenter.x + cos(angleInRad) * (outerRadius - lineLength),
                y = (outerRadius - lineLength) * sin(angleInRad) + clockCenter.y
            )

            drawLine(
                start = startOffset,
                end = endOffset,
                color = lineColor,
                strokeWidth = 1.dp.toPx()
            )
        }
    }
}
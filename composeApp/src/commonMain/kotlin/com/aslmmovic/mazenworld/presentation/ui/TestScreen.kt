import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun IronManCharacter(modifier: Modifier = Modifier) {
    val armorRed = Color(0xFFC70000) // Deep Red
    val faceGold = Color(0xFFFDD835) // Gold Yellow
    val eyeWhite = Color(0xFFFFFFFF) // White

    val infiniteTransition = rememberInfiniteTransition(label = "IronManInfiniteTransition")
    val breathingScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.02f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "BreathingScale"
    )
    val eyeGlowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "EyeGlowAlpha"
    )


    Canvas(modifier = modifier) {
        val headWidth = size.width * 0.8f * breathingScale
        val headHeight = size.height * 0.9f * breathingScale
        val headTop = (size.height - headHeight) / 2
        val headLeft = (size.width - headWidth) / 2

        // Outer Helmet Shape (Red)
        val helmetPath = Path().apply {
            moveTo(headLeft + headWidth * 0.5f, headTop) // Top center
            cubicTo(
                headLeft + headWidth * 0.9f, headTop, // Top right control
                headLeft + headWidth, headTop + headHeight * 0.2f, // Mid right control
                headLeft + headWidth, headTop + headHeight * 0.7f // Mid right
            )
            cubicTo(
                headLeft + headWidth, headTop + headHeight * 0.9f, // Bottom right control
                headLeft + headWidth * 0.8f, headTop + headHeight, // Bottom right control
                headLeft + headWidth * 0.5f, headTop + headHeight // Bottom center
            )
            cubicTo(
                headLeft + headWidth * 0.2f, headTop + headHeight, // Bottom left control
                headLeft, headTop + headHeight * 0.9f, // Bottom left control
                headLeft, headTop + headHeight * 0.7f // Mid left
            )
            cubicTo(
                headLeft, headTop + headHeight * 0.2f, // Mid left control
                headLeft + headWidth * 0.1f, headTop, // Top left control
                headLeft + headWidth * 0.5f, headTop // Top center
            )
            close()
        }
        drawPath(helmetPath, color = armorRed, style = Fill)

        // Faceplate (Gold)
        val faceplatePath = Path().apply {
            moveTo(headLeft + headWidth * 0.2f, headTop + headHeight * 0.3f)
            lineTo(headLeft + headWidth * 0.8f, headTop + headHeight * 0.3f)
            // Right jaw line
            cubicTo(
                headLeft + headWidth * 0.9f, headTop + headHeight * 0.45f,
                headLeft + headWidth * 0.8f, headTop + headHeight * 0.7f,
                headLeft + headWidth * 0.65f, headTop + headHeight * 0.85f
            )
            // Chin
            lineTo(headLeft + headWidth * 0.35f, headTop + headHeight * 0.85f)
            // Left jaw line
            cubicTo(
                headLeft + headWidth * 0.2f, headTop + headHeight * 0.7f,
                headLeft + headWidth * 0.1f, headTop + headHeight * 0.45f,
                headLeft + headWidth * 0.2f, headTop + headHeight * 0.3f
            )
            close()
        }
        drawPath(faceplatePath, color = faceGold, style = Fill)

        // Eye Slits (White, glowing)
        val eyeWidth = headWidth * 0.2f
        val eyeHeight = headHeight * 0.08f
        val eyeY = headTop + headHeight * 0.4f

        // Left Eye
        drawRoundRect(
            color = eyeWhite.copy(alpha = eyeGlowAlpha),
            topLeft = Offset(headLeft + headWidth * 0.25f, eyeY),
            size = Size(eyeWidth, eyeHeight),
            cornerRadius = CornerRadius(x = 8.dp.toPx(), y = 8.dp.toPx()),
            style = Fill
        )

        // Right Eye
        drawRoundRect(
            color = eyeWhite.copy(alpha = eyeGlowAlpha),
            topLeft = Offset(headLeft + headWidth * 0.55f, eyeY),
            size = Size(eyeWidth, eyeHeight),
            cornerRadius = CornerRadius(x = 8.dp.toPx(), y = 8.dp.toPx()),
            style = Fill
        )

        // Mouth Slit (Gold)
        val mouthWidth = headWidth * 0.3f
        val mouthHeight = headHeight * 0.03f
        val mouthY = headTop + headHeight * 0.65f
        drawRoundRect(
            color = faceGold,
            topLeft = Offset(headLeft + headWidth * 0.35f, mouthY),
            size = Size(mouthWidth, mouthHeight),
            cornerRadius = CornerRadius(x = 4.dp.toPx(), y = 4.dp.toPx()),
            style = Fill
        )
    }
}


enum class CharacterMood {
    Thinking,
    Right,
    Wrong
}

@Composable
fun YellowCharacter(
    mood: CharacterMood,
    modifier: Modifier = Modifier
) {
    // Infinite transition for looping animations
    val infiniteTransition = rememberInfiniteTransition(label = "characterLoop")
    val rightColor = Color(0xFF81C784)    // greenish
    val wrongColor = Color(0xFFE57373)    // reddish
    val thinkingColor = Color(0xFFFFD54F) // yellowish
    // 👀 Eye movement (used mainly for Thinking)
    val eyeShift by infiniteTransition.animateFloat(
        initialValue = -15f,
        targetValue = 15f,
        animationSpec = infiniteRepeatable(
            animation = tween(600, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "eyeShift"
    )

    // 😀 Bounce animation (for Right mood)
    val bounce by infiniteTransition.animateFloat(
        initialValue = -15f,
        targetValue = 15f,
        animationSpec = infiniteRepeatable(
            animation = tween(600, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        ),
        label = "bounce"
    )

    // 😞 Shake animation (for Wrong mood)
    val shake by infiniteTransition.animateFloat(
        initialValue = -15f,
        targetValue = 15f,
        animationSpec = infiniteRepeatable(
            animation = tween(600, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "shake"
    )


    val bodyColor by animateColorAsState(
        targetValue = when (mood) {
            CharacterMood.Right -> rightColor
            CharacterMood.Wrong -> wrongColor
            CharacterMood.Thinking -> thinkingColor
        },
        animationSpec = tween(durationMillis = 600, easing = LinearOutSlowInEasing),
        label = "bodyColor"
    )

    // Mood transition for facial expression
    val transition = updateTransition(targetState = mood, label = "moodTransition")

    val mouthSmile by transition.animateFloat(
        transitionSpec = { tween(400) },
        label = "mouthSmile"
    ) { state ->
        when (state) {
            CharacterMood.Right -> 1f
            CharacterMood.Wrong -> -1.5f
            CharacterMood.Thinking -> 0f
        }
    }

    Canvas(
        modifier = modifier
            .size(200.dp)
            .background(Color.Transparent)
    ) {
        val width = size.width
        val height = size.height

        // Base offsets for mood animations
        val yOffset = if (mood == CharacterMood.Right) bounce else 0f
        val xOffset = if (mood == CharacterMood.Wrong) shake else 0f

        // Draw body (shifted a bit if bouncing/shaking)
        drawOval(
            color = bodyColor,
            topLeft = Offset(width * 0.2f + xOffset, height * 0.2f - yOffset),
            size = Size(width * 0.6f, height * 0.6f)
        )

        // Eyes
        val eyeRadius = width * 0.07f
        val thinkingShift = if (mood == CharacterMood.Thinking) eyeShift else 0f
        val leftEyeCenter = Offset(width * 0.4f + thinkingShift + xOffset, height * 0.45f - yOffset)
        val rightEyeCenter =
            Offset(width * 0.6f + thinkingShift + xOffset, height * 0.45f - yOffset)

        // Glasses frames
        drawCircle(
            Color.Black,
            radius = eyeRadius + 5f,
            center = leftEyeCenter,
            style = Stroke(width = 4f)
        )
        drawCircle(
            Color.Black,
            radius = eyeRadius + 5f,
            center = rightEyeCenter,
            style = Stroke(width = 4f)
        )
        drawLine(
            Color.Black,
            leftEyeCenter.copy(x = leftEyeCenter.x + eyeRadius + 5f),
            rightEyeCenter.copy(x = rightEyeCenter.x - eyeRadius - 5f),
            strokeWidth = 4f
        )

        // Eye pupils
        drawCircle(Color.Black, radius = eyeRadius * 0.5f, center = leftEyeCenter)
        drawCircle(Color.Black, radius = eyeRadius * 0.5f, center = rightEyeCenter)

        // Mouth expression
        val mouthStart = if (mouthSmile >= 0) 20f else 200f
        val mouthSweep = if (mouthSmile >= 0) 140f else -140f
        drawArc(
            color = Color.Black,
            startAngle = mouthStart,
            sweepAngle = mouthSweep,
            useCenter = false,
            topLeft = Offset(width * 0.4f + xOffset, height * 0.55f - yOffset),
            size = Size(width * 0.2f, height * 0.15f),
            style = Stroke(width = 5f, cap = StrokeCap.Round)
        )
    }
}


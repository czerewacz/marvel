package ai.akun.character_presentation.ui.details

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString

class CharacterDetailsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    fun setCharacter(character: ai.akun.character_domain.model.CharacterDomainEntity) = with(character) {
        text = buildSpannedString {

            bold { append("Comics Available: ") }
            appendLine(comics.size.toString())

            bold { append("Comics: ") }
            appendLine()
            comics.forEach { comicName ->

                appendLine("\u2022 $comicName")
            }
        }
    }
}
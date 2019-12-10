package id.seedgrow.noteszone.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @author Adi Suryantoro (adisuryantoro.mail@gmail.com)
 * @version FileNoteModel, v 0.1 08/12/2019 by Adi Suryantoro
 */

/* Add ini build.gradle app

androidExtensions {
        experimental = true
    }

*/

//After using data class
@Parcelize
data class FileNoteModel(
    var id: String = "",
    var title: String = "",
    var desc: String ="",
    var time: String = ""
): Parcelable

//Before using data class
/*class NoteModel(var title: String, var desc: String) {
    constructor(): this("", "")
}*/
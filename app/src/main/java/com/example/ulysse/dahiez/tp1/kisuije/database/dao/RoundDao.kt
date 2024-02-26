import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RoundDao {
    @Insert
    fun insert(round: Round?)

    @get:Query("SELECT * FROM round")
    val allRounds: List<Round?>?
}

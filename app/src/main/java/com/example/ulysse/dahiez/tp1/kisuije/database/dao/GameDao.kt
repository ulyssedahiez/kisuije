import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GameDao {
    @Insert
    fun insert(game: Game?)

    @get:Query("SELECT * FROM game")
    val allGames: List<Game?>?
}

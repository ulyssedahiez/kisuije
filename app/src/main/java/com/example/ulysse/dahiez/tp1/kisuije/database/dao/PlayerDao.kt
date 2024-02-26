import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PlayerDao {
    @Insert
    fun insert(player: Player?)

    @get:Query("SELECT * FROM player")
    val allPlayers: List<Player?>?
}

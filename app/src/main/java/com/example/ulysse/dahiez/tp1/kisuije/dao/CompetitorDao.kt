import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CompetitorDao {
    @Insert
    fun insert(competitor: Competitor?)

    @get:Query("SELECT * FROM competitor")
    val allCompetitors: List<Competitor?>?
}

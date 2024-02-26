import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StarDao {
    @Insert
    fun insert(star: Star?)

    @get:Query("SELECT * FROM star")
    val allStars: List<Star?>?
}

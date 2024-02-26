import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StarDao {
    @Query("SELECT * FROM stars")
    fun getAll(): List<Star>

    @Insert
    fun insertAll(vararg stars: Star)
}

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Competitor(
    @PrimaryKey val id_competitor: Int,
    val id_player: Int,
    val id_star: Int,
    val nb_point: Int,
    val id_game: Int
)

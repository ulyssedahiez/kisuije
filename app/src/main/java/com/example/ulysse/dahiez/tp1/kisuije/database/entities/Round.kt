import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Round(
    @PrimaryKey val id_round: Int,
    val id_game: Int,
    val list_competitor: List<Competitor>
)

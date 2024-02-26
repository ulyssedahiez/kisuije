import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Game(
    @PrimaryKey val id_game: Int,
    val list_player: List<Player>,
    val list_round: List<Round>,
    val nb_round: Int
)

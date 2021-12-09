package clothDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Top")
data class TopCloth(
    @PrimaryKey @ColumnInfo(name = "TopName") val name: String,
    val total: String,
    val shoulder: String,
    val chest: String,
    val sleeve: String,
    val classfi: String //분류
)

@Entity(tableName = "Bottom")
data class BottomCloth(
        @PrimaryKey @ColumnInfo(name = "BottomName") val name: String,
        val total: String,
        val waist: String, //허리 단면
        val thigh: String, //허벅지 단면
        val crotch: String, //밑위
        val hem: String, //밑단
        val classfi: String //분류
)

@Entity(tableName = "Outer")
data class OuterCloth(
        @PrimaryKey @ColumnInfo(name = "OuterName") val name: String,
        val total: String,
        val shoulder: String,
        val chest: String,
        val sleeve: String,
        val classfi: String //분류
)
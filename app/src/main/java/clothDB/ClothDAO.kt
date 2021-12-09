package clothDB

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ClothDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopCloth(topCloth: TopCloth)

    @Delete
    suspend fun deleteTopCloth(topCloth: TopCloth)//기본키로 찾고 삭제할 것

    @Query("SELECT * FROM Top")
    fun getAllTopCloth(): LiveData<List<TopCloth>>

    @Query("SELECT * FROM Top WHERE classfi = :classfi")
    fun getClassTopCloth(classfi :String): LiveData<List<TopCloth>>

    @Query("SELECT * FROM Top WHERE TopName = :name")
    suspend fun getTopByName(name: String): TopCloth

    @Query("SELECT classfi FROM Top WHERE TopName = :name")
    suspend fun getClassOfTopByName(name: String): String


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBottomCloth(bottomCloth: BottomCloth)

    @Delete
    suspend fun deleteBottomCloth(bottomCloth: BottomCloth)

    @Query("SELECT * FROM Bottom")
    fun getAllBottomCloth(): LiveData<List<BottomCloth>>

    @Query("SELECT * FROM Bottom WHERE classfi = :classfi")
    fun getClassBottomCloth(classfi: String): LiveData<List<BottomCloth>>

    @Query("SELECT * FROM Bottom WHERE BottomName = :name")
    suspend fun getBottomByName(name: String): BottomCloth

    @Query("SELECT classfi FROM Bottom WHERE BottomName = :name")
    suspend fun getClassOfBottomByName(name: String): String


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOuterCloth(outerCloth: OuterCloth)

    @Delete
    suspend fun deleteOuterCloth(outerCloth: OuterCloth)

    @Query("SELECT * FROM `Outer`")
    fun getAllOuterCloth(): LiveData<List<OuterCloth>>

    @Query("SELECT * FROM `Outer` WHERE classfi = :classfi")
    fun getClassOuterCloth(classfi: String): LiveData<List<OuterCloth>>

    @Query("SELECT * FROM `Outer` WHERE OuterName = :name")
    suspend fun getOuterByName(name: String): OuterCloth

    @Query("SELECT classfi FROM `Outer` WHERE OuterName = :name")
    suspend fun getClassOfOuterByName(name: String): String
}
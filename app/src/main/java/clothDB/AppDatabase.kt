package clothDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [TopCloth::class, BottomCloth::class, OuterCloth::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun getClothDAO() : ClothDAO

    //데이터베이스는 너무 느려서 싱글톤이 권장된다.
    companion object{
        private var INSTANCE: AppDatabase? = null

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }
        }
        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }
        }

        fun getDatabase(context: Context): AppDatabase{
            if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                            context, AppDatabase::class.java, "clothDB.db")
                            .addMigrations(MIGRATION_1_2)
                            .build()
                }
            return INSTANCE as AppDatabase
        }
    }
}